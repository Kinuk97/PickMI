package controller.mgr;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User;
import serivce.face.MgrService;
import serivce.impl.MgrServiceImpl;
import util.Paging;

/**
 * Servlet implementation class MgrUserListController
 */
@WebServlet("/mgr/userlist")
public class MgrUserListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MgrService mgrService = new MgrServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// ?š”ì²??ŒŒ?¼ë¯¸í„°?—?„œ curPageë¥? êµ¬í•˜ê³? Paging ê°ì²´ ë°˜í™˜
		Paging paging = mgrService.getPaging(req);
				
		// paging ê°ì²´ë¥? MODELê°’ìœ¼ë¡? ì§?? •
		req.setAttribute("paging", paging);

		// ê²??ƒ‰?–´ ?ŒŒ?¼ë¯¸í„° 
		paging.setSearch(req.getParameter("search"));

		//ProfileBoard ?…Œ?´ë¸”ì˜ ëª©ë¡ ì¡°íšŒ
		List<User> userlist = mgrService.getuserList(paging);

		//ê²Œì‹œê¸? ëª©ë¡?„ MODELê°’ìœ¼ë¡? ì§?? •
		req.setAttribute("userlist", userlist);
		
		req.getRequestDispatcher("/WEB-INF/views/mgr/userlist.jsp")
		.forward(req, resp);

		}
	
}
