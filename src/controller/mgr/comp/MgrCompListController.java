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
		
		// 관리자 로그인 됐을 경우
		if (req.getSession().getAttribute("mgrlogin") != null) {
			
			Paging paging = compBoardService.getPaging(req);
			
			paging.setSearch(req.getParameter("search"));
			
			//mgr main navTab에서 사용할 번호
			req.setAttribute("boardno", 4);
			
			//Paging 객체 모델값으로 전달
			req.setAttribute("paging", paging);
			
			//페이징에 필요한 url 전달	
			req.setAttribute("url", req.getRequestURL());
			
//		System.out.println(paging);		
			
			
			// ProfileBoard 게시물 목록 조회
			List<CompBoard> list = compBoardService.getBoardList(paging);
			
			// list 객체를 모델값으로 전달
			req.setAttribute("list", list);
						
			req.getRequestDispatcher("/WEB-INF/views/mgr/comp/complist.jsp")
			.forward(req, resp);
			
		} else {
			// 관리자 로그인 안됐을 경우
			req.getRequestDispatcher("/WEB-INF/views/mgr/layouts/mustlogin.jsp")
			.forward(req, resp);
		}
	
	}
	
}
