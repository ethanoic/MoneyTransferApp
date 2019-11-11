package com.moneytransferapp.application;
import org.glassfish.jersey.server.ResourceConfig;

public class MoneyTransferApplication extends ResourceConfig {
	public MoneyTransferApplication() {
		packages("com.moneytransferapp.resources");
		register(com.moneytransferapp.application.MyJacksonFeature.class);
		register(com.moneytransferapp.auth.ApiKeyFilter.class);
	}
}
