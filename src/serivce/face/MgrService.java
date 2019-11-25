package serivce.face;

import javax.servlet.http.HttpServletRequest;

import dto.Manager;

public interface MgrService {

	/**
	 * 로그인 정보 파싱
	 * @param req
	 * @return Manger - 로그인 정보
	 */
	public Manager getLoginMgr(HttpServletRequest req);

	/**
	 * 
	 * @param mgr - 로그인 정보
	 * @return boolean - true( 인증 ), false( 비인증 )
	 */
	public boolean login(Manager mgr);
	
	/**
	 * 관리자 정보 가져오기
	 * @param mgr - 관리자 아이디를 가진 객체
	 * @return Manager - 조회된 관리자
	 */
	public Manager getMgrByMgrid(Manager mgr);
		

	
	
}
