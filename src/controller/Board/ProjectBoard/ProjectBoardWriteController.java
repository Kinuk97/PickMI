package controller.Board.ProjectBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serivce.face.FileService;
import serivce.impl.FileServiceImpl;


@WebServlet("/projectBoard/write")
public class ProjectBoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FileService fileService = FileServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/board/projectBoard/write.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		int resultBoardno = fileService.writeBoard(req, 2);
		
		if (resultBoardno > 0) {
			resp.sendRedirect("/projectBoard/view?proj_no=" + resultBoardno);
		} else {
//			System.out.println("게시글 작성 오류 : " + resultBoardno);
			resp.sendRedirect("/projectBoard/list");
		}
	}
}
