package old.chat1.sale.message;

import net.sf.json.JSONObject;

public class MessageFactory {
	private static String CHAT_MESSAGE = "ChatMessage";
	private static String JOIN_ROOM_MESSAGE = "JoinRoomMessage";

	public static Message createMessage(JSONObject jsonMessage){
		String type = jsonMessage.getString("type");
		Message message = createMessageObject(type);
		if(message!=null)
			message.fillMessage(jsonMessage);
		return message;
	}

	private static Message createMessageObject(String type) {
		if(type.equals(CHAT_MESSAGE))
			return new ChatMessage();
		if(type.equals(JOIN_ROOM_MESSAGE))
			return new JoinRoomMessage();
		return null;
	}
}
