package controller.Board.ProjectBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Files;
import dto.LikePost;
import dto.Mate;
import dto.ProjectBoard;
import serivce.face.FileService;
import serivce.face.MateService;
import serivce.face.ProjectBoardService;
import serivce.impl.FileServiceImpl;
import serivce.impl.MateServiceImpl;
import serivce.impl.ProjectBoardServiceImpl;


@WebServlet("/projectBoard/view")
public class ProjectBoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProjectBoardService projectBoardService = new ProjectBoardServiceImpl();
	private FileService fileService = FileServiceImpl.getInstance();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		ProjectBoard projectBoard = projectBoardService.getProjectBoardno(req);
//		projectBoard.setUserno((int) session.getAttribute("userno"));
		
		projectBoard = projectBoardService.view(projectBoard);
		
		// 찜 상태 전달
		LikePost like = new LikePost();
		like.setBoardno(projectBoard.getProj_no());
		like.setPostno(2);
		try {
		like.setUserno((int)session.getAttribute("userno"));
		} catch (NullPointerException e) {
			
		}
		
//		System.out.println(like);
		int countLike = projectBoardService.countLike(like);
		req.setAttribute("countLike", countLike);
//		System.out.println(countLike);
		
		boolean check = projectBoardService.checkLike(like);
		if(check) {
			req.setAttribute("canLike", true);
		} else {
			req.setAttribute("canLike", false);
		}
		
	
		
		if (projectBoard != null) {
			Files files = new Files();
			files.setPostno(2);
			files.setBoardno(projectBoard.getProj_no());
			
			req.setAttribute("files", fileService.getFiles(files));
			
			req.setAttribute("projectBoard", projectBoard);
			
			req.getRequestDispatcher("/WEB-INF/views/board/projectBoard/view.jsp").forward(req, resp);
			
		} else {
			resp.sendRedirect("projectBoard/list");
		}
		
	}

}
