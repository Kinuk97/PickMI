package controller.mgr;

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

	private MgrService mgrService = MgrServiceImpl.getInstance();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 로그인 안됐을 경우
		if (req.getSession().getAttribute("mgrlogin") != null ) {
		
			// 요청파라미터에서 curPage를 구하고 Paging 객체 반환
			Paging paging = mgrService.getPaging(req);
			
			// paging 객체를 MODEL값으로 지정
			req.setAttribute("paging", paging);
			
			// mgr main navTab에서 사용할 번호
			req.setAttribute("boardno", 6);
			
			// 검색어 파라미터
			paging.setSearch(req.getParameter("search"));
			
			// 유저 목록 조회
			List<User> userlist = mgrService.getuserList(paging);
			
			// mgr/user/userlist에 모델 (MODEL = userlist) 값 전달하기
			req.setAttribute("userlist", userlist);
			
			req.getRequestDispatcher("/WEB-INF/views/mgr/user/userlist.jsp")
			.forward(req, resp);	
		} else {
			// 로그인 안됐을 경우
			req.getRequestDispatcher("/WEB-INF/views/mgr/layouts/mustlogin.jsp")
			.forward(req, resp);
		}
	}	
}
