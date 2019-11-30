package controller.Board.CompBoard.reply;

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
import serivce.face.CompBoardService;
import serivce.face.ReplyService;
import serivce.impl.CompBoardServiceImpl;
import serivce.impl.ReplyServiceImpl;
import util.Paging;

@WebServlet("/compBoard/reply/list")
public class CompBoardReplyListController extends HttpServlet {

	private CompBoardService compBoardService = new CompBoardServiceImpl();
	private ReplyService replyService = ReplyServiceImpl.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		// 페이징만드는 reply.getPaging에서 쓰려고 넣어주는 속성값
		Reply reply = new Reply();
		reply.setPostno(4);
		reply.setBoardno(compBoardService.getParam(req).getComp_no());
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
