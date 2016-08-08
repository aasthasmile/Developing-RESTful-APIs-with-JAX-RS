package org.aastha.java.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

import org.aastha.java.messenger.database.DatabaseClass;
import org.aastha.java.messenger.model.Message;

@XmlRootElement
public class MessageService {

	private static Map<Long,Message> messages=DatabaseClass.getMessages();

	public MessageService(){
		messages.put(1L,new Message(1,"Hello World","Aastha"));
		messages.put(2L,new Message(2,"Hello Jersey","Aastha"));
	}
	
	public List<Message> getAllMessages() {
		// TODO Auto-generated method stub
		
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(long id){
		return messages.get(id);
	}
	
	public Message addMessage(Message message){
		message.setId(messages.size() +1);
		messages.put(message.getId(),message);
		return message;
		
	}
	public Message updateMessage(Message message){
		if(message.getId()==0)
			return null;
		messages.put(message.getId(), message);
		return message;
	}
	public Message removeMessage(long id){
		return messages.remove(id);
	}
	
	public List<Message> getAllMessagesforYear(int year) {
		List<Message> messageforYear =new ArrayList<>();
		Calendar cal=Calendar.getInstance();
		for(Message message:messages.values()){
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR)== year){
				messageforYear.add(message);
			}
		}
		return messageforYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start,int size) {
		ArrayList<Message> list=new ArrayList<Message>(messages.values());
		if(start-size>list.size())return new ArrayList<Message>();
		return list.subList(start, start+size);
	}
}
