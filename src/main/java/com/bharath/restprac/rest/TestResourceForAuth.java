package com.bharath.restprac.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/secured")
public class TestResourceForAuth {
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String getTest() {
		return "Hey Tested";
	}
}
