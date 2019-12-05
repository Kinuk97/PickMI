package controller.mypage;

import java.io.IOException;
import java.io.PrintWriter;

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
		
		HttpSession session = req.getSession();
		User user = new User();
		
		user.setEmail(session.getAttribute("email").toString());
		user.setName(session.getAttribute("name").toString());
		
		userService.findPw(user);
		
		req.setAttribute("user", user);
		// 비밀번호 수정 폼 띄우기	
		req.getRequestDispatcher("/WEB-INF/views/mypage/mypwmodify.jsp")
		.forward(req, resp);		
	}
 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = req.getSession();
	
		User pwparam = myPageService.getcurPwParam(req);
		
		pwparam.setUserno((Integer) session.getAttribute("userno"));  
		pwparam.setEmail((String) session.getAttribute("email"));
		
		System.out.println(pwparam);
		
		boolean curpw = myPageService.eqPW(pwparam);
		
		if(curpw) { // 비밀번호가 일치하면
			pwparam.setPw(req.getParameter("pw1")); //파라미터(req) pw값 수정할 비밀번호로 바꿔주기
			myPageService.modifyPw(pwparam); // 새로운비밀번호
			System.out.println();			
			resp.sendRedirect("/mypage");
		} else {
			// 비밀번호가 일치하지 않는다면
			
			resp.sendRedirect("/mypage/pwmodify");
		}
	}
}
