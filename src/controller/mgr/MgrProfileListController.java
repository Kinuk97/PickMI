package controller.mgr;

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

	ProfileBoardService profileBoardService = new ProfileBoardServiceImpl();
	MgrService mgrService = new MgrServiceImpl();
	
//	CompBoardService compBoard = new CompBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		
		//?���??��?��미터?��?�� curPage�? 구하�? Paging 객체 반환
		Paging paging = mgrService.getPaging(req);
		
		 // �??��?�� ?��?��미터 
		paging.setSearch(req.getParameter("search"));
		
		//Paging 객체�? model값으�? �??��
		req.setAttribute("paging", paging);
//		System.out.println(paging);		

		// ProfileBoard 게시�? 목록 조회
		List<ProfileBoard> list = mgrService.getPfBoardList(paging);		
		
		// list 객체�? model값으�? �??��
		req.setAttribute("list", list);
		
//		System.out.println("profile list controller : " + list);
		//view 보내�?
		req.getRequestDispatcher("/WEB-INF/views/mgr/profilelist.jsp").forward(req, resp);

	}
	
}
