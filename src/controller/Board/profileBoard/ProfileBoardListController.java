package controller.Board.profileBoard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.ProfileBoard;
import serivce.face.ProfileBoardService;
import serivce.impl.ProfileBoardServiceImpl;
import util.Paging;

@WebServlet("/profileBoard/list")
public class ProfileBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProfileBoardService profileBoardService = ProfileBoardServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청파라미터에서 curPage를 구하고 Paging 객체 반환
		Paging paging = profileBoardService.getPaging(req);
		
		//Paging 객체를 model값으로 지정
		req.setAttribute("paging", paging);
		
		
		//게시글 목록 조회
		List<ProfileBoard> list = profileBoardService.getBoardList(paging);
		
		
		req.setAttribute("list", list);
		
//		System.out.println("profile list controller : " + list);
		//view 보내기
		req.getRequestDispatcher("/WEB-INF/views/board/profileBoard/list.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		Paging paging = profileBoardService.getPaging(req);
	
		Gson gson = new Gson();
		
		resp.getWriter().println(gson.toJson(profileBoardService.getBoardList(paging)));

	}
}
