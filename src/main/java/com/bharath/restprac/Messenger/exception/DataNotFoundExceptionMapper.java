package com.bharath.restprac.Messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.bharath.restprac.Messenger.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage errorMessage=new ErrorMessage(ex.getMessage(), 404, "http://www.govet.org");
		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
		 
	}
	

}