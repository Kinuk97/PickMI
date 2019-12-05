package controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.face.UserDao;
import dao.impl.UserDaoImpl;
import dto.User;
import serivce.face.FileService;
import serivce.impl.FileServiceImpl;

/**
 * Servlet implementation class MyPhotoDelete
 */
@WebServlet("/mypage/photodelete")
public class MyPhotoDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	FileService fileService = FileServiceImpl.getInstance();
	private UserDao userDao = new UserDaoImpl();

	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("AJAX 실행 ! ");
		
		HttpSession session = req.getSession();
		
		User user = new User();
		
		user.setUserno((Integer)req.getSession().getAttribute("userno"));
//		user.setPhoto_originname((String)req.getAttribute("originname")); //세션에 오리진네임 없음
//		user.setPhoto_storedname((String)req.getAttribute("storedname")); //세션에 오리진네임 없음
			
		System.out.println("photdeletecontroller : " + user);
		
		fileService.myPhotoDelete(req.getServletContext().getRealPath("upload"), user);
		resp.sendRedirect("/mypage");
		
		}

}
