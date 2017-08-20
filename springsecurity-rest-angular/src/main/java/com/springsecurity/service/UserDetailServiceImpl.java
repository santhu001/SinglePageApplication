package com.springsecurity.service;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springsecurity.dao.UserDao;
import com.springsecurity.model.Role;
import com.springsecurity.model.User;
import com.springsecurity.model.UserStatus;
@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	private UserDao userDao;
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userDao.findUserByName(username);
		if(user!=null) {
		String password=user.getPassword();
		boolean enabled=user.getStatus().equals(UserStatus.ACTIVE);
		boolean accountNonExpired=user.getStatus().equals(UserStatus.ACTIVE);
		boolean credentialsNonExpired=user.getStatus().equals(UserStatus.ACTIVE);
		boolean accountNonLocked=user.getStatus().equals(UserStatus.ACTIVE);
		
		Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		for(Role role:user.getRoles()) {
			authorities.add(new GrantedAuthority() {
				
				@Override
				public String getAuthority() {
					// TODO Auto-generated method stub
					return role.getRoleName();
				}
			});
		}
		
		org.springframework.security.core.userdetails.User securityUser=
				new org.springframework.security.core.userdetails.User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
		return securityUser;
		}else {
			throw new UsernameNotFoundException("User not found!!");
		}
	}

	
}
