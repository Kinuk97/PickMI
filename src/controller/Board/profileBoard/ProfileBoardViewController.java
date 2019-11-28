package controller.Board.profileBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ProfileBoard;
import serivce.face.ProfileBoardService;
import serivce.impl.ProfileBoardServiceImpl;


@WebServlet("/profile/view")
public class ProfileBoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProfileBoardService profileService = new ProfileBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//게시글 정보 얻기
		ProfileBoard profile = profileService.getProfileno(req);
//		System.out.println("profile view controller : " + profile);
		
		//게시판 상세정보 얻기
		ProfileBoard detailProfile = profileService.view(profile);
		
		//모델값 지정하기
		req.setAttribute("profile", detailProfile);
		
		
		//view 지정
		req.getRequestDispatcher("/WEB-INF/views/board/profileBoard/view.jsp").forward(req, resp);
	
	}

}
