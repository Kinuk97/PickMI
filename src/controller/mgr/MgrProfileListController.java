package controller.mgr;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ProfileBoard;
import serivce.face.ProfileBoardService;
import serivce.impl.ProfileBoardServiceImpl;
import util.Paging;

/**
 * Servlet implementation class MgrProfileListController
 */
@WebServlet("/mgr/profilelist")
public class MgrProfileListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ProfileBoardService profileBoardService = new ProfileBoardServiceImpl();
	
//	CompBoardService compBoard = new CompBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		//?š”ì²??ŒŒ?¼ë¯¸í„°?—?„œ curPageë¥? êµ¬í•˜ê³? Paging ê°ì²´ ë°˜í™˜
		Paging paging = profileBoardService.getPaging(req);
		
		 // ê²??ƒ‰?–´ ?ŒŒ?¼ë¯¸í„° 
		paging.setSearch(req.getParameter("search"));
		
		//Paging ê°ì²´ë¥? modelê°’ìœ¼ë¡? ì§?? •
		req.setAttribute("paging", paging);
		System.out.println(paging);		
		
		
		// ProfileBoard ê²Œì‹œê¸? ëª©ë¡ ì¡°íšŒ
		List<ProfileBoard> list = profileBoardService.getBoardList(paging);
		
		// list ê°ì²´ë¥? modelê°’ìœ¼ë¡? ì§?? •
		req.setAttribute("list", list);
		
//		System.out.println("profile list controller : " + list);
		//view ë³´ë‚´ê¸?
		req.getRequestDispatcher("/WEB-INF/views/mgr/profilelist.jsp").forward(req, resp);

	}
	
}
