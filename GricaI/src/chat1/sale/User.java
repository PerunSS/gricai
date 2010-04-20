package chat1.sale;

import org.jwebsocket.api.WebSocket;

public class User {
	private String username;
	private WebSocket webSocket;
	private ChatRoom room;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public WebSocket getWebSocket() {
		return webSocket;
	}
	public void setWebSocket(WebSocket webSocket) {
		this.webSocket = webSocket;
	}
	public void setRoom(ChatRoom room) {
		this.room = room;
	}
	public ChatRoom getRoom() {
		return room;
	}
}
