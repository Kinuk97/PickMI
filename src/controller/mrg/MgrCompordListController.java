package controller.mrg;

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
public class MgrCompordListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CompBoardService compBoardService = new CompBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청파라미터에서 curPage를 구하고 Paging 객체 반환
		Paging paging = compBoardService.getPaging(req);
		
		 // 검색어 파라미터 
		paging.setSearch(req.getParameter("search"));
		
		//Paging 객체를 model값으로 지정
		req.setAttribute("paging", paging);
		System.out.println(paging);		
		
		
		// ProfileBoard 게시글 목록 조회
		List<CompBoard> list = compBoardService.getBoardList(paging);
		
		// list 객체를 model값으로 지정
		req.setAttribute("list", list);

		
		req.getRequestDispatcher("/WEB-INF/views/mgr/complist.jsp")
		.forward(req, resp);
	
	}
	
}
