package serivce.face;

import java.util.List;

import dto.ProfileBoard;

public interface ProfileBoardService {
	/**
	 * profileBoard의 모든 게시글을 불러온다
	 * @return - 게시글목록
	 */
	public List<ProfileBoard> getProfileList();

}
