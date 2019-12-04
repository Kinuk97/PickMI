package controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import serivce.face.MyPageService;
import serivce.face.UserService;
import serivce.impl.MyPageServiceImpl;
import serivce.impl.UserServiceImpl;

/**
 * Servlet implementation class MyPwModifyController
 */
@WebServlet("/mypage/pwmodify")
public class MyPwModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	MyPageService myPageService = MyPageServiceImpl.getInstance();
	UserService userService = new UserServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
//		HttpSession session = req.getSession();
//		//PW 가져오기		
//		User user = myPageService.getUser((String)session.getAttribute("email"));
//		System.out.println(user);	
//		req.setAttribute("user", user);

		// 비밀번호 수정 폼 띄우기	
		req.getRequestDispatcher("/WEB-INF/views/mypage/mypwmodify.jsp")
		.forward(req, resp);
		
	}
 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
//		// 비밀번호 수정에 필요한 파라미터 받기
//		User user = userService.getLoginParam(req);
//		System.out.println(user);	
		
		HttpSession session = req.getSession();
		
		// 현재 로그인 되어진 아이디의 비밀번호 가져오기
//		User curpw = myPageService.getUser((String)session.getAttribute("email"));
//		System.out.println(pw); //pw 나옴
		
		// 세션을 통한 현재 로그인된 사용자정보  가져오기
		User curpw = myPageService.getUser((String)session.getAttribute("email"));
		// 비밀번호 수정에 필요한 파라미터 받기
		User user = myPageService.getPwParam(req);
		// user객체에 userno담아주기
		user.setUserno(curpw.getUserno());
		user.setPw(curpw.getPw());
		System.out.println(curpw);
		System.out.println("user : " + user);		// 비밀번호 확인

//		boolean canmodify = myPageService.modifyPw(user);
		
		
	}
}
