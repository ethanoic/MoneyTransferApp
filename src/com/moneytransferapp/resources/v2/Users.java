package com.moneytransferapp.resources.v2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.moneytransferapp.models.v2.GetUserModel;
import com.moneytransferapp.models.GetUserProfileModel;
import com.moneytransferapp.models.Link;
import com.moneytransferapp.services.UserManager;

@Path("v2/users")
public class Users extends com.moneytransferapp.resources.Users {
	
	private UserManager manager = new UserManager();
	
	@Override
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response GetOne(@PathParam("id") int id) {
		GetUserProfileModel profile = manager.GetUserProfile(id);
		
		GetUserModel user = new GetUserModel();
		user.profile = profile;
		Link linkTransactions = new Link();
		linkTransactions.Label = "Transactions";
		linkTransactions.Url = "/users/" + id + "/transactions";
		linkTransactions.Method = "GET";
		user.links.add(linkTransactions);

		Link linkContacts = new Link();
		linkContacts.Label = "Contacts";
		linkContacts.Url = "/users/" + id + "/contacts";
		linkContacts.Method = "GET";
		user.links.add(linkContacts);
		
		return Response.ok(user).build();
	}
}
