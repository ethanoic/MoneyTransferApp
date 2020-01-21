package com.moneytransferapp.models;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "GetTransactionModel")
public class GetTransactionModel {

	@JsonProperty("amount")
	public double Amount;
	
	@JsonProperty("bankaccount")
	public String BankAccount;
	
	@JsonProperty("recipientid")
	public int recipientId;
	
	@JsonProperty("senderid")
	public int senderId;

	@JsonProperty("transactiondatetime")
	public Date transactiondatetime;
	
	@JsonProperty("status")
	public int status;
}
