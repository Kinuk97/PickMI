package controller.Message;

import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import dao.face.MessageDao;
import dao.impl.MessageDaoImpl;
import dto.Chat;
import dto.Chatter;
import dto.User;
import serivce.face.MessageService;
import serivce.impl.MessageServiceImpl;

@ServerEndpoint("/ws/msg")
public class MessageWebSocketController {
	
	private MessageService messageService = new MessageServiceImpl();

	public static int cnt = 5;
	
	//웹소켓이 연결되면 호출되는 이벤트
	@OnOpen
	public void onOpen() {
		System.out.println("opened");
	}
	
	//웹 소켓이 닫히면 호출되는 이벤트
	@OnClose
	public void onClose() {
		System.out.println("closed");
	}
	
	
	@OnMessage
	public String onMessage(String msg, Session session)  {

		Gson gson = new Gson();
		
		JsonElement jsonElement = JsonParser.parseString(msg);
		String type = jsonElement.getAsJsonObject().get("type").toString().replace("\"", "");
//		System.out.println(type);
		
		if( "list".equals(type) ) {
			Chatter chatter = gson.fromJson(msg, Chatter.class);
			System.out.println(chatter);

//			List<Chat> chatList = messageService.getChatList();
			List<Chat> chatList = messageService.getLastChatList(chatter);
			System.out.println( chatList );
			
			
			return new Gson().toJson(chatList);
		}
		
		if( "msg".equals(type) ) {
			System.out.println("msg");
			
			Chatter chatter = gson.fromJson(msg, Chatter.class);
			System.out.println(chatter);
			
		}
		
		if( "search".equals(type)) {
			
			User user = gson.fromJson(msg, User.class);
//			System.out.println(user);
			String search = null;
			List<User> searchList = messageService.getSearchList(search, user);
//			System.out.println("searchList : " + searchList);
			
			return new Gson().toJson(searchList);
			
		}
		
		return null;
	}
	
	@OnError
	public void onError(Session session, Throwable th) {
		System.out.println("error : " + th);
	}
}
