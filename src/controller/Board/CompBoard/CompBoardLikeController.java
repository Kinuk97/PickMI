package controller.Board.CompBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.LikePost;
import serivce.face.CompBoardService;
import serivce.impl.CompBoardServiceImpl;

@WebServlet("/compBoard/like")
public class CompBoardLikeController extends HttpServlet {
	
	private CompBoardService compBoardService = new CompBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		//찜 정보 가져오기
		LikePost like = new LikePost();
		
		like.setPostno(4);
		like.setBoardno(compBoardService.getParam(req).getComp_no());
		like.setUserno((int)session.getAttribute("userno"));
		
		
		boolean check = compBoardService.checkLike(like);
		int countLike = 0;
		
		if (check) {
			compBoardService.like(like);
			countLike = compBoardService.countLike(like);
		
		} else {
			compBoardService.like(like);
			countLike = compBoardService.countLike(like);
		}
		
		System.out.println(countLike);
		resp.getWriter().append("{\"countLike\":" + countLike + ",\"check\":" + check+"}");
	}
	
	
	

}
