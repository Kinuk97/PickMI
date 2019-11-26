package controller.mrg;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ProfileBoard;
import serivce.face.MgrService;
import serivce.impl.MgrServiceImpl;
import util.Paging;

/**
 * Servlet implementation class MgrMain
 */
@WebServlet("/mgr/pblist")
public class MgrProfileListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MgrService mgrService = new MgrServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//로그인 세션정보 확인
//		HttpSession s = req.getSession();		
//		System.out.println(s.getAttribute("login"));
//		System.out.println(s.getAttribute("mgrid"));
		
		// 요청파라미터에서 curPage를 구하고 Paging 객체 반환
		Paging paging = mgrService.getPaging(req);
//		System.out.println("ProfileListController - " + paging);
		
		// 검색어 파라미터 
		paging.setSearch(req.getParameter("search"));
//		System.out.println(paging);
		
		// paging 객체를 MODEL값으로 지정
		req.setAttribute("paging", paging);
		System.out.println("paging : " + paging);
		
		//ProfileBoard 테이블의 목록 조회
		List<ProfileBoard> pbList = mgrService.getpbList(paging);
		
		//게시글 목록을 MODEL값으로 지정
		req.setAttribute("pblist", pbList);
		
		req.getRequestDispatcher("/WEB-INF/views/mgr/pblist.jsp")
		.forward(req, resp);
		
		
		
	}
	
}
