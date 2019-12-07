package controller.mgr.profile;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ProfileBoard;
import serivce.face.FileService;
import serivce.face.ProfileBoardService;
import serivce.face.ProjectBoardService;
import serivce.impl.FileServiceImpl;
import serivce.impl.ProfileBoardServiceImpl;
import serivce.impl.ProjectBoardServiceImpl;

/**
 * Servlet implementation class MgrProfileBoardDelete
 */
@WebServlet("/mgr/profileboard/delete")
public class MgrProfileBoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProfileBoardService profileBoardService = ProfileBoardServiceImpl.getInstance();
	private FileService fileService = FileServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		

		String[] strings = req.getParameterValues("checkRow");
		if( strings != null ) {
			ProfileBoard chkpfBoard = new ProfileBoard();
			
			for (String string : strings) {
				chkpfBoard.setProf_no(Integer.parseInt(string));
				fileService.deleteFile(getServletContext().getRealPath("uplaod"), 1, chkpfBoard.getProf_no());
				profileBoardService.removeProfile(chkpfBoard);
			}
			
		} else {
			
			ProfileBoard pfBoard = profileBoardService.getProfileno(req);
			fileService.deleteFile(getServletContext().getRealPath("uplaod"), 1, pfBoard.getProf_no());
			profileBoardService.removeProfile(pfBoard);
		
		}
		
		resp.sendRedirect("/mgr/profilelist");
}
	
	
}
