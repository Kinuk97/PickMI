package dao.face;

import java.util.List;

import dto.Files;
import dto.ProfileBoard;
import util.Paging;

public interface ProfileBoardDao {
	
	/**
	 * 유저 번호로 유저 이름 받아오기
	 * @param profile
	 * @return
	 */
	public ProfileBoard selectNameByUserno(ProfileBoard profile);
	
	/**
	 * 프로필 테이블에 프로필을 저장한다
	 * @param profile
	 */
	public void insertProfile(ProfileBoard profile);
	
	/**
	 * 파일 테이블에 파일을 저장한다
	 * @param file
	 */
	public void insertFile(Files file);
	
	/**
	 * profileno와 fileno를 맞추기 위해 필요함
	 * @return
	 */
	public int selectProfileno();
	
	/**
	 * 게시글 번호로 프로필 상세보기
	 * @param profile
	 * @return
	 */
	public ProfileBoard selectProfileByProfileno(ProfileBoard profile);
	
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

