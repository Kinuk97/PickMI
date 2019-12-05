package controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.face.UserDao;
import dao.impl.UserDaoImpl;
import dto.User;
import serivce.face.FileService;
import serivce.face.MyPageService;
import serivce.impl.FileServiceImpl;
import serivce.impl.MyPageServiceImpl;

/**
 * Servlet implementation class MyPageController
 */
@WebServlet("/mypage")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	FileService fileService = FileServiceImpl.getInstance();
	private MyPageService myPageService = MyPageServiceImpl.getInstance();
	private UserDao userDao = new UserDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 세션에서 현재 로그인 돼 있는 email 가져오기
		String email = (String) req.getSession().getAttribute("email");
		
//		System.out.println(email);
		
		// email에 해당하는 회원정보 가져오기
		User userinfo = myPageService.getUser(email);
		
		req.setAttribute("userinfo", userinfo);

		req.getRequestDispatcher("/WEB-INF/views/mypage/mpmain.jsp")
		.forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println("두포스트");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		User user = new User();
		
		user.setUserno((Integer)req.getSession().getAttribute("userno"));
//		user.setPhoto_originname((String)req.getAttribute("originname"));
//		user.setPhoto_storedname((String)req.getAttribute("storedname"));
		
//		System.out.println("user 객체 정보 : " + user); -- null
		
		fileService.myPhotoFile(req, user);
//		System.out.println("파일서비스갔다온 후 user :" + user);
				
		userDao.insertphoto(user);
		
		resp.getWriter().println(user.getPhoto_storedname());
	}
	
}
