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
	/**
	 * 회원가입에 필요한 파라미터 값 받기
	 * 
	 * @param req - 요청받은 파라미터 값(email,pw,name)
	 * @return user - 회원가입 정보
	 */
	User getJoinParam(HttpServletRequest req);
	
	/**
	 * 회원가입
	 * 
	 * @param user - 회원가입 정보
	 */
	void join(User user);
	
	/**
	 * 이메일 중복검사
	 * @param user - 이메일 
	 * @return boolean - true : 중복 & false : 중복이 아님
	 */
	boolean emailCheck(User user);
	
	/**
	 * 유저 번호 가져오기
	 * @param user - 이메일
	 * @return user - 유저번호
	 */
	User getUserno(User user);
	
	/**
	 * 비밀번호 찾기에 필요한 파라미터 받기
	 * 
	 * @param req - 요청받은 파라미터 값
	 * @return - 이메일, 이름
	 */
	User getPwParam(HttpServletRequest req);
	
	/**
	 * 비밀번호 확인
	 * @param user
	 * @return User - 비밀번호
	 */
	User findPw(User user);
	
	/**
	 * 새로운 비밀번호를 받는다.
	 * 
	 * @param req
	 * @return
	 */
	User getNewParam(HttpServletRequest req);
	
	/**
	 * 비밀번호 변경
	 * 
	 * @param newPw
	 * @return
	 */
	public void updatePw(User newPw);
	
	
	

}
