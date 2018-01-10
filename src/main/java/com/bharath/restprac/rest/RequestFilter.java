package com.bharath.restprac.rest;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
public class RequestFilter implements ContainerRequestFilter {
	
	private static final String AUTHORIZATION_HEADER_KEY="Authorization";
	private static final String AUTHORIZATION_CLIP_VALUE="Basic ";
	private static final String SECURITY_STRING="secured";
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if(requestContext.getUriInfo().getPath().contains(SECURITY_STRING)) {

			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			if(authHeader.size()>0) {
				String authToken = authHeader.get(0);
				String baseCodedToken = authToken.replaceFirst(AUTHORIZATION_CLIP_VALUE, "");
				String decodedString = Base64.decodeAsString(baseCodedToken);
				StringTokenizer splitter=new StringTokenizer(decodedString, ":");
				String username=splitter.nextToken();
				String password=splitter.nextToken();
				if(username.equals("user") && password.equals("pass")) {
					return;
				}
			}
			Response unAuthResponse=Response
									.status(Response.Status.UNAUTHORIZED)
									.entity("User not Authorized")
									.build();
			requestContext.abortWith(unAuthResponse);

		}
	}

}
