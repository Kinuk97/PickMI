package controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serivce.face.MyPageService;
import serivce.impl.MyPageServiceImpl;

/**
 * Servlet implementation class MyPhotoModify
 */
@WebServlet("/mypage/photomodify")
public class MyPhotoModify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MyPageService myPageService = MyPageServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
	
	}
	
}
