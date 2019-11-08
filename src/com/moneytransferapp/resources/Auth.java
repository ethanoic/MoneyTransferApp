package com.moneytransferapp.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("auth")
public class Auth {

	@POST
	public Response Login(
			@FormParam("username") String username, 
			@FormParam("password") String password) {
		
		// auth manager to validate username and password
		
		return Response.ok(username + "," + password).build();
	}
}
