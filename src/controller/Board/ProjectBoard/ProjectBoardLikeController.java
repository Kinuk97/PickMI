package controller.Board.ProjectBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.LikePost;
import serivce.face.ProjectBoardService;
import serivce.impl.ProjectBoardServiceImpl;

@WebServlet("/projectBoard/like")
public class ProjectBoardLikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProjectBoardService projectBoardService = new ProjectBoardServiceImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 찜 정보 얻기
		LikePost like = projectBoardService.getLike(req);
//		System.out.println("likepost : " + like);

		// 찜 정보 토글
		boolean check = projectBoardService.checkLike(like);
		int countLike = 0;
		
		if(check) {
			projectBoardService.like(like);
			countLike = projectBoardService.countLike(like);
		} else {
			projectBoardService.like(like);
			countLike = projectBoardService.countLike(like);
		}


		// 결과 JSON응답
		resp.getWriter().println("{\"countLike\":" + countLike + ", \"check\":" + check +"}");

	}

}
