package controller.mgr.project;

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
	
	private ProjectBoardService projectBoardService = ProjectBoardServiceImpl.getInstance(); 
	private MgrService mgrService = MgrServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 로그인 안됐을 경우
		if (req.getSession().getAttribute("mgrlogin") != null ) {
		
			//?���??��?��미터?��?�� curPage�? 구하�? Paging 객체 반환
			Paging paging = mgrService.getPaging(req, 2);
			
			// 검색어 파라미터
			paging.setSearch(req.getParameter("search"));
//	System.out.println("검색어 확인 : " + paging);
			
			//Paging 객체�? model값으�? �??��
			req.setAttribute("paging", paging);
			
			//mgr main navTab에서 사용할 번호
			req.setAttribute("boardno", 3);
			
			// ProjectBoard 게시�? 목록 조회
			List<ProjectBoard> list = mgrService.getPjBoardList(paging);
			
			// projectlist 객체�? model값으�? �??��
			req.setAttribute("list", list);
			
			req.getRequestDispatcher("/WEB-INF/views/mgr/project/projectlist.jsp")
			.forward(req, resp);
			
		} else {
			// 로그인 안됐을 경우
			req.getRequestDispatcher("/WEB-INF/views/mgr/layouts/mustlogin.jsp")
			.forward(req, resp);
		}
	}	
}



