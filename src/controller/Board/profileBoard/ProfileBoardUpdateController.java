package controller.Board.profileBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Files;
import dto.ProfileBoard;
import serivce.face.FileService;
import serivce.face.ProfileBoardService;
import serivce.impl.FileServiceImpl;
import serivce.impl.ProfileBoardServiceImpl;

@WebServlet("/profileBoard/update")
public class ProfileBoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProfileBoardService profileBoardService = ProfileBoardServiceImpl.getInstance();
	private FileService fileService = FileServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProfileBoard profile = profileBoardService.getProfileno(req);
		profile = profileBoardService.view(profile);
		
		if(profile != null) {
			Files file = new Files();
			file.setPostno(1);
			file.setBoardno(profile.getProf_no());
			
			req.setAttribute("file", fileService.getFiles(file));
			req.setAttribute("profile", profile);
//		System.out.println("profileboard update controller : " + profile);
			
			req.getRequestDispatcher("/WEB-INF/views/board/profileBoard/update.jsp").forward(req,resp);
		} else {
			resp.sendRedirect("/profileBoard/list");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		int profileno = fileService.modifyBoard(req, 1);
		
		if (profileno > 0) {
			resp.sendRedirect("/profileBoard/view?prof_no=" + profileno );
		} else {
			System.out.println("게시글 수정 오류(profile) : " + profileno);
			resp.sendRedirect("/profileBoard/list");
		}
	
	}
	
}
