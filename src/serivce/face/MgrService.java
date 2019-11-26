package serivce.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Manager;
import dto.ProfileBoard;
import util.Paging;

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
		
	/**
	 * 게시글 목록 조회
	 * @return List<ProfileBoard> 프로필게시글 목록
	 */
	public List<ProfileBoard> getpbList();

	/**
	 * 요청파라미터 curPage를 파싱
	 * @param req - 요청 정보 객체
	 * @return Paging - 페이징 정보
	 */
	public Paging getPaging(HttpServletRequest req);

	/**
	 * 페이징 정보를 활용하여 보여질 게시글 목록만 조히
	 * @param paging - 페이징 정보
	 * @return pbList - 프로필 게시글 목록
	 */
	public List<ProfileBoard> getpbList(Paging paging);
	
	
}
