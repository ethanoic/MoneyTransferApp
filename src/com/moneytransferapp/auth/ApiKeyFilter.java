package com.moneytransferapp.auth;

import java.lang.reflect.Method;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.moneytransferapp.services.AuthManager;

@Provider
public class ApiKeyFilter implements javax.ws.rs.container.ContainerRequestFilter {
	
	@Context
    private ResourceInfo resourceInfo;
	
	private AuthManager authManager = new AuthManager();
	
	@Override
    public void filter(ContainerRequestContext requestContext)
    {
		// check if resource method requested has a rolesAllowed "user"
		Method method = resourceInfo.getResourceMethod();
		if (method.isAnnotationPresent(RolesAllowed.class)) {
			// extract token from header "auth"
			String authKey = requestContext.getHeaderString("Auth");
			if (authKey != null) {
				// call method from authmanager .isTokenValid
				if (!authManager.isTokenValid(authKey)) {
					// return 403 forbidden if token is invalid
					requestContext.abortWith(Response.status(Status.FORBIDDEN).build());
					return;
				}	
			} else {
				requestContext.abortWith(Response.status(Status.FORBIDDEN).build());
				return;
			}
			
		}
		
    }
}
