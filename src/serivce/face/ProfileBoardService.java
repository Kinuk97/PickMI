package serivce.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.FreeBoard;
import dto.ProfileBoard;
import util.Paging;

public interface ProfileBoardService {
	
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
