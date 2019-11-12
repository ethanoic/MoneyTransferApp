package com.moneytransferapp.auth.jwt;

import java.lang.reflect.Method;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.moneytransferapp.services.AuthManager;

public class JWTFilter implements javax.ws.rs.container.ContainerRequestFilter {
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
			String authKey = requestContext.getHeaderString("Authorization");
			if (authKey != null) {
				String token = authKey.split(" ")[1];
				// call method from authmanager .isTokenValid
				if (!JWTToken.validateJWT(token)) {
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
