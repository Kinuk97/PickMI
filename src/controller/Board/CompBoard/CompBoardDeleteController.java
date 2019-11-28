package controller.Board.CompBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CompBoard;
import serivce.face.CompBoardService;
import serivce.face.FileService;
import serivce.impl.CompBoardServiceImpl;
import serivce.impl.FileServiceImpl;

@WebServlet("/compBoard/delete")
public class CompBoardDeleteController extends HttpServlet {
	
	private CompBoardService compBoardService = new CompBoardServiceImpl();
	private FileService fileService = FileServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CompBoard compBoard = compBoardService.getParam(req);
		CompBoard comp = compBoardService.compBoardDetail(compBoard);
		
		fileService.deleteFile(getServletContext().getRealPath("upload"), 4, compBoard.getComp_no());
		compBoardService.delete(compBoard);
		
		resp.sendRedirect("/compBoard/list");
	}
	
	
	

}
