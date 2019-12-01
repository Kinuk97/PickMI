package controller.Board.CompBoard.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Reply;
import serivce.face.ReplyService;
import serivce.impl.ReplyServiceImpl;

@WebServlet("/compBoard/reply/write")
public class CompBoardReplyWriteController extends HttpServlet {

	private ReplyService replyService = ReplyServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		Reply reply = replyService.getParam(req);
		
		reply.setPostno(4);
		
		replyService.writeReply(reply);
		
		resp.sendRedirect("/compBoard/view?comp_no=" + reply.getBoardno());
	}
}
