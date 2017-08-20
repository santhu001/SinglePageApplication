package com.springsecurity.rest;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.springsecurity.dao.LoginDAO;
import com.springsecurity.model.MyUser;
@Path("/login")
public class Loginservice {
	private LoginDAO loginDao;
	public void setLoginDao(LoginDAO loginDao) {
		this.loginDao = loginDao;
	}

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(MyUser user) {
		MyUser user2=loginDao.login(user.getEmailAddress(), user.getPassword());
		if(user2!=null) {
		return Response.status(201).entity(user2.getName()).build();
		}
		return Response.status(401).entity("Invalid credentionls").build();
	}
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(MyUser user) {
		Serializable id=loginDao.register(user);
		if(id!=null) {
			return Response.status(203).entity(user.getName()).build();	
		}
		return Response.status(201).entity(user.getName()).build();
	}
	
}
