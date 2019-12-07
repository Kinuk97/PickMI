package serivce.impl;

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
	public void sendInvite(Alert alert) {
		alertDao.insertAlert(alert);
		
	}

}
