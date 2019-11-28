package controller.Board.FreeBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Files;
import dto.FreeBoard;
import serivce.face.FileService;
import serivce.face.FreeBoardService;
import serivce.impl.FileServiceImpl;
import serivce.impl.FreeBoardServiceImpl;

@WebServlet("/freeboard/update")
public class FreeBoardModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FreeBoardService freeBoardService = FreeBoardServiceImpl.getInstance();
	private FileService fileService = FileServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FreeBoard freeBoard = freeBoardService.getParam(req);

		freeBoard = freeBoardService.FreeBoardDetail(freeBoard);

		if (freeBoard != null) {
			Files file = new Files();
			file.setPostno(3);
			file.setBoardno(freeBoard.getFree_no());

			req.setAttribute("file", fileService.getFiles(file));

			req.setAttribute("board", freeBoard);

			req.getRequestDispatcher("/WEB-INF/views/board/freeboard/update.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("/freeboard/list");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		int resultBoardno = fileService.modifyBoard(req, 3);
		
		if (resultBoardno > 0) {
			resp.sendRedirect("/freeboard/view?free_no=" + resultBoardno);
		} else {
			System.out.println("게시글 수정 오류 : " + resultBoardno);
			resp.sendRedirect("/freeboard/list");
		}

	}
}
