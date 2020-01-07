package com.moneytransferapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddTransaction {
	
	@JsonProperty("amount")
	public double Amount;
	
	@JsonProperty("bankaccount")
	public String BankAccount;
	
	@JsonProperty("recipientid")
	public int recipientId;
	
	@JsonProperty("senderid")
	public int senderId;
}
