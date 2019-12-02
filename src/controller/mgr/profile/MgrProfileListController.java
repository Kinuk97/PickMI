package controller.mgr.profile;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ProfileBoard;
import serivce.face.MgrService;
import serivce.face.ProfileBoardService;
import serivce.impl.MgrServiceImpl;
import serivce.impl.ProfileBoardServiceImpl;
import util.Paging;

/**
 * Servlet implementation class MgrProfileListController
 */
@WebServlet("/mgr/profilelist")
public class MgrProfileListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ProfileBoardService profileBoardService = ProfileBoardServiceImpl.getInstance();
	private MgrService mgrService = MgrServiceImpl.getInstance();

	
//	CompBoardService compBoard = new CompBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 안됐을 경우
		if (req.getSession().getAttribute("mgrlogin") != null) {
		
			// 요청파라미터에서 curPage를 구하고 Paging 객체 반환
			Paging paging = mgrService.getPaging(req);
			
//			// 검색어 파라미터
//			paging.setSearch(req.getParameter("search"));
			
			//mgr main navTab에서 사용할 번호
			req.setAttribute("boardno", 2);
			
			//Paging 객체를 MODEL값으로 지정
			req.setAttribute("paging", paging);
//		System.out.println(paging);		
			
			// ProfileBoard 게시글 목록 조회
			List<ProfileBoard> list = mgrService.getPfBoardList(paging);		
			
			System.out.println(list);
			
			// mgr/profile/profilelist에 모델 (MODEL = profilelist ) 값 전달하기
			req.setAttribute("list", list);
			
//		System.out.println("profile list controller : " + list);
			//view 보내기
			req.getRequestDispatcher("/WEB-INF/views/mgr/profile/profilelist.jsp").forward(req, resp);	
		} else {
			// 로그인 안됐을 경우
			req.getRequestDispatcher("/WEB-INF/views/mgr/layouts/mustlogin.jsp")
			.forward(req, resp);
		}
	}	
}


