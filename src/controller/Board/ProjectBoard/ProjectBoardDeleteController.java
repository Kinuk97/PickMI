package controller.Board.ProjectBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ProjectBoard;
import serivce.face.FileService;
import serivce.face.ProjectBoardService;
import serivce.impl.FileServiceImpl;
import serivce.impl.ProjectBoardServiceImpl;


@WebServlet("/projectBoard/delete")
public class ProjectBoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProjectBoardService projectBoardService = new ProjectBoardServiceImpl();
	private FileService fileService = FileServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ProjectBoard projectBoard = projectBoardService.getProjectBoardno(req);
		
		fileService.deleteFile(getServletContext().getRealPath("upload"), 2, projectBoard.getProj_no());
		projectBoardService.delete(projectBoard);
		
		resp.sendRedirect("/projectBoard/list");
	}
}
