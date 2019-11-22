package serivce.face;

import javax.servlet.http.HttpServletRequest;

import dto.User;

public interface UserService {
	
	/**
	 * 로그인에 필요한 파마리터 값 받기
	 * 
	 * @param req - 요청받은 파라미터 값(email,pw)
	 * @return user - 로그인 정보
	 */
	User getLoginParam(HttpServletRequest req);
	
	/**
	 * 로그인 처리
	 * 
	 * @param user - 로그인 정보
	 * @return boolean - true & false로 로그인 인증
	 */
	boolean login(User user);

}
