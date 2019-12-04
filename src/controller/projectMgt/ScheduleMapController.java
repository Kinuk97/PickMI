package controller.projectMgt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serivce.face.ScheduleService;
import serivce.impl.ScheduleServiceImpl;


@WebServlet("/schedule/map")
public class ScheduleMapController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ScheduleService scheduleService = ScheduleServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/projectMgt/map.jsp");
	}

}
