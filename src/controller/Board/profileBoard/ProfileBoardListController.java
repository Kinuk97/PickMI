package controller.Board.profileBoard;

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

@WebServlet("/profileBoard/list")
public class ProfileBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProfileBoardService profileBoardService = new ProfileBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//게시글 목록 조회
		List<ProfileBoard> list = profileBoardService.getProfileList();
		
		req.setAttribute("list", list);
		
//		System.out.println("profile list controller : " + list);
		//view 보내기
		req.getRequestDispatcher("/WEB-INF/views/board/profileBoard/list.jsp").forward(req, resp);
	
	}
}
