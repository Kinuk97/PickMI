package controller.Board.CompBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dto.User;
import serivce.face.CompBoardService;
import serivce.face.MyPageService;
import serivce.impl.CompBoardServiceImpl;
import serivce.impl.MyPageServiceImpl;
import util.Paging;

@WebServlet("/compBoard/list")
public class CompBoardListController extends HttpServlet {

	private CompBoardService compBoardService = new CompBoardServiceImpl();
	private MyPageService myPageService = MyPageServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청파라미터에서 curPage를 구하고 Paging 객체 반환
		Paging paging = compBoardService.getPaging(req);
		
		HttpSession session = req.getSession();
//		System.out.println(paging);
		User user = new User();
		
		String photo = (String) req.getSession().getAttribute("photo_storedname");

		System.out.println(photo);

		
		//Paging객체를 MODEL값으로 지정
		req.setAttribute("paging", paging);
		
		//VIEW지정
		req.getRequestDispatcher("/WEB-INF/views/board/compBoard/list.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		Paging paging = compBoardService.getPaging(req);
		
		Gson gson = new Gson();
		
		resp.getWriter().println(gson.toJson(compBoardService.getBoardList(paging)));
		
	}
	
	
	
}
