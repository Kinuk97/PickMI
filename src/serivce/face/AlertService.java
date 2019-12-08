package serivce.face;

import dto.Alert;

public interface AlertService {
	/**
	 * 프로필 게시판에 글이 있는지 확인하기
	 * @param alert
	 * @return
	 */
	public int checkProfile(Alert alert);
	/**
	 * 프로젝트 팀에서 거절되는 알림
	 * @param alert
	 */
	public void sendAlertdenied(Alert alert);
	
	/**
	 * 프로필게시판에서 초대를 보내는 메소드
	 * @param alert
	 */
	public void sendInvite(Alert alert);

}
