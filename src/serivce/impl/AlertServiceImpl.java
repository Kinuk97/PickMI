package serivce.impl;

import java.util.ArrayList;
import java.util.List;

import dao.face.AlertDao;
import dao.impl.AlertDaoImpl;
import dto.Alert;
import dto.ProjectBoard;
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
	public List<Alert> invitedList(Alert alert) {
//		List<Alert> alert1 = new ArrayList<Alert>();
//			alert1 = alertDao.invitedList(alert);
			
		
		return alertDao.invitedList(alert);
	}
	
	@Override
	public int checkProfile(Alert alert) {
		return alertDao.checkProfile(alert);
	}
	
	@Override
	public void sendAlertdenied(Alert alert) {
		alertDao.insertDeniedAlert(alert);
		
	}
	
	@Override
	public void sendInvite(Alert alert) {
		alertDao.insertAlert(alert);
		
	}

}
