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
	public Paging getPaging(HttpServletRequest req);

	/**
	 * usertb에서 email 가져오기
	 * @param req
	 * @return
	 */
	public User getUserno(HttpServletRequest req);

	/**
	 * 페이징 정보를 활요앻 보여질 게시물 목록만 조회
	 * @param paging
	 * @param req
	 * @return
	 */
	public List<ProfileBoard> getpfList(Paging paging, HttpServletRequest req);
	public List<ProjectBoard> getpjList(Paging paging, HttpServletRequest req);
	public List<CompBoard> getcompList(Paging paging, HttpServletRequest req);
	public List<FreeBoard> getfreeList(Paging paging, HttpServletRequest req);


// ------ 비밀번호 수정

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
	
	/**
	 * 댓글 리스트
	 * @return
	 */
	public List<Reply> getReplyList(Paging paging);

	
	
}
