package serivce.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Alert;

public interface AlertService {

	/**
	 * 파라미터가져오는 메소드
	 * @param req
	 * @return
	 */
	public Alert getParam(HttpServletRequest req);
	
	/**
	 * 알림 리스트
	 * 
	 * @param aler - userno가 필요
	 * @return
	 */
	public List<Alert> getAlertList(Alert alert);
	
	/**
	 * 프로필게시판에서 초대를 보내는 메소드
	 * @param alert
	 */
	public void sendInvite(Alert alert);

}
