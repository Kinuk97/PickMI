package controller.Message;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ws/msg")
public class MessageController {
	public static int cnt = 5;
	@OnOpen
	public void onOpen() {
		System.out.println("opened");
	}
	
	@OnClose
	public void onClose() {
		System.out.println("closed");
	}
	
	@OnMessage
	public String onMessage(String msg, Session session)  {
		System.out.println(msg);
		if("check".equals(msg)) {
			System.out.println("check");
			return "{\"cnt\":"+ cnt++ +"}";
		}
		
		return null;
		
	}
	
	@OnError
	public void onError(Session session, Throwable th) {
		System.out.println("error");
	}
}
