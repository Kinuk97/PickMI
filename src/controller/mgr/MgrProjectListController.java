package controller.mgr;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ProjectBoard;
import serivce.face.MgrService;
import serivce.face.ProjectBoardService;
import serivce.impl.MgrServiceImpl;
import serivce.impl.ProjectBoardServiceImpl;
import util.Paging;

/**
 * Servlet implementation class MgrProjectListController
 */
@WebServlet("/mgr/projectlist")
public class MgrProjectListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MgrService mgrService = new MgrServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	//?���??��?��미터?��?�� curPage�? 구하�? Paging 객체 반환
	Paging paging = mgrService.getPaging(req);
	
	// 검색어 파라미터
	paging.setSearch(req.getParameter("search"));
//	System.out.println("검색어 확인 : " + paging);
	
	//Paging 객체�? model값으�? �??��
	req.setAttribute("paging", paging);
	
	// ProjectBoard 게시�? 목록 조회
	List<ProjectBoard> list = mgrService.getPjBoardList(paging);
		
	// projectlist 객체�? model값으�? �??��
	req.setAttribute("list", list);
		
	req.getRequestDispatcher("/WEB-INF/views/mgr/projectlist.jsp")
	.forward(req, resp);
	
	}
	
}
