package serivce.face;

import dto.Alert;

public interface AlertService {
	
	/**
	 * 프로필게시판에서 초대를 보내는 메소드
	 * @param alert
	 */
	public void sendInvite(Alert alert);

}
