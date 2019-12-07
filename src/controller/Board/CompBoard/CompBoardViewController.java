package controller.Board.CompBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.face.CompBoardDao;
import dao.impl.CompBoardDaoImpl;
import dto.CompBoard;
import dto.Files;
import dto.LikePost;
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

		//찜 정보 받아오기
		HttpSession session = req.getSession();
		LikePost like = new LikePost();
		like.setPostno(4);
		like.setBoardno(compBoard.getComp_no());
		
		try {
			like.setUserno((int)session.getAttribute("userno"));
		} catch (NullPointerException e) {
			System.out.println("로그인하지 않은 유저가 글을 읽는 중");
		}
		
		int countLike = compBoardService.countLike(like);
		req.setAttribute("countLike", countLike);
		
		boolean check = compBoardService.checkLike(like);
		
		if(check) {
			req.setAttribute("canLike", true); //like 할 수 있음
			
		} else {
			req.setAttribute("canLike", false); // like 할 수 없음, unlike 가능
		}
		
		
		//댓글 갯수 가져오기
		Reply cntreply = new Reply();
		
		cntreply.setBoardno(compBoardView.getComp_no());
		cntreply.setPostno(4);
		
		replyService.CountReply(cntreply);
		
		req.setAttribute("cntreply", replyService.CountReply(cntreply));
		//===========================================

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
