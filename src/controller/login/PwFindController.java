package controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.User;
import serivce.face.UserService;
import serivce.impl.UserServiceImpl;

@WebServlet("/pwFind")
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
		
		User user = userService.getPwParam(req);

		User pw = userService.findPw(user);
//		System.out.println(pw);
//		System.out.println("user : " + user);
//		System.out.println("userpw : " + pw.getPw());
//		User userpw = userService.getupdatePwParam(req)
//		userService.updatepw(userpw)

//		System.out.println("getpwparam: " + user);
		user.setPw(pw.getPw()); // user에는 입력한 이메일 비밀번호 이름이 들어있음.
		System.out.println( "input user : " + user);
		boolean Member = userService.login(user);
		
		req.setAttribute("USER", user);
		
		if(Member) {
			User newPw = new User();
			newPw = userService.getNewParam(req);
			newPw.setEmail(user.getEmail());
//		newPw.setPw(pw.getPw());
//			System.out.println("param : " + newPw);
			userService.updatePw(newPw);
			
			resp.sendRedirect("/login");
		} else {
			
			req.getRequestDispatcher("/WEB-INF/views/user/pwfind.jsp").forward(req, resp);
		}

	}
}
