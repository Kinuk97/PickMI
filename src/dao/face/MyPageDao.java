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

	
// ------ 페이징	
	
	/**
	 * 총 게시글 수 조회
	 * @param req
	 * @return
	 */
	public int selectpfCntAll(HttpServletRequest req);
	

// ----- 비밀번호 수정
	
	int selectCntUserByupw(User pwparam);

	/**
	 * 사용자 삭제하는 메소드
	 * 
	 * @param user - 사용자 번호가 담겨있는 객체
	 * @return 쿼리 수행 결과
	 */
	public int deleteUser(User user);

	public int selectCntAll(HttpServletRequest req, int i);
	
	/**
	 * 작성한 게시글 리스트
	 * @param paging - 각 게시판 페이징
	 * @param user - userno
	 * @param i - postno
	 * @return
	 */
	public List selectboard(Paging paging, User user, int i);

	
	/**
	 * 찜한 게시글 리스트
	 * @param paging - 각 게시판 페이징
	 * @param user - userno
	 * @param i - postno
	 * @return
	 */
	public List likeboard(Paging paging, User user, int i);

	/**
	 * 작성한 댓글 리스트
	 * @param paging - 각 게시판 페이징
	 * @param user - userno
	 * @param i - postno
	 * @return
	 */
	public List writeReply(Paging paging, User user, int i);

	
	// ---------------------- tES T --------------------
	public int selectCntAll(HttpServletRequest req);



}
