package serivce.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.face.AlertDao;
import dao.impl.AlertDaoImpl;
import dto.Alert;
import serivce.face.AlertService;

public class AlertServiceImpl implements AlertService {
	
	private AlertDao alertDao = AlertDaoImpl.getInstance();
	
	private AlertServiceImpl() {
		
	}
	private static class Singleton {
		private static final AlertService instance = new AlertServiceImpl();
	}
	
	public static AlertService getInstance() {
		return Singleton.instance;

	}
	
	@Override
	public Alert getParam(HttpServletRequest req) {
		Alert alert = new Alert();
		
		String param = req.getParameter("userno");
		if (param != null && !"".equals(param)) {
			try {
				alert.setUserno(Integer.parseInt(param));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		param = req.getParameter("alert");
		if (param != null && !"".equals(param)) {
			alert.setAlert(param);
		}
		
		param = req.getParameter("sender");
		if (param != null && !"".equals(param)) {
			try {
				alert.setSender(Integer.parseInt(param));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		param = req.getParameter("checkcheck");
		if (param != null && !"".equals(param)) {
			alert.setCheckcheck(param.charAt(0));
		}
		
		return alert;
	}
	
	@Override
	public List<Alert> getAlertList(Alert alert) {
		return alertDao.selectMyAlert(alert);
	}
	
	@Override
	public void sendInvite(Alert alert) {
		alertDao.insertAlert(alert);
		
	}


}
