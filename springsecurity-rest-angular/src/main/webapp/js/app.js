// create the module and name it kartzHubApp
// also include ngRoute for all our routing needs
var kartzHubApp = angular.module('kartzHubApp', [ 'ngRoute', 'ngCookies',
		'kartzHubApp.services' ]);

kartzHubApp.constant('APP_URL', '/springsecurity-rest-angular/rest')

// configure our routes
kartzHubApp.config(function($routeProvider,$httpProvider) {
	$httpProvider.interceptors.push('myHttpInterceptor');
	$routeProvider.when('/login', {
		templateUrl : 'pages/login.html',
		controller : 'loginController'
	})

	.when('/register', {
		templateUrl : 'pages/register.html',
		controller : 'registerController'
	}).when('/dashboard', {
		templateUrl : 'pages/dashboard.html',
		controller : 'dashboardController'
	}).when('/forgotpassword', {
		templateUrl : 'pages/forgotpassword.html',
		controller : 'forgotpasswordController'
	}).when('/logout', {
		templateUrl : 'pages/login.html',
		controller : 'LogoutController'
	}).otherwise({
		redirectTo : '/login'
	});
});

// create the controller and inject Angular's $scope
kartzHubApp.controller('loginController', function($rootScope, $scope,$cookies,
		UserService, $location) {
	$rootScope.bodyClass = 'bg-dark';
	
	$scope.signin = function(user) {
		UserService.login({
			emailAddress : user.email,
			password : user.password
		}).success(function(user) {
			$rootScope.user = user;
			$location.path("/dashboard");
		});
	}
});
kartzHubApp.controller('LogoutController', function($rootScope, $location,
		UserService) {
	
	$rootScope.bodyClass = 'bg-dark';
	UserService.inValidateSession();
	$rootScope.user = '';
	$location.path('/login')
});
kartzHubApp.controller('dashboardController', function($rootScope, UserService,
		$location) {
	if (UserService.isLoggedIn()) {
		$rootScope.bodyClass = '';
	} else {
		$location.path('/login')
	}

});
kartzHubApp.controller('registerController', function($rootScope) {
	$rootScope.bodyClass = 'bg-dark';
});
kartzHubApp.controller('forgotpasswordController', function($rootScope) {
	$rootScope.bodyClass = 'bg-dark forgot-pwd';
});