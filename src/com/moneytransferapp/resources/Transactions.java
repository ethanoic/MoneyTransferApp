package com.moneytransferapp.resources;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("transactions")
public class Transactions {
	
	@GET
	@RolesAllowed("user")
	public Response GetAll() {
		return Response.ok("this is protected").build();
	}
}
