package com.moneytransferapp.auth.jwt;

public class jwtIssuer {
	public static String getId() {
		return "moneytransferapp";
	}
	public static String getIssuer() {
		return "moneytransfer.app"; // change to your own domain name
	}
}
