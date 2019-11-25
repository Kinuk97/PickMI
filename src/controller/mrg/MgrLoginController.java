package controller.mrg;

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
		
		// VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/mgr/mgrlogin.jsp")
		.forward(req, resp);

		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
		
		// 로그인 정보 얻어오기
		Manager mgr = mgrService.getLoginMgr(req);
		
		
		// 로그인 인증
		boolean login = mgrService.login(mgr);
		
		if(login == true) { // 로그인 됐을 경우
			
			// 로그인한 관리자 정보 얻어오기
			mgr = mgrService.getMgrByMgrid(mgr);
			
			//세션정보 저장
			req.getSession().setAttribute("login", login);
			req.getSession().setAttribute("mgrid", mgr.getMgrid());
			
			
			//////////////////////////// sendRedirect 관리자 페이지 첫 화면 // 첫화면 뭐인지..
			resp.sendRedirect("/mgr/list");
			////////////////////////////
			
		} else { // 로그인 실패시
			System.out.println("로그인 실패");
			req.setAttribute("login", login); // 이거 왜 ? 로그인 실패인데 세션저장?
			req.getRequestDispatcher("/WEB-INF/views/mgr/mgrlogin.jsp")
			.forward(req, resp);
		}
	}
}
