package com.moneytransferapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CreateUser")
public class CreateUser {
	
	@JsonProperty("name")
	public String Name;
	
	@JsonProperty("email")
	public String Email;
	
	@JsonProperty("mobile")
	public String Mobile;
	
	@JsonProperty("password")
	public String Password;
}
