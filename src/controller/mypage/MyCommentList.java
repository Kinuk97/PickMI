package controller.mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Reply;
import serivce.face.MyPageService;
import serivce.impl.MyPageServiceImpl;
import util.Paging;


@WebServlet("/mycomment/list")
public class MyCommentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MyPageService myPageService = new MyPageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.setCharacterEncoding("UTF-8");
		 
		req.getRequestDispatcher("/WEB-INF/views/mypage/mycomment.jsp").forward(req, resp);
		
		Paging paging = myPageService.getPaging(req);
		
		req.setAttribute("paging", paging);
		
		int userno = (int) req.getSession().getAttribute("userno"); // userno 세션값 가져오기
		Reply reply = new Reply(); // 댓글 객체
		reply.setUserno(userno); // 댓글객체에 userno 담기
		
		List<Reply> list = myPageService.getReplyList(paging);
		
		req.setAttribute("list", list);
	}
	

}
