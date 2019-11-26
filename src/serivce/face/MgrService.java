package serivce.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Manager;
import dto.ProfileBoard;
import dto.User;
import util.Paging;

public interface MgrService {

	
//----- Login
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
// Login -----		

// ----- UserList
	/**
	 * 요청파라미터 curPage를 파싱
	 * @param req - 요청 정보 객체
	 * @return Paging - 페이징 정보
	 */
	public Paging getPaging(HttpServletRequest req);
	
	/**
	 * user리스트 목록 조회 
	 * @return List<User> - user리스트 목록
	 */
	public List<User> getuserList();

	/**
	 * 페이징 정보를 활용하여 보여질 유저 목록만 조회
	 * @param paging - 페이징 정보
	 * @return uList - 유저리스트 목록
	 */
	public List<User> getuserList(Paging paging);

// UserList ----- 
	
}
