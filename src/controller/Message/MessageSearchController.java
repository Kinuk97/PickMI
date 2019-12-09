package controller.Message;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.User;
import serivce.face.MessageService;
import serivce.impl.MessageServiceImpl;

@WebServlet("/msg/search")
public class MessageSearchController extends HttpServlet {
	
	private MessageService messageService = new MessageServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String search = req.getParameter("search");
		
		List<User> searchList = messageService.getSearchList(search, null);
		
		req.setAttribute("searchList", searchList);		
		req.getRequestDispatcher("/WEB-INF/views/msg/search.jsp").forward(req, resp);
	}
	

}
