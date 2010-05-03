package old.chat1.sale;

import org.jwebsocket.core.server.StartWebSocketServer;

public class Starter  {

	public static void main(String[] args) {
		try{
			StartWebSocketServer.main(args);
		}catch (Exception e) {
			System.out.println("error");
			for(StackTraceElement element : e.getStackTrace()){
				System.err.println(element);
			}
			e.printStackTrace();
		}
	}
}
