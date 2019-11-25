package serivce.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.ProfileBoard;
import util.Paging;

public interface ProfileBoardService {
	
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
