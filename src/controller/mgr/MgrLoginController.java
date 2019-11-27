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

	// ManagerService Í∞ùÏ≤¥
	MgrService mgrService = new MgrServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// VIEW Ïß??†ï
		req.getRequestDispatcher("/WEB-INF/views/mgr/mgrlogin.jsp")
		.forward(req, resp);

		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
		
		// Î°úÍ∑∏?ù∏ ?†ïÎ≥? ?ñª?ñ¥?ò§Í∏?
		Manager mgr = mgrService.getLoginMgr(req);
		
		
		// Î°úÍ∑∏?ù∏ ?ù∏Ï¶?
		boolean login = mgrService.login(mgr);
		
		if(login == true) { // Î°úÍ∑∏?ù∏ ?êê?ùÑ Í≤ΩÏö∞
			
			// Î°úÍ∑∏?ù∏?ïú Í¥?Î¶¨Ïûê ?†ïÎ≥? ?ñª?ñ¥?ò§Í∏?
			mgr = mgrService.getMgrByMgrid(mgr);
			
			//?Ñ∏?Öò?†ïÎ≥? ???û•
			req.getSession().setAttribute("login", login);
			req.getSession().setAttribute("mgrid", mgr.getMgrid());
			
			
			//////////////////////////// sendRedirect Í¥?Î¶¨Ïûê ?éò?ù¥Ïß? Ï≤? ?ôîÎ©? // Ï≤´ÌôîÎ©? Î≠êÏù∏Ïß?..
			resp.sendRedirect("/mgr/main");
			////////////////////////////
			
		} else { // Î°úÍ∑∏?ù∏ ?ã§?å®?ãú
			System.out.println("Î°úÍ∑∏?ù∏ ?ã§?å®");
			req.setAttribute("login", login); // ?ù¥Í±? ?ôú ? Î°úÍ∑∏?ù∏ ?ã§?å®?ù∏?ç∞ ?Ñ∏?Öò???û•?
			req.getRequestDispatcher("/WEB-INF/views/mgr/mgrlogin.jsp")
			.forward(req, resp);
		}
	}
}
