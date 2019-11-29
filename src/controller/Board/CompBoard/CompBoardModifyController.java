package controller.Board.CompBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CompBoard;
import dto.Files;
import serivce.face.CompBoardService;
import serivce.face.FileService;
import serivce.impl.CompBoardServiceImpl;
import serivce.impl.FileServiceImpl;

@WebServlet("/compBoard/update")
public class CompBoardModifyController extends HttpServlet {
	
	private CompBoardService compBoardService = new CompBoardServiceImpl();
	private FileService fileService = FileServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//받아오기
		CompBoard compBoard = compBoardService.getParam(req);
		
		compBoard = compBoardService.compBoardDetail(compBoard);
		
		if (compBoard != null) {
			Files files = new Files();
			
			files.setPostno(4);
			files.setBoardno(compBoard.getComp_no());
			
			req.setAttribute("files", fileService.getFiles(files));
			req.setAttribute("compBoard", compBoard);
			req.getRequestDispatcher("/WEB-INF/views/board/compBoard/update.jsp").forward(req, resp);
			
			
		} else {
			resp.sendRedirect("/compBoard/list");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		int resultBoardno = fileService.modifyBoard(req, 4);
		
		if (resultBoardno > 0) {
			resp.sendRedirect("/compBoard/view?comp_no=" + resultBoardno);
			
		} else {
			System.out.println("게시글 수정 오류 : " + resultBoardno);
			resp.sendRedirect("/compBoard/list");
		}
	}
	
	

}
