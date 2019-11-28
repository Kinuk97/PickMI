package controller.mgr.comp;

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
		
		//?���??��?��미터?��?�� curPage�? 구하�? Paging 객체 반환
		Paging paging = compBoardService.getPaging(req);
		
		 // �??��?�� ?��?��미터 
		paging.setSearch(req.getParameter("search"));
		
		//Paging 객체�? model값으�? �??��
		req.setAttribute("paging", paging);
//		System.out.println(paging);		
		
		
		// ProfileBoard 게시�? 목록 조회
		List<CompBoard> list = compBoardService.getBoardList(paging);
		
		// list 객체�? model값으�? �??��
		req.setAttribute("list", list);

		
		req.getRequestDispatcher("/WEB-INF/views/mgr/comp/complist.jsp")
		.forward(req, resp);
	
	}
	
}
