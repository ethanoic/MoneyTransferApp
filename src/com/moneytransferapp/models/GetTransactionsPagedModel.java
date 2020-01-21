package com.moneytransferapp.models;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "GetTransactionsPagedModel")
public class GetTransactionsPagedModel {
	@JsonProperty("data")
	public ArrayList<GetTransactionModel> Data;
	
	@JsonProperty("next")
	public String Next;
	
	public GetTransactionsPagedModel() {
		Data = new ArrayList<GetTransactionModel>();
		Next = "";
	}
}
