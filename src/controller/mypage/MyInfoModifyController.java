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
 * Servlet implementation class MyPwModifyController
 */
@WebServlet("/mypage/infomodify")
public class MyInfoModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MyPageService myPageService = MyPageServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
        String email = (String) req.getSession().getAttribute("email");
        String pw = (String) req.getSession().getAttribute("pw");
        String name = (String) req.getSession().getAttribute("name");
        myPageService.userUpdate(email, pw, name);
		User userinfo = myPageService.getUser(email);
		req.setAttribute("userinfo", userinfo);

        resp.sendRedirect("/main");
        return;

		
	}

}
