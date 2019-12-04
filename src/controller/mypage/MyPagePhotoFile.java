package controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serivce.face.FileService;
import serivce.impl.FileServiceImpl;

/**
 * Servlet implementation class MyPagePhotoFile
 */
@WebServlet("/mypage/photo")
public class MyPagePhotoFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	FileService fileService = FileServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
	
		fileService.myPhotoFile(req);
	
	}
	
}
