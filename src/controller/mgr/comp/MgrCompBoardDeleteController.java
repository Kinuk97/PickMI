package controller.mgr.comp;

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

@WebServlet("/mgr/compboard/delete")
public class MgrCompBoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CompBoardService compBoardService = new CompBoardServiceImpl();
	private FileService fileService = FileServiceImpl.getInstance();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String[] strings = req.getParameterValues("checkRow");
		CompBoard compBoard =  new CompBoard();
//		System.out.println(strings);
		
		for (String string : strings) {
			compBoard.setComp_no(Integer.parseInt(string));
			fileService.deleteFile(getServletContext().getRealPath("upload"), 4, compBoard.getComp_no());
			compBoardService.delete(compBoard);
		}
		
		
//		CompBoard comp = compBoardService.compBoardDetail(compBoard);
		
//		fileService.deleteFile(getServletContext().getRealPath("upload"), 4, compBoard.getComp_no());
//
//		compBoardService.delete(compBoard);
//		
//		resp.sendRedirect("/mgr/complist");

	
	}
	
}
