package controller.join;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User;
import serivce.face.UserService;
import serivce.impl.UserServiceImpl;


@WebServlet("/emailCheck")
public class EmailCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserService userService = new UserServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = userService.getJoinParam(req);
		
		boolean checkResult = userService.emailCheck(user);
		
		if(checkResult == false) {
			req.setAttribute("email", checkResult);
		}else {
			resp.sendRedirect("/insert");
		}
	}
}
