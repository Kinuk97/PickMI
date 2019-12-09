package controller.Alert;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.Alert;
import serivce.face.AlertService;
import serivce.impl.AlertServiceImpl;

@WebServlet("/alert/list")
public class AlertListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AlertService alertService = AlertServiceImpl.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Alert alert = alertService.getParam(req);
		
		List<Alert> list = alertService.getAlertList(alert);
		
		resp.setCharacterEncoding("UTF-8");
		
		resp.getWriter().println(new Gson().toJson(list));
	}
}
