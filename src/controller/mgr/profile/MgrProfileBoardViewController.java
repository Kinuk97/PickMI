package controller.mgr.profile;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ProfileBoard;
import serivce.face.ProfileBoardService;
import serivce.impl.ProfileBoardServiceImpl;

/**
 * Servlet implementation class MgrProfileBoardViewController
 */
@WebServlet("/mgr/profileview")
public class MgrProfileBoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProfileBoardService profileService = new ProfileBoardServiceImpl();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println("두겟");

		//게시글 정보 얻기
		ProfileBoard profile = profileService.getProfileno(req);	
//		System.out.println("profile객체의 메소드 : " + profile);
		
		//게시판 상세정보 얻기
		ProfileBoard detailProfile = profileService.view(profile);
//		System.out.println("게시판 상세정보 : " + detailProfile);
		
		
		//모델값 지정하기
		req.setAttribute("profile", profile);
		
		
		//view 지정
		req.getRequestDispatcher("/WEB-INF/views/mgr/profile/profileview.jsp").forward(req, resp);
	
	}
	
}
