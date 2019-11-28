package controller.mgr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Manager;
import serivce.face.MgrService;
import serivce.impl.MgrServiceImpl;

/**
 * Servlet implementation class MgrLoginController
 */
@WebServlet("/mgr/login")
public class MgrLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ManagerService 객체
	MgrService mgrService = new MgrServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//로그인 ON
		if (req.getSession().getAttribute("mgrlogin") != null ) {
		
			resp.sendRedirect("/mgr/main");
		} else {
			//로그인 OFF
			// VIEW �??��
			req.getRequestDispatcher("/WEB-INF/views/mgr/mgrlogin.jsp")
			.forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
		
		// 로그?�� ?���? ?��?��?���?
		Manager mgr = mgrService.getLoginMgr(req);
		
		
		// 로그?�� ?���?
		boolean login = mgrService.login(mgr);
		
		if(login == true) { // 로그?�� ?��?�� 경우
			
			// 로그?��?�� �?리자 ?���? ?��?��?���?
			mgr = mgrService.getMgrByMgrid(mgr);
			
			//?��?��?���? ???��
			req.getSession().setAttribute("mgrlogin", login);
			req.getSession().setAttribute("mgrid", mgr.getMgrid());
			
			
			//////////////////////////// sendRedirect �?리자 ?��?���? �? ?���? // 첫화�? 뭐인�?..
			resp.sendRedirect("/mgr/main");
			////////////////////////////
			
		} else { // 로그?�� ?��?��?��
//			System.out.println("로그?�� ?��?��");
			req.setAttribute("login", login); // ?���? ?�� ? 로그?�� ?��?��?��?�� ?��?��???��?
			req.getRequestDispatcher("/WEB-INF/views/mgr/mgrlogin.jsp")
			.forward(req, resp);
		}
	}
}
