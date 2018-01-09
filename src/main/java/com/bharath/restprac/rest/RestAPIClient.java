package com.bharath.restprac.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bharath.restprac.Messenger.model.Message;

public class RestAPIClient {
	public static void main(String[] args) {
		Client client=ClientBuilder.newClient();
		WebTarget baseTarget = client.target("http://localhost:8080/AdvancedJaxRs/webapi");
		WebTarget messagesTarget = baseTarget.path("messages");
		WebTarget singleMessageTarget = messagesTarget.path("{messageId}");
		
		Message message1=singleMessageTarget.resolveTemplate("messageId", "1")
				.request(MediaType.APPLICATION_JSON)
				.get(Message.class);
		Message message2=singleMessageTarget.resolveTemplate("messageId", "2")
				.request(MediaType.APPLICATION_JSON)
				.get(Message.class);
		//Message message=response.readEntity(Message.class);
		System.out.println(message1.getMessage());
		System.out.println(message2.getMessage());
		
		Message newMessage=new Message(4, "Hello Hello", "ME and Myself");
		Response postResponse=messagesTarget
								.request()
								.post(Entity.json(newMessage));
		Message createdMessage=postResponse.readEntity(Message.class);
		System.out.println(createdMessage.getMessage());

	}
}
