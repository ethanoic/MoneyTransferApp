package com.moneytransferapp.models.v2;

import java.util.ArrayList;

import com.moneytransferapp.models.GetUserProfileModel;
import com.moneytransferapp.models.Link;

public class GetUserModel {
	public GetUserProfileModel profile;
	public ArrayList<Link> links;
	public GetUserModel() {
		profile = new GetUserProfileModel();
		links = new ArrayList();
	}
}
