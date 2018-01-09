package com.bharath.restprac.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class InvocationPractice {
	public static void main(String args[]) {
	Invocation invocation=invocationForMessagesByYear(2018);
	Response response=invocation.invoke();
	System.out.println(response.getStatus());
	
	}

	private static Invocation invocationForMessagesByYear(int year) {
		Client client=ClientBuilder.newClient();
		return client.target("http://localhost:8080/AdvancedJaxRs/webapi")
				.path("messages")
				.queryParam("year",	year)
				.request(MediaType.APPLICATION_JSON)
				.buildGet();
		
	}
}
	
