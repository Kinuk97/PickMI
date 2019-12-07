package controller.mgr.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ProfileBoard;
import dto.ProjectBoard;
import serivce.face.FileService;
import serivce.face.ProjectBoardService;
import serivce.impl.FileServiceImpl;
import serivce.impl.ProjectBoardServiceImpl;

/**
 * Servlet implementation class MgrProjectBoardDeleteController
 */
@WebServlet("/mgr/projectboard/delete")
public class MgrProjectBoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProjectBoardService projectBoardService = ProjectBoardServiceImpl.getInstance(); 
	private FileService fileService = FileServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String[] strings = req.getParameterValues("checkRow");
		ProjectBoard chkpjBoard = new ProjectBoard();
		if(strings != null) {
			
			for (String string : strings) {
				chkpjBoard.setProj_no(Integer.parseInt(string));
				fileService.deleteFile(getServletContext().getRealPath("uplaod"), 2, chkpjBoard.getProj_no());
				projectBoardService.delete(chkpjBoard);			

			}		
		} else {
			ProjectBoard pjBoard = projectBoardService.getProjectBoardno(req);
			fileService.deleteFile(getServletContext().getRealPath("uplaod"), 2, pjBoard.getProj_no());
			projectBoardService.delete(pjBoard);			
			
		}
		resp.sendRedirect("/mgr/projectlist");
	}
}
