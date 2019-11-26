package controller.mrg;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ProfileBoard;
import serivce.face.ProfileBoardService;
import serivce.impl.ProfileBoardServiceImpl;
import util.Paging;

/**
 * Servlet implementation class MgrProfileListController
 */
@WebServlet("/mgr/profilelist")
public class MgrProfileListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ProfileBoardService profileBoardService = new ProfileBoardServiceImpl();
	
//	CompBoardService compBoard = new CompBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		//요청파라미터에서 curPage를 구하고 Paging 객체 반환
		Paging paging = profileBoardService.getPaging(req);
		
		 // 검색어 파라미터 
		paging.setSearch(req.getParameter("search"));
		
		//Paging 객체를 model값으로 지정
		req.setAttribute("paging", paging);
		System.out.println(paging);		
		
		
		// ProfileBoard 게시글 목록 조회
		List<ProfileBoard> list = profileBoardService.getBoardList(paging);
		
		// list 객체를 model값으로 지정
		req.setAttribute("list", list);
		
//		System.out.println("profile list controller : " + list);
		//view 보내기
		req.getRequestDispatcher("/WEB-INF/views/mgr/profilelist.jsp").forward(req, resp);

	}
	
}
