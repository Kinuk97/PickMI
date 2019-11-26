package controller.mrg;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User;
import serivce.face.MgrService;
import serivce.impl.MgrServiceImpl;
import util.Paging;

/**
 * Servlet implementation class MgrUserListController
 */
@WebServlet("/mgr/userlist")
public class MgrUserListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MgrService mgrService = new MgrServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 요청파라미터에서 curPage를 구하고 Paging 객체 반환
		Paging paging = mgrService.getPaging(req);
				
		// paging 객체를 MODEL값으로 지정
		req.setAttribute("paging", paging);

		// 검색어 파라미터 
		paging.setSearch(req.getParameter("search"));

		//ProfileBoard 테이블의 목록 조회
		List<User> userlist = mgrService.getuserList(paging);

		//게시글 목록을 MODEL값으로 지정
		req.setAttribute("userlist", userlist);
		
		req.getRequestDispatcher("/WEB-INF/views/mgr/userlist.jsp")
		.forward(req, resp);

		}
	
}
