package controller.mgr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import serivce.face.MgrService;
import serivce.impl.MgrServiceImpl;

/**
 * Servlet implementation class MgrUserDelete
 */
@WebServlet("/mgr/user/delete")
public class MgrUserDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// ManagerService 객체
	private MgrService mgrService = MgrServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		
	}
	
}
