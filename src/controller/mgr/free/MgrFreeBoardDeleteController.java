package controller.mgr.free;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.FreeBoard;
import dto.ProjectBoard;
import serivce.face.FileService;
import serivce.face.FreeBoardService;
import serivce.impl.FileServiceImpl;
import serivce.impl.FreeBoardServiceImpl;

/**
 * Servlet implementation class MgrFreeBoardDeleteController
 */
@WebServlet("/mgr/freeboard/delete")
public class MgrFreeBoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private FreeBoardService freeboardService = FreeBoardServiceImpl.getInstance();
	private FileService fileService = FileServiceImpl.getInstance();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String[] strings = req.getParameterValues("checkRow");
		FreeBoard chkfrBoard = new FreeBoard();

		if(strings != null) {
			
			for (String string : strings) {
				chkfrBoard.setFree_no(Integer.parseInt(string));
				fileService.deleteFile(getServletContext().getRealPath("upload"), 3, chkfrBoard.getFree_no());
				freeboardService.removeBoard(chkfrBoard);		
			}
						
		} else {
			
			FreeBoard freeBoard = freeboardService.getParam(req);
			fileService.deleteFile(getServletContext().getRealPath("uplaod"), 2, freeBoard.getFree_no());
			freeboardService.removeBoard(freeBoard);			

			
		}
		
		
			resp.sendRedirect("/mgr/freelist");		
	}
}
