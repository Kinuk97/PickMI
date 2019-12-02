package dao.face;

import java.util.List;

import dto.ProjectBoard;
import util.Paging;

public interface ProjectBoardDao {
	
	/**
	 * 게시글 작성 전 다음 게시글 번호 가져오기
	 * 
	 * @return int - 다음 게시글 번호
	 */
	public int getNextBoardno();
	
	public int selectCntAll();
	
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
	
//	/**
//	 * 지역필터에 의한 게시판 조회
//	 * 
//	 * @param paging
//	 * @return
//	 */
//	public List<ProjectBoard> selectBoardListByLoc(Paging paging);
}
