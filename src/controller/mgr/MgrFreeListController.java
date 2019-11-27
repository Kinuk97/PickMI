package controller.mgr;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.FreeBoard;
import serivce.face.FreeBoardService;
import serivce.impl.FreeBoardServiceImpl;
import util.Paging;

/**
 * Servlet implementation class MgrFreeListController
 */
@WebServlet("/mgr/freelist")
public class MgrFreeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	FreeBoardService freeBoardService = FreeBoardServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//?š”ì²??ŒŒ?¼ë¯¸í„°?—?„œ curPageë¥? êµ¬í•˜ê³? Paging ê°ì²´ ë°˜í™˜
		Paging paging = freeBoardService.getPaging(req);
		
		//Paging ê°ì²´ë¥? modelê°’ìœ¼ë¡? ì§?? •
		req.setAttribute("paging", paging);
		
		// FreeBoard ê²Œì‹œê¸? ëª©ë¡ ì¡°íšŒ
		List<FreeBoard>freelist = freeBoardService.getBoardList(paging);
		
		//freelist ê°ì²´ë¥? modelê°’ìœ¼ë¡? ì§?? •
		req.setAttribute("list", freelist);
		
		req.getRequestDispatcher("/WEB-INF/views/mgr/freelist.jsp").forward(req, resp);
	}
	
	
}
