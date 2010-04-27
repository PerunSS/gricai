package chat1.cups;

import java.util.Properties;

import org.jwebsocket.api.WebSocket;
import org.jwebsocket.api.WebSocketException;
import org.jwebsocket.api.WebSocketHandler;


public class WebSocketHandlerChups implements WebSocketHandler{

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
	public void onMessage(WebSocket socket, Object object) {
		
	}

	@Override
	public void onOpen(WebSocket socket) {
		
	}


}