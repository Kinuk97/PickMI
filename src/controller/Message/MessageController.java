package controller.Message;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Chat;
import serivce.face.MessageService;
import serivce.impl.MessageServiceImpl;

@WebServlet("/msg/chatting")
public class MessageController extends HttpServlet {

	private MessageService messageService = new MessageServiceImpl();
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Chat chat = new Chat();
		chat.setChat_msg(req.getParameter("chat_msg"));
		chat.setChat_sender((int)req.getSession().getAttribute("userno")); 
//		System.out.println(Integer.parseInt(req.getParameter("chat_no")));
		
		try {
			chat.setChat_no(Integer.parseInt(req.getParameter("chat_no"))); //채팅방 생성을 하기 전에 채팅방이 없는 경우에도 채팅방이 있는지 찾아서 null이 나옴
		} catch (NumberFormatException e) {
			// 채팅방이 없는 경우
		}
		
		List<Chat> chattingList = messageService.getChattingList(chat);
		req.setAttribute("chattingList", chattingList);
		
		req.getRequestDispatcher("/WEB-INF/views/msg/chatlist.jsp").forward(req, resp);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Chat chat = new Chat();
		chat.setChat_msg(request.getParameter("chat_msg"));
		chat.setChat_sender((int)request.getSession().getAttribute("userno")); 
		chat.setChat_no(Integer.parseInt(request.getParameter("chat_no")));

		System.out.println("chat_msg : " + request.getParameter("chat_msg"));
		System.out.println("userno : " + (int)request.getSession().getAttribute("userno"));
		System.out.println("chat_no : " + request.getParameter("chat_no"));
		
		messageService.insert(chat);
		
		List<Chat> chattingList = messageService.getChattingList(chat);
		request.setAttribute("chattingList", chattingList);
		
		request.getRequestDispatcher("/WEB-INF/views/msg/chatlist.jsp").forward(request, response);
		
	}

}
