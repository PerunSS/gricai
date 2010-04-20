package chat1.sale.message;

import net.sf.json.JSONObject;


public class ChatMessage extends Message {

	private String username;
	private String messageText;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	@Override
	public String toJsonString() {
		return "{ \"type\" : \"ChatMessage\" , \"username\" : \""+username+"\" , \"text\" : \""+messageText+"\" } \r\n";
	}

	@Override
	public void fillMessage(JSONObject jsonMessage) {
		// TODO Auto-generated method stub
		
	}

}
