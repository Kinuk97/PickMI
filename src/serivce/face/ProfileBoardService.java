package serivce.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.ProfileBoard;
import sun.java2d.cmm.Profile;
import util.Paging;

public interface ProfileBoardService {
	/**
	 * 게시글 작성하기
	 * @param req
	 */
	public void write(HttpServletRequest req);
	
	/**
	 * 게시글 상세정보 불러오기
	 * @param profile
	 * @return
	 */
	public ProfileBoard view(ProfileBoard profile);
	
	/**
	 * 요청 파라미터 파싱
	 * @param req - 요청 정보 객체
	 * @return Board - 요청정보에 포함된 전달 파라미터
	 */
	public ProfileBoard getProfileno(HttpServletRequest req);
	
	/**
	 * 페이징을 이용한 전체 목록 가져오기
	 * @param paging
	 * @return - 페이징을 이용한 목록보기
	 */
	public List<ProfileBoard> getBoardList(Paging paging);
	
	/**
	 * profileBoard의 모든 게시글을 불러온다
	 * @return - 게시글목록
	 */
	public List<ProfileBoard> getProfileList();

	/**
	 * 요청 파라미터 curPage를 파싱한다
	 * @param req - 요청정보객체
	 * @return - 페이징정보
	 */
	public Paging getPaging(HttpServletRequest req);
}
