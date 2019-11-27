package controller.mgr;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ProjectBoard;
import serivce.face.ProjectBoardService;
import serivce.impl.ProjectBoardServiceImpl;
import util.Paging;

/**
 * Servlet implementation class MgrProjectListController
 */
@WebServlet("/mgr/projectlist")
public class MgrProjectListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProjectBoardService projectBoardService = new ProjectBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	//?š”ì²??ŒŒ?¼ë¯¸í„°?—?„œ curPageë¥? êµ¬í•˜ê³? Paging ê°ì²´ ë°˜í™˜
	Paging paging = projectBoardService.getPaging(req);
	
	//Paging ê°ì²´ë¥? modelê°’ìœ¼ë¡? ì§?? •
	req.setAttribute("paging", paging);
	
	// ProjectBoard ê²Œì‹œê¸? ëª©ë¡ ì¡°íšŒ
	List<ProjectBoard>projectlist = projectBoardService.getBoardList(paging);
	
	// projectlist ê°ì²´ë¥? modelê°’ìœ¼ë¡? ì§?? •
	req.setAttribute("list", projectlist);
		
	req.getRequestDispatcher("/WEB-INF/views/mgr/projectlist.jsp")
	.forward(req, resp);
	
	}
	
}
