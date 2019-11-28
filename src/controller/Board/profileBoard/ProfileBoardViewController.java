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


@WebServlet("/profile/view")
public class ProfileBoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProfileBoardService profileService = ProfileBoardServiceImpl.getInstance();
	private FileService fileService = FileServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//게시글 정보 얻기
		ProfileBoard profile = profileService.getProfileno(req);
		
		//게시판 상세정보 얻기
		ProfileBoard detailProfile = profileService.view(profile);
		
		if (profile != null) {
			Files file = new Files();
			file.setPostno(1);
			file.setBoardno(profile.getProf_no());
			
			req.setAttribute("file", fileService.getFiles(file));
			//모델값 지정하기
			req.setAttribute("profile", detailProfile);
			//view 지정
			req.getRequestDispatcher("/WEB-INF/views/board/profileBoard/view.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("/profileBoard/list");
		}
	
	}

}
