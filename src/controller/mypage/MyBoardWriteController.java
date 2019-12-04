package controller.mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		// 요청파라미터에서 curPage 구하고 Paging 객체 반환
		Paging paging = myPageService.getPaging(req);
		
		// paging 객체를 MODEL값으로 지정
		req.setAttribute("paging", paging);
		
		// 사용자번호 가져오기
		User user =  myPageService.getUserno(req);
		
		// Profile List 목록 조회
		List<ProfileBoard> pflist = myPageService.getpfList(paging, req);
		
		// Project List 목록 조회
		List<ProjectBoard> pjlist = myPageService.getpjList(paging, req);
		
		// Comp List 목록 조회
		List<CompBoard> complist = myPageService.getcompList(paging, req);
		
		// Free List 목록 조회
		List<FreeBoard> freelist = myPageService.getfreeList(paging, req);
		
		req.setAttribute("pflist", pflist);
		req.setAttribute("pjlist", pjlist);
		req.setAttribute("complist", complist);
		req.setAttribute("freelist", freelist);
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/myboardwrite.jsp")
		.forward(req, resp);	
	}
}
