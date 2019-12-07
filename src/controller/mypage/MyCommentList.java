package controller.mypage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dto.Reply;
import dto.User;
import serivce.face.CompBoardService;
import serivce.face.MyPageService;
import serivce.face.ReplyService;
import serivce.impl.CompBoardServiceImpl;
import serivce.impl.MyPageServiceImpl;
import serivce.impl.ReplyServiceImpl;
import util.Paging;

@WebServlet("/mycomment/list")
public class MyCommentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MyPageService myPageService = new MyPageServiceImpl();
	private CompBoardService compBoardService = new CompBoardServiceImpl();
	private ReplyService replyService = ReplyServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		// =======================================================
		
		
		Reply reply = new Reply();

		User user = new User();
		
		// 요청파라미터에서 curPage 구하고 Paging 객체 반환
		Paging comppaging = replyService.getPaging(req,1);
		Paging freepaging = replyService.getPaging(req,2);
		
		// paging 객체를 MODEL값으로 지정
		req.setAttribute("comppaging", comppaging);
		req.setAttribute("freepaging", freepaging);
		
		// 세션값에서 userno 꺼내기
		user.setUserno((Integer)session.getAttribute("userno"));
		reply.setUserno((Integer)session.getAttribute("userno"));
		reply.setUsername((String)session.getAttribute("name"));
		user.setName((String)session.getAttribute("name"));
//		System.out.println(user);
		
		Map<Integer, List> map = new HashMap<Integer, List>();
		
		for(int i = 1; i<=2; i++) {
			if(i == 1) {
				map.put(i, replyService.getReplyList(comppaging, reply, i));
			} else if(i == 2) {
				map.put(i, replyService.getReplyList(freepaging, reply, i));
			}
		}
		
		System.out.println(map);
		req.setAttribute("complist", map.get(1));
		req.setAttribute("freelist", map.get(2));
		

		
		req.getRequestDispatcher("/WEB-INF/views/mypage/mycomment.jsp")
		.forward(req, resp);	

	}
	

}
