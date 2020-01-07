package com.moneytransferapp.models;

import java.util.ArrayList;

public class GetUserModel {
	public GetUserProfileModel Profile;
	public ArrayList<GetTransactionModel> Transactions;
	public ArrayList<GetContactModel> Contacts;
	
	public GetUserModel() {
		this.Profile = new GetUserProfileModel();
		this.Transactions = new ArrayList();
		this.Contacts = new ArrayList();
	}
}
