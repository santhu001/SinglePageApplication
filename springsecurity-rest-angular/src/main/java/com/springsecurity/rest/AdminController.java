package com.springsecurity.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/admin")
public class AdminController {
	@GET
	@Path("/msg")
	public String getMsg() {
		return "hi";
	}
}
