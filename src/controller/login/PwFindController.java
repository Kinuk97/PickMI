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

@WebServlet("/pwfind")
public class PwFindController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/user/pwfind.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		User user = userService.getPwParam(req);

		User pw = userService.findPw(user);
//		System.out.println("user : " + user);
//		System.out.println("getpwparam: " + user);
		
		user.setPw(pw.getPw()); // user에는 입력한 이메일 비밀번호 이름이 들어있음.
//		System.out.println( "input user : " + user);
		boolean Member = userService.login(user);
//		System.out.println(Member);
		req.setAttribute("USER", user);
				
		if(Member) { // 유저 정보가 일치하면

			req.getRequestDispatcher("/WEB-INF/views/user/pwchange.jsp").forward(req, resp);
		} else { // 유저 정보가 일치하지 않는다면
			PrintWriter out = resp.getWriter();
			
			out.println("<script>alert('없는 회원입니다^^'); close(); </script>");
			out.flush();
		}

	}
}
