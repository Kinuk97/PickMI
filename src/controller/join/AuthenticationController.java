package controller.join;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/authentic")
public class AuthenticationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.getRequestDispatcher("WEB-INF/views/user/mail.jsp").forward(req, resp);
//		
//	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("WEB-INF/views/user/mail.jsp").forward(req, resp);

//		resp.sendRedirect("/login");

	}

}
