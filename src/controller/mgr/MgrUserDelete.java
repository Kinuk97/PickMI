package controller.mgr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User;
import serivce.face.FileService;
import serivce.face.MgrService;
import serivce.face.MyPageService;
import serivce.impl.FileServiceImpl;
import serivce.impl.MgrServiceImpl;
import serivce.impl.MyPageServiceImpl;

/**
 * Servlet implementation class MgrUserDelete
 */
@WebServlet("/mgr/user/delete")
public class MgrUserDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FileService fileService = FileServiceImpl.getInstance();
	private MyPageService myPageService = MyPageServiceImpl.getInstance();
	// ManagerService 객체
	private MgrService mgrService = MgrServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String[] strings = req.getParameterValues("checkRow");
		User user = new User();
		
		for(String string : strings) {
			user.setUserno(Integer.parseInt(string));
			fileService.myPhotoDelete(req.getServletContext().getRealPath("upload"), user); 
			myPageService.userDelete(user);

		}
		resp.sendRedirect("/mgr/userlist");
	}
	
}
