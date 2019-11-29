package controller.Board.FreeBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Files;
import dto.FreeBoard;
import dto.Reply;
import serivce.face.FileService;
import serivce.face.FreeBoardService;
import serivce.face.ReplyService;
import serivce.impl.FileServiceImpl;
import serivce.impl.FreeBoardServiceImpl;
import serivce.impl.ReplyServiceImpl;

@WebServlet("/freeboard/view")
public class FreeBoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FreeBoardService freeBoardService = FreeBoardServiceImpl.getInstance();
	private ReplyService replyService = ReplyServiceImpl.getInstance();
	private FileService fileService = FileServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FreeBoard freeBoard = freeBoardService.getParam(req);
		
		freeBoardService.viewCounting(freeBoard);
		
		freeBoard = freeBoardService.FreeBoardDetail(freeBoard);
		
		if (freeBoard != null) {
			Files file = new Files();
			file.setPostno(3);
			file.setBoardno(freeBoard.getFree_no());
			
			req.setAttribute("file", fileService.getFiles(file));
			
			req.setAttribute("board", freeBoard);
			
			// 페이징만드는 reply.getPaging에서 쓰려고 넣어주는 속성값
			Reply reply = new Reply();
			reply.setPostno(3);
			reply.setBoardno(freeBoard.getFree_no());
			req.setAttribute("postno", reply.getPostno());
			req.setAttribute("boardno", reply.getBoardno());
			
			req.setAttribute("replyList", replyService.getReplyList(replyService.getPaging(req), reply));
			
			req.getRequestDispatcher("/WEB-INF/views/board/freeboard/view.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("/freeboard/list");
		}
		
	}
}
