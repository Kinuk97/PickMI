package controller.mgr.free;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.FreeBoard;
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
		
		FreeBoard boardno = freeboardService.getParam(req);
		FreeBoard selectBoard = freeboardService.FreeBoardDetail(boardno);
		System.out.println("boardno : " + boardno);
		System.out.println("selectBoard : " + selectBoard);
		
		if (boardno.getFree_no() != 0) {
			try {
				if (selectBoard != null) {			
					fileService.deleteFile(getServletContext().getRealPath("upload"), 3, boardno.getFree_no());
					freeboardService.removeBoard(boardno);
				}
			} catch (ClassCastException e) {
				e.printStackTrace();	
			}
		}	
			resp.sendRedirect("/mgr/freelist");		
	}
}
