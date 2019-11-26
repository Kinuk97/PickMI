package controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import serivce.face.UserService;
import serivce.impl.UserServiceImpl;


@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// login폼 띄우기(view)
		req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 세션 객체 얻기
		HttpSession session = req.getSession();
		
		// 로그인에 필요한 파라미터 받기
		User user = userService.getLoginParam(req);
		System.out.println("email, pw : " + user);
		
		// 로그인 확인
		boolean login = userService.login(user);
		
		if(login == true) {
			String eamil = req.getParameter("eamil");
			String pw = req.getParameter("pw");
			String userno = req.getParameter("userno");
			
			session.setAttribute("login", true);
			session.setAttribute("email", eamil);
			session.setAttribute("userno", userno);
			
			resp.sendRedirect("/main");
		} else {
			resp.sendRedirect("/login");
		}
	}

}
