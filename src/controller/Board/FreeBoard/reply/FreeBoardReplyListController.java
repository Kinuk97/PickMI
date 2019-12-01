package controller.Board.FreeBoard.reply;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.Reply;
import serivce.face.FreeBoardService;
import serivce.face.ReplyService;
import serivce.impl.FreeBoardServiceImpl;
import serivce.impl.ReplyServiceImpl;
import util.Paging;

@WebServlet("/freeboard/reply/list")
public class FreeBoardReplyListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FreeBoardService freeBoardService = FreeBoardServiceImpl.getInstance();
	private ReplyService replyService = ReplyServiceImpl.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		
		// 페이징만드는 reply.getPaging에서 쓰려고 넣어주는 속성값
		Reply reply = new Reply();
		reply.setPostno(3);
		reply.setBoardno(freeBoardService.getParam(req).getFree_no());
		req.setAttribute("postno", reply.getPostno());
		req.setAttribute("boardno", reply.getBoardno());
		// =======================================================

		Paging paging = replyService.getPaging(req);

		Map<String, Object> map = new HashMap<String, Object>();
		
		Gson gson = new Gson();
		
		map.put("paging", paging);
		map.put("replyList", replyService.getReplyList(paging, reply));
		
		resp.getWriter().println(gson.toJson(map));
	}
}
