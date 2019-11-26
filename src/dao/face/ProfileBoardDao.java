package dao.face;

import java.util.List;

import dto.ProfileBoard;
import util.Paging;

public interface ProfileBoardDao {
	
	/**
	 * 페이징을 이용한 게시글 조회
	 * @return
	 */
	public List<ProfileBoard> selectAll(Paging paging);

	
//	public selectNameByUserno();
	
	/**
	 * profileboard의 모든 게시글을 불러온다
	 * @return
	 */
	public List<ProfileBoard> selectProfileList();

	/**
	 * 게시글 의 수 조회
	 * @return int - 총 게시글 수
	 */
	public int selectCntAll();
}

