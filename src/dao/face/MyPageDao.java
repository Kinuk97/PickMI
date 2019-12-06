package dao.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.CompBoard;
import dto.FreeBoard;
import dto.ProfileBoard;
import dto.ProjectBoard;
import dto.Reply;
import dto.User;
import util.Paging;

public interface MyPageDao {

// ----- User Info -----
	// email에 해당하는 사용자 조회하기
	User selectOne(String email);

	public void updateUser(User user);
// ------------------------------------
	
	/**
	 * userno 조회
	 * @return
	 */
	public User selectUserbyUserno(HttpServletRequest req);

	/**
	 * 총 게시글 수 조회
	 * @param req
	 * @return
	 */
	public int selectCntAll(HttpServletRequest req);
	
	/**
	 * 페이징 대상 게시글 목록 조회
	 * @param paging - 페이징 조회
	 * @param req List - 조회된 회원 게시물 목록
	 * @return
	 */
	List<ProfileBoard> selectPf(Paging paging, HttpServletRequest req);

	List<ProjectBoard> selectPj(Paging paging, HttpServletRequest req);

	List<CompBoard> selectComp(Paging paging, HttpServletRequest req);

	List<FreeBoard> selectFree(Paging paging, HttpServletRequest req);


// ----- 비밀번호 수정
	
	int selectCntUserByupw(User pwparam);

	/**
	 * 사용자 삭제하는 메소드
	 * 
	 * @param user - 사용자 번호가 담겨있는 객체
	 * @return 쿼리 수행 결과
	 */
	public int deleteUser(User user);
	
	/**
	 * 댓글 조회
	 * @param paging
	 * @return
	 */
	List<Reply> selectReply(Paging paging);




}
