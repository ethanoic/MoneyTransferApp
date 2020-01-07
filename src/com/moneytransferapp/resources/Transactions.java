package com.moneytransferapp.resources;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.moneytransferapp.models.AddTransaction;
import com.moneytransferapp.models.GetTransactionModel;
import com.moneytransferapp.services.TransactionsManager;

@Path("transactions")
public class Transactions {
	
	private TransactionsManager txManager = new TransactionsManager();
	
	@GET
	@RolesAllowed("user")
	@Produces({"application/json; qs=1", "application/xml; qs=0.5"})
	public Response GetAll() {
		// return transaction objects
		GetTransactionModel tx = new GetTransactionModel();
		
		return Response.ok(tx).build();
	}
	
	@POST
	@RolesAllowed("user")
	@Consumes("application/json")
	public Response Add(AddTransaction transaction) {
		// use TransactionManager method .Add to insert a new transaction
		txManager.Add(transaction);
		return Response.ok().build();
	}
}
