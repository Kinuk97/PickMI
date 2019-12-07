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
		
		chatter1.setChat_user((Integer) req.getSession().getAttribute("userno")); //로그인한 유저의 유저번호
		chatter2.setChat_user(Integer.parseInt(req.getParameter("userno"))); //채팅을 나눌 사용자의 유저번호
		
		System.out.println(1);
		
		System.out.println("chatter1 : " + chatter1);
		System.out.println("chatter2 : " + chatter2);
		
		int i = messageService.chattingUserCheck(chatter1, chatter2);
		
		if ( i==0) {
			System.out.println(2);
			System.out.println("error");
			
		} else {
			req.setAttribute("chat_no", i);
			System.out.println(3);
			
		}
		
		System.out.println(4);
		resp.getWriter().println("{\"chat_no\":"+chatter1.getChat_no()+"}");
		
	}
}
