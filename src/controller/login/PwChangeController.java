package controller.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User;
import serivce.face.UserService;
import serivce.impl.UserServiceImpl;


@WebServlet("/pwchange")
public class PwChangeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");

		User user = userService.getNewParam(req);
		
		user.setEmail(req.getParameter("email"));
		user.setName(req.getParameter("name"));
//		System.out.println("user2:"+user);
		
		userService.updatePw(user);
		
		PrintWriter out = resp.getWriter();
		
		out.println("<script>alert('비밀번호가 변경되었습니다^^'); close(); </script>");
		out.flush();
		
	}
}
