package org.aastha.java.messenger.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlRootElement;

import org.aastha.java.messenger.model.Message;
import org.aastha.java.messenger.service.MessageService;

@XmlRootElement
@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResources {
	
	MessageService messageService =new MessageService();
	
	@GET
	public List<Message> getMesssages(@BeanParam MessageFilterBean filterBean){
		if(filterBean.getYear()>0){
			return messageService.getAllMessagesforYear(filterBean.getYear());
		}
		if(filterBean.getStart()>=0 && filterBean.getSize()>=0){
			return messageService.getAllMessagesPaginated(filterBean.getStart(),filterBean.getSize());
		}
		return messageService.getAllMessages(); 
	}
	
	@POST
	public Response addMessage(Message message){
		return Response.status(Status.CREATED)
		.entity(messageService.addMessage(message))
		.build();
		//to return instance of response
		//return messageService.addMessage(message);
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message){
		message.setId(id);
		return messageService.updateMessage(message);
	}
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") long id){
		messageService.removeMessage(id);
	}
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id){
		return messageService.getMessage(id);
		
	}
	
//	@GET
//	@Path("/{messageId}")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String test(){
//		int i=0;
//		return "test"+(i++);
//	}
	
	
}
