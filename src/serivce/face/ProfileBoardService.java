package serivce.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.LikePost;
import dto.ProfileBoard;
import util.Paging;

public interface ProfileBoardService {
	/**
	 * 게시글을 삭제한다
	 * @param profile
	 */
	public void removeProfile(ProfileBoard profile);
	
	/**
	 * 좋아요를 누를때마다 숫자 증가
	 * @param like
	 */
	public void like(LikePost like);
	
	/**
	 * 찜을 한 적이 있는지 확인하기
	 * @param like
	 * @return
	 */
	public boolean checkLike(LikePost like);
	
	/**
	 * 찜 받은 갯수 가져오기
	 * @param like
	 * @return
	 */
	public int countLike(LikePost like);
	
	/**
	 * 유저 번호로 유저 이름 가져오기
	 * @param profile
	 * @return
	 */
	public ProfileBoard getNameByUserno(int userno);
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
