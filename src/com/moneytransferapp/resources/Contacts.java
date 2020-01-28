package com.moneytransferapp.resources;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.moneytransferapp.services.ContactsManager;

@Path("contacts")
public class Contacts {
	
	private int userid;
	
	public Contacts() {
		this.userid = 0;
	}
	
	public Contacts(int userid) {
		this.userid = userid;
	}
	
	ContactsManager manager = new ContactsManager();
	
	// DELETE user/{userid}/contacts/{contactid}
	@DELETE
	@RolesAllowed("user")
	@Path("{contactid}")
	public Response Delete(@PathParam("contactid") int contactid) {
		if (this.userid == 0) {
			manager.Delete(contactid);
		} else {
			manager.Delete(contactid, this.userid);
		}
		return Response.ok().build();
	}
}
