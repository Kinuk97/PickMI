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
import dto.Reply;
import serivce.face.CompBoardService;
import serivce.face.FileService;
import serivce.face.ReplyService;
import serivce.impl.CompBoardServiceImpl;
import serivce.impl.FileServiceImpl;
import serivce.impl.ReplyServiceImpl;
import util.Paging;

@WebServlet("/compBoard/view")
public class CompBoardViewController extends HttpServlet {
	
	private CompBoardService compBoardService = new CompBoardServiceImpl();
	private CompBoardDao compBoardDao = new CompBoardDaoImpl();
	private FileService fileService = FileServiceImpl.getInstance();
	private ReplyService replyService = ReplyServiceImpl.getInstance();

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
			
			// 페이징 만드는 reply.getPaging에서 쓰려고 넣어주는 속성값
			Reply reply = new Reply();
			reply.setPostno(4);
			reply.setBoardno(compBoard.getComp_no());
			req.setAttribute("postno", reply.getPostno());
			req.setAttribute("boardno", reply.getBoardno());
			//=========================================================
			
			Paging paging = replyService.getPaging(req);
			req.setAttribute("paging", paging);
			req.setAttribute("replyList", replyService.getReplyList(paging, reply));
			
			req.getRequestDispatcher("/WEB-INF/views/board/compBoard/view.jsp").forward(req, resp);
			
		} else {
			resp.sendRedirect("compBoard/list");
		}
		
	}
}
