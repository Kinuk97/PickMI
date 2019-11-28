package controller.Board.CompBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.face.CompBoardDao;
import dao.impl.CompBoardDaoImpl;
import dto.CompBoard;
import dto.Files;
import serivce.face.CompBoardService;
import serivce.face.FileService;
import serivce.impl.CompBoardServiceImpl;
import serivce.impl.FileServiceImpl;

@WebServlet("/compBoard/view")
public class CompBoardViewController extends HttpServlet {
	
	private CompBoardService compBoardService = new CompBoardServiceImpl();
	private CompBoardDao compBoardDao = new CompBoardDaoImpl();
	private FileService fileService = FileServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//요청 파라미터 얻기
		CompBoard compBoard = compBoardService.getParam(req);

		compBoardDao.countViews(compBoard);
		
		CompBoard compBoardView = compBoardService.compBoardDetail(compBoard);
		
		if (compBoard != null) {
			Files files = new Files();
			files.setPostno(4);
			files.setBoardno(compBoardView.getComp_no());
			
			req.setAttribute("files", fileService.getFiles(files));
			
			req.setAttribute("compBoard", compBoardView);
			
			req.getRequestDispatcher("/WEB-INF/views/board/compBoard/view.jsp").forward(req, resp);
			
		} else {
			resp.sendRedirect("compBoard/list");
		}
		
	}
}
