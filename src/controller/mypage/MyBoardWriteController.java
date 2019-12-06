package controller.mypage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.CompBoard;
import dto.FreeBoard;
import dto.ProfileBoard;
import dto.ProjectBoard;
import dto.User;
import serivce.face.MyPageService;
import serivce.impl.MyPageServiceImpl;
import util.Paging;

/**
 * Servlet implementation class MyBoardWriteController
 */
@WebServlet("/mypage/boardwrite")
public class MyBoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MyPageService myPageService = MyPageServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		User user = new User();
		
		// 요청파라미터에서 curPage 구하고 Paging 객체 반환
		Paging pfpaging = myPageService.getPaging(req,1);
		Paging pjpaging = myPageService.getPaging(req,2);
		Paging comppaging = myPageService.getPaging(req,3);
		Paging freepaging = myPageService.getPaging(req,4);
		
		// paging 객체를 MODEL값으로 지정
		req.setAttribute("pfpaging", pfpaging);
		req.setAttribute("pjpaging", pjpaging);
		req.setAttribute("comppaging", comppaging);
		req.setAttribute("freepaging", freepaging);
		
		// 세션값에서 userno 꺼내기
		user.setUserno((Integer)session.getAttribute("userno"));		
//		System.out.println(user);
		
		Map<Integer, List> map = new HashMap<Integer, List>();
		
		for(int i = 1; i<=4; i++) {
			if(i == 1) {
				map.put(i, myPageService.getList(pfpaging, user, i));
			} else if(i == 2) {
				map.put(i, myPageService.getList(pjpaging, user, i));
			} else if(i == 3) {
				map.put(i, myPageService.getList(comppaging, user, i));
			} else if(i == 4) {
				map.put(i, myPageService.getList(freepaging, user, i));
			}
		}
		
//		// Profile List 목록 조회
//		List<ProfileBoard> pflist = myPageService.getpfList(pfpaging, user);
//		
//		// Project List 목록 조회
//		List<ProjectBoard> pjlist = myPageService.getpjList(pjpaging, user);
//		
//		// Comp List 목록 조회
//		List<CompBoard> complist = myPageService.getcompList(comppaging, user);
//		
//		// Free List 목록 조회
//		List<FreeBoard> freelist = myPageService.getfreeList(freepaging, user);
		
		req.setAttribute("pflist", map.get(1));
		req.setAttribute("pjlist", map.get(2));
		req.setAttribute("complist", map.get(3));
		req.setAttribute("freelist", map.get(4));
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/myboard/myboardwrite.jsp")
		.forward(req, resp);	
	}
}