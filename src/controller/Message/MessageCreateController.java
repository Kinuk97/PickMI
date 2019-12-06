package controller.Message;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Chat;
import dto.Chatter;
import serivce.face.MessageService;
import serivce.impl.MessageServiceImpl;

/**
 * Servlet implementation class MessageCreateController
 */
@WebServlet("/msg/create")
public class MessageCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MessageService messageService = new MessageServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Chatter chatter1 = new Chatter(); //나
		Chatter chatter2 = new Chatter(); //상대방
		
		//1. 지금 대화중인 방이 있는지 확인한다 ( 대화 상대 번호 필요 )
		//2. 적절한 방 번호를 찾는다
		//	( 존재하는 방 또는 새로운 방 )
		
		int val = messageService.selectNextChatno();
		
		chatter1.setChat_user((Integer) req.getSession().getAttribute("userno")); //로그인한 유저의 유저번호
		chatter2.setChat_user(Integer.parseInt(req.getParameter("userno"))); //채팅을 나눌 사용자의 유저번호

		chatter1.setChat_no(val);
		chatter2.setChat_no(val);
		messageService.makeRoom(chatter1, chatter2);
		
		resp.getWriter().println("{\"chat_no\":"+chatter1.getChat_no()+"}");
	}
}
