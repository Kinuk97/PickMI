package controller.mgr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MgrMainController
 */
@WebServlet("/mgr/main")
public class MgrMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 로그인 안됐을 경우
		if (req.getSession().getAttribute("mgrlogin") != null ) {
		
			//mgrmain.jsp navTab에서 사용할 번호
			req.setAttribute("boardno", 1);
			
			req.getRequestDispatcher("/WEB-INF/views/mgr/mgrmain.jsp")
			.forward(req, resp);
				
		} else {
			// 로그인 안됐을 경우
			req.getRequestDispatcher("/WEB-INF/views/mgr/layouts/mustlogin.jsp")
			.forward(req, resp);
		}
	}
}
