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
import serivce.impl.MyPageServiceImpl;

/**
 * Servlet implementation class MyPwModifyController
 */
@WebServlet("/mypage/pwmodify")
public class MyPwModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	MyPageService myPageService = MyPageServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		// 세션에서 사용자 id 가져오기
	
		String email = (String) req.getSession().getAttribute("email");
		String pw = (String) req.getSession().getAttribute("pw");

		
		System.out.println(email + pw );
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/mypwmodify.jsp")
		.forward(req, resp);
		
	}
 
	
	

}
