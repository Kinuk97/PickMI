package serivce.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.CompBoard;
import dto.FreeBoard;
import dto.ProfileBoard;
import dto.ProjectBoard;
import dto.Reply;
import dto.User;
import util.Paging;

public interface MyPageService {

// ----- User info	-----

	/**
	 * email에 해당하는 사용자 정보 가져오기
	 * @param email - 사용자 이메일이 담겨있는 객체
	 * @return 입력받은 email에 해당하는 데이터
	 */
	public User getUser(String email);

	/**
	 * 회원정보 수정해주는 기능
	 * @param email
	 * @param pw
	 * @param name
	 */
	public boolean userUpdate(String email, String pw, String name);
// --------------------------------------
	
	/**
	 * 현재 페이지를 paging으로 반환하는 메소드
	 * @param req - 요청 정보 객체
	 * @return Paging - 페이징 처리 객체
	 */
//	public Paging getPaging(HttpServletRequest req);

	/**
	 * usertb에서 email 가져오기
	 * @param req
	 * @return
	 */
	public User getUserno(HttpServletRequest req);

	public User getcurPwParam(HttpServletRequest req);

	boolean eqPW(User pwparam);

	public void modifyPw(User pwparam);

	/**
	 * 게시글 삭제하는 기능
	 * 
	 * @param User - 사용자번호가 담겨있는 객체
	 * @return 삭제 성공 여부 반환
	 */
	public boolean userDelete(User user);
		
	public Paging getPaging(HttpServletRequest req, int i);
	
	
	/**
	 * 내가 작성한 게시글 리스트
	 * @param pfpaging
	 * @param user
	 * @param i
	 * @return
	 */
	public List getList(Paging pfpaging, User user, int i);
	
	
	/**
	 * 내가 찜한 리스트
	 * @param paging - 게시판 페이징
	 * @param user - userno
	 * @param i - postno
	 * @return
	 */
	public List getLikeList(Paging paging, User user, int i);

	/**
	 * 내 댓글 리스트
	 * @param paging
	 * @param userno
	 * @param i - postno
	 * @return
	 */
	public List getReplyList(Paging paging, User user, int i);

// -------------------- tES T ---------------------------------	
	
	public Paging getPaging(HttpServletRequest req);
	
//-----------------------------------------------------------------
	
}