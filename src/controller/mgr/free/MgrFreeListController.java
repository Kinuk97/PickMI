package controller.mgr.free;

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

		if (req.getSession().getAttribute("mgrlogin") != null) {
		
			//?���??��?��미터?��?�� curPage�? 구하�? Paging 객체 반환
			Paging paging = freeBoardService.getPaging(req);
			
			//Paging 객체�? model값으�? �??��
			req.setAttribute("paging", paging);
			
			//mgr main navTab에서 사용할 번호
			req.setAttribute("boardno", 5);
			
			
			// FreeBoard 게시�? 목록 조회
			List<FreeBoard>freelist = freeBoardService.getBoardList(paging);
			
			//freelist 객체�? model값으�? �??��
			req.setAttribute("list", freelist);
			
			req.getRequestDispatcher("/WEB-INF/views/mgr/free/freelist.jsp").forward(req, resp);
		} else {
			// 에러페이지
			req.getRequestDispatcher("/WEB-INF/views/mgr/layouts/mustlogin.jsp")
			.forward(req, resp);
		}
	}	
}
