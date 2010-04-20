package chat1.sale;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.jwebsocket.api.WebSocket;
import org.jwebsocket.api.WebSocketException;
import org.jwebsocket.api.WebSocketHandler;

import chat1.sale.message.ChatMessage;
import chat1.sale.message.JoinRoomMessage;
import chat1.sale.message.Message;
import chat1.sale.message.MessageFactory;

public class WebSocketHandlerSale implements WebSocketHandler {

	private Map<String, ChatRoom> rooms = new HashMap<String, ChatRoom>();
	private Map<String, User> users = new HashMap<String, User>();

	@Override
	public void init(Properties prop) {

	}

	@Override
	public void onClose(WebSocket socket) {
		try {
			socket.close();
		} catch (WebSocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onException(WebSocket socket, Throwable throwable) {

	}

	@Override
	public void onMessage(WebSocket socket, Object message) {
		JSONObject jsonMessage = (JSONObject) message;
		Message msg = MessageFactory.createMessage(jsonMessage);
		if(msg instanceof JoinRoomMessage){
			joinRoom(socket, msg);
		}else if(msg instanceof ChatMessage){
			sendChat(msg);
		}
	}
	
	@Override
	public void onOpen(WebSocket socket) {

	}	

	private void joinRoom(WebSocket socket, Message msg) {
		JoinRoomMessage joinRoomMessage = (JoinRoomMessage)msg;
		ChatRoom room = rooms.get(joinRoomMessage.getRoomName());
		if(room == null){
			room = new ChatRoom();
			room.setRoomName(joinRoomMessage.getRoomName());
		}
		User user = new User();
		user.setUsername(joinRoomMessage.getUsername());
		user.setWebSocket(socket);
		room.addUser(user);
	}

	private void sendChat(Message msg) {
		ChatMessage chatMessage = (ChatMessage) msg;
		User user = users.get(chatMessage.getUsername());
		user.getRoom().recieveMessage(chatMessage);
	}
	

}
