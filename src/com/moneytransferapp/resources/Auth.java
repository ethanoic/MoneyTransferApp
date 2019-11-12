package com.moneytransferapp.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.moneytransferapp.services.AuthManager;

@Path("auth")
public class Auth {

	private AuthManager authManager = new AuthManager();
	
	@POST
	public Response Login(
			@FormParam("username") String username, 
			@FormParam("password") String password) {
		
		// auth manager to validate username and password
		String token = authManager.AuthenticateGetJWT(username, password);
		
		return Response.ok(token).build();
	}
}
