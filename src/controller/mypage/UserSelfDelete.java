package controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import serivce.face.FileService;
import serivce.face.MyPageService;
import serivce.impl.FileServiceImpl;
import serivce.impl.MyPageServiceImpl;

/**
 * Servlet implementation class UserSelfDelete
 */
@WebServlet("/mypage/selfdelete")
public class UserSelfDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	FileService fileService = FileServiceImpl.getInstance();
	MyPageService myPageService = MyPageServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.getRequestDispatcher("/WEB-INF/views/mypage/selfdelete")
		.forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		
		HttpSession session = req.getSession();
//		System.out.println("test" + session.getAttribute("photo_storedname"));
				
		User user = new User();
		
		user.setUserno((Integer)req.getSession().getAttribute("userno"));
		user.setPhoto_originname((String)req.getSession().getAttribute("photo_originname")); 
		user.setPhoto_storedname((String)req.getSession().getAttribute("photo_storedname")); 
//		System.out.println("UserSelfDeleteController : " + user);
		
		if( user.getPhoto_storedname() != null ) { // 사용자 프로필 사진이 있으면 삭제
		
			fileService.myPhotoDelete(req.getServletContext().getRealPath("upload"), user); 
			myPageService.userDelete(user);
//			System.out.println("유저가 삭제 됐는지? : " + user);
			session.invalidate();
			
		} else {
			myPageService.userDelete(user);
			session.invalidate();
		}
		
		resp.sendRedirect("/main");
		
		
		

		
		
	
	}
	
}
