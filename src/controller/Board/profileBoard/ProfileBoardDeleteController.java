package controller.Board.profileBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ProfileBoard;
import serivce.face.FileService;
import serivce.face.ProfileBoardService;
import serivce.impl.FileServiceImpl;
import serivce.impl.ProfileBoardServiceImpl;

@WebServlet("/profileBoard/delete")
public class ProfileBoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProfileBoardService profileBoardService = ProfileBoardServiceImpl.getInstance();
	private FileService fileService = FileServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ProfileBoard profile = profileBoardService.getProfileno(req);
		profile = profileBoardService.view(profile);
		
		if ( profile.getProf_no() != 0 ) {
			fileService.deleteFile(getServletContext().getRealPath("upload"), 1, profile.getProf_no());
			profileBoardService.removeProfile(profile);
		}
		resp.sendRedirect("/profileBoard/list");
	}
}
