package controller.Board.profileBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.LikePost;
import serivce.face.ProfileBoardService;
import serivce.impl.ProfileBoardServiceImpl;

/**
 * Servlet implementation class ProfileBoardLikeController
 */
@WebServlet("/profileBoard/like")
public class ProfileBoardLikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProfileBoardService profileBoardService = ProfileBoardServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		//찜 정보 가져오기
		LikePost like = new LikePost();
		like.setPostno(1);
		like.setBoardno(profileBoardService.getProfileno(req).getProf_no());
		like.setUserno((int)session.getAttribute("userno"));
		
		
//		System.out.println("profile like controller : " + like);
		//찜 했던 적 있는지 확인
		boolean check = profileBoardService.checkLike(like);
		int countLike = 0 ;
//		System.out.println("profile like controller :" + check);
		if(check) {
			profileBoardService.like(like);
			countLike = profileBoardService.countLike(like);
		} else {
			profileBoardService.like(like);
			countLike = profileBoardService.countLike(like);

		}
//		System.out.println("profile like controller : " + countLike);
		resp.getWriter().append("{\"countLike\":" + countLike + ", \"check\":"+ check+"}");

	}

}
