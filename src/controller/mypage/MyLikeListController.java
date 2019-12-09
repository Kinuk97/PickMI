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

import dto.User;
import serivce.face.MyPageService;
import serivce.impl.MyPageServiceImpl;
import util.Paging;

/**
 * Servlet implementation class MyLikeListController
 */
@WebServlet("/mypage/likelist")
public class MyLikeListController extends HttpServlet {
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
		
				// paging 객체를 MODEL값으로 지정
		req.setAttribute("paging", pfpaging);
		req.setAttribute("pjpaging", pjpaging);
		req.setAttribute("comppaging", comppaging);
		req.setAttribute("url", req.getRequestURL());
		
		// 세션값에서 userno 꺼내기
		user.setUserno((Integer)session.getAttribute("userno"));		
//		System.out.println(user);
		
		Map<Integer, List> map = new HashMap<Integer, List>();

		for(int i = 1; i<=3; i++) {
			if(i == 1) { // 프로필 게시판 리스트
				map.put(i, myPageService.getLikeList(pfpaging, user, i));
			} else if(i == 2) { // 프로젝트 게시판 리스트
				map.put(i, myPageService.getLikeList(pjpaging, user, i));
			} else if(i == 3) { // 완성된 게시판 리스트
				map.put(i, myPageService.getLikeList(comppaging, user, i));
			}
		
		}
		req.setAttribute("pflist", map.get(1));
		req.setAttribute("pjlist", map.get(2));
		req.setAttribute("complist", map.get(3));
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/mylike.jsp")
		.forward(req, resp);	
	}
}
