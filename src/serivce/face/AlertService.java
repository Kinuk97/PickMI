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
	 * 나를 초대한 팀장 리스트 불러오기
	 * @param alert
	 * @return
	 */
	public List<Alert> invitedList(Alert alert);
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
