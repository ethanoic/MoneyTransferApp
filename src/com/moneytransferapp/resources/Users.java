package com.moneytransferapp.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import com.moneytransferapp.models.CreateUser;
import com.moneytransferapp.services.UserManager;

@Path("users")
public class Users {
	
	private UserManager manager = new UserManager();
	@POST
	@Consumes({"application/json","application/xml"})
	public Response Create(CreateUser user) {
		
		// send to manager class to add to db
		manager.CreateUser(user);
		
		return Response.ok().build();
	}
	
}
