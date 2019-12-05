package controller.projectMgt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CheckList;
import serivce.face.ScheduleService;
import serivce.impl.ScheduleServiceImpl;

@WebServlet("/schedule/add/checkList")
public class CheckListAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ScheduleService scheduleService = ScheduleServiceImpl.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		CheckList checkList = scheduleService.getCheck(req);
		
		scheduleService.addCheck(checkList);
	}
	
}
