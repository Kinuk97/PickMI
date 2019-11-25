package controller.Board.profileBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import serivce.face.ProfileBoardService;
import serivce.impl.ProfileBoardServiceImpl;

/**
 * Servlet implementation class ProfileBoardWriteController
 */
@WebServlet("/profileBoard/write")
public class ProfileBoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProfileBoardService profileBoardService = new ProfileBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		
		//view 전달
		req.getRequestDispatcher("/WEB-INF/views/board/profileBoard/write.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//write에 저장
//		profileBoardService.write(req);
		
		//list로 리다이렉트
		resp.sendRedirect("/profileBoard/list");
	}

}