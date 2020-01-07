package com.moneytransferapp.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import com.moneytransferapp.models.CreateUser;
import com.moneytransferapp.models.GetUserModel;
import com.moneytransferapp.services.UserManager;

@Path("users")
public class Users {
	
	private UserManager manager = new UserManager();
	@POST
	@Consumes({"application/json","application/xml"})
	public Response Create(CreateUser user) {
		
		// send to manager class to add to db
		int id = manager.CreateUser(user);
		
		return Response.ok(id).build();
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response GetOne(@PathParam("id") int id) {
		
		// TODO get 1 user object
		GetUserModel user = manager.GetUser(id);
		
		return Response.ok(user).build();
	}
}
