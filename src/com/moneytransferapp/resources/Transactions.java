package com.moneytransferapp.resources;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.moneytransferapp.models.AddTransaction;
import com.moneytransferapp.models.GetTransactionModel;
import com.moneytransferapp.models.GetTransactionsPagedModel;
import com.moneytransferapp.services.TransactionsManager;

@Path("transactions")
public class Transactions {
	
	private int userId = 0;
	
	public Transactions() {
		
	}
	
	public Transactions(int userId) {
		this.userId = userId;
	}
	
	private TransactionsManager txManager = new TransactionsManager();
	
	// GET users/{userId}/transactions?page={page}&pageSize={pageSize} 
	
	@GET
	@RolesAllowed("user")
	@Produces({"application/json; qs=1", "application/xml; qs=0.5"})
	public Response GetAll(@QueryParam("page") int page, 
							@QueryParam("pageSize") int pageSize) {
		
		GetTransactionsPagedModel txList = new GetTransactionsPagedModel();
		if (this.userId != 0) {
			// return transaction objects for user
			txList = txManager.GetByUser(this.userId, page, pageSize);
			// transaction manager class returns the get transactions paged model
		} else {
			// get all transactions 
			// txList = txManager.
		}
		return Response.ok(txList).build();
	}
	
	@POST
	@RolesAllowed("user")
	@Consumes("application/json")
	public Response Add(AddTransaction transaction) {
		// use TransactionManager method .Add to insert a new transaction
		txManager.Add(transaction);
		return Response.ok().build();
	}
	
	@PUT
	@Path("{id}")
	@RolesAllowed("user")
	public Response UpdateComplete(@PathParam("id") int id) {
		txManager.CompleteTransaction(id);
		return Response.ok().build();
	}
}
