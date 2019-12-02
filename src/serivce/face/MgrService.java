package serivce.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.FreeBoard;
import dto.Manager;
import dto.ProfileBoard;
import dto.ProjectBoard;
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
	 * 요청에서 받은 User를 꺼내주는 메소드
	 * @param req - 서블릿이 받은 요청
	 * @return - 요청 파라미터가 담김 User
	 */
	public User getParam(HttpServletRequest req);
	

	/**
	 * 현재 페이지와 검색어를 paging으로 반환하는 메소드
	 * @param req - 요청 정보 객체
	 * @return Paging - 페이징 처리 객체
	 */
	public Paging getPaging(HttpServletRequest req);
	
//	/**
//	 * user리스트 목록 조회 
//	 * @return List<User> - user리스트 목록
//	 */
//	public List<User> getuserList();


	/**
	 * 페이징 정보를 활용하여 보여질 유저 목록만 조회
	 * @param paging - 페이징 정보
	 * @return uList - 유저리스트 목록
	 */
	public List<User> getuserList(Paging paging);


	/**
	 * 관리자가 User 탈퇴 시키는 기능 
	 * @param user - 유저 번호가 담겨있는 객체
	 * @return 삭제 성공 여부 반환
	 */
	public boolean removeUser(User user);
	

// UserList ----- 

// ----- ProfileList
	
	/**
	 * 요청에서 받은 ProfileBoard 꺼내주는 메소드
	 * 
	 * @param req - 서블릿이 받은 요청
	 * @return - 요청 파라미터가 담김 ProfileBoard
	 */
	public ProfileBoard getPfParam(HttpServletRequest req);


//	public List<ProfileBoard> getPfBoardList();
		
	/**
	 * 프로필게시판의 게시글 목록
	 * @param paging - 페이징 처리 객체
	 * @return List<ProfileBoard> - 게시글 목록
	 */
	public List<ProfileBoard> getPfBoardList(Paging paging);
	
// ProjectList -----

	public List<ProjectBoard> getPjBoardList();
	
	public List<ProjectBoard> getPjBoardList(Paging paging);

//	public void listdeleteComp(HttpServletRequest req);
	
// ----- ProjectList



}
