package controller.mgr;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CompBoard;
import serivce.face.CompBoardService;
import serivce.impl.CompBoardServiceImpl;
import util.Paging;

/**
 * Servlet implementation class MgrCompordListController
 */
@WebServlet("/mgr/complist")
public class MgrCompListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CompBoardService compBoardService = new CompBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//?š”ì²??ŒŒ?¼ë¯¸í„°?—?„œ curPageë¥? êµ¬í•˜ê³? Paging ê°ì²´ ë°˜í™˜
		Paging paging = compBoardService.getPaging(req);
		
		 // ê²??ƒ‰?–´ ?ŒŒ?¼ë¯¸í„° 
		paging.setSearch(req.getParameter("search"));
		
		//Paging ê°ì²´ë¥? modelê°’ìœ¼ë¡? ì§?? •
		req.setAttribute("paging", paging);
		System.out.println(paging);		
		
		
		// ProfileBoard ê²Œì‹œê¸? ëª©ë¡ ì¡°íšŒ
		List<CompBoard> list = compBoardService.getBoardList(paging);
		
		// list ê°ì²´ë¥? modelê°’ìœ¼ë¡? ì§?? •
		req.setAttribute("list", list);

		
		req.getRequestDispatcher("/WEB-INF/views/mgr/complist.jsp")
		.forward(req, resp);
	
	}
	
}
