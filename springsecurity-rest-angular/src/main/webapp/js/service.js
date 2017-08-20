/**
 * 
 */
var module = angular.module('kartzHubApp.services', [])
module.factory('FlashService', function($rootScope) {
	return {
		show : function(message) {
			$rootScope.flash = message;

		},
		clear : function() {
			$rootScope.flash = '';
		}
	}
});
module.factory("SessionService", function() {
	return {
		get : function(key) {
			return sessionStorage.getItem(key);
		},
		set : function(key, val) {
			return sessionStorage.setItem(key, val);
		},
		unset : function(key) {
			return sessionStorage.removeItem(key);
		}
	}
});
module.factory('UserService', function($http, SessionService, FlashService,
		APP_URL) {
	var cacheSession = function() {
		SessionService.set('authenticated', true);
	};
	var uncacheSession = function() {
		SessionService.unset('authenticated');
	};
	var loginError = function(response) {
		console.log(response)
		FlashService.show(response.flash);
	};
	return {
		login : function(data) {
			var login = $http.post(APP_URL + "/login/login",
					data);
			login.success(cacheSession);
			login.success(FlashService.clear);
			login.error(loginError);
			return login;
		},
		logout : function() {
			var logout = $http.get(APP_URL + "/login/logout");
			logout.success(uncacheSession);
			return logout;
		},
		isLoggedIn : function() {
			return SessionService.get('authenticated');
		},
		inValidateSession:function() {
			uncacheSession();
		}
	}
});

module.factory('myHttpInterceptor',function($q,$cookies){
	var token = null;
	return {
		'request' : function(config) {
			config.headers['X-XSRF-TOKEN'] = $cookies['XSRF-TOKEN'];
			return config;
		},
		'response': function(response){
            token = response.headers('XSRF-TOKEN');
            console.log(token)
            return response;
        }
	}
})
	
