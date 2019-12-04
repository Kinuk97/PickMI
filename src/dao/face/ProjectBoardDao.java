package dao.face;

import java.util.List;

import dto.LikePost;
import dto.ProjectBoard;
import util.Paging;

public interface ProjectBoardDao {
	
	/**
	 * 게시글 작성 전 다음 게시글 번호 가져오기
	 * 
	 * @return int - 다음 게시글 번호
	 */
	public int getNextBoardno();
	
	/**
	 * 게시글 수 조회
	 * @return int - 게시글 총 개수
	 */
	public int selectCntAll(String proj_loc, String proj_progress, String proj_job, String proj_career);
	
	/**
	 * 게시글 목록
	 * 
	 * @param paging - 페이징 처리 객체
	 * @return List<ProjectBoard> - 게시글 리스트
	 */
	public List<ProjectBoard> selectAll(Paging paging);

	public ProjectBoard selectBoardByProjectno(ProjectBoard projectBoard);
	
	/**
	 * 게시글 작성
	 * 
	 * @param projectBoard - 게시글 작성 내용
	 */
	public void insert(ProjectBoard projectBoard);
	
	/**
	 * 게시글 삭제
	 * @param projectBoard
	 */
	public void deleteProjBoard(ProjectBoard projectBoard);
	
	/**
	 * 게시글 수정
	 * 
	 * @param projectBoard
	 * @return
	 */
	public int updateBoard(ProjectBoard projectBoard);
	
	
	// ------------ 찜하기 ---------------
	
	/**
	 * 찜 삭제
	 * 
	 * @param like - 찜 정보
	 */
	public void deleteLike(LikePost like);
	
	/**
	 * 찜 기록
	 * 
	 * @param like - 찜 정보
	 */
	public void insertLike(LikePost like);
	
	/**
	 * 게시글 찜상태를 위한 COUNT 조회
	 * 
	 * @param like - 찜 상태 정보 저장 객체
	 * @return int - 찜 상태 확인 값
	 */
	public int selectCntLike(LikePost like);
	
	/**
	 * 찜 여부 확인
	 * 
	 * @param like
	 * @return
	 */
	public int checkCntByUserno(LikePost like);
	
	// ------------------------------------
	
	/**
	 * 리스트 출력
	 * @return
	 */
	public List<ProjectBoard> selectListToMain();

	

}
