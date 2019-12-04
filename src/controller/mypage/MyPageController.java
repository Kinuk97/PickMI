package controller.mypage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User;
import serivce.face.MyPageService;
import serivce.impl.MyPageServiceImpl;

/**
 * Servlet implementation class MyPageController
 */
@WebServlet("/mypage")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MyPageService myPageService = MyPageServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 세션에서 현재 로그인 돼 있는 email 가져오기
		String email = (String) req.getSession().getAttribute("email");
		
//		System.out.println(email);
		
		// email에 해당하는 회원정보 가져오기
		User userinfo = myPageService.getUser(email);
		
		req.setAttribute("userinfo", userinfo);

		req.getRequestDispatcher("/WEB-INF/views/mypage/mpmain.jsp")
		.forward(req, resp);
	
	}
	
}
