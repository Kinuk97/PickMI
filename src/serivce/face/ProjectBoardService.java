package serivce.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.LikePost;
import dto.ProjectBoard;
import util.Paging;

public interface ProjectBoardService {
	
	/**
	 * 요청 파라미터 curPage를 파싱한다.
	 * projectBoard테이블과 curPage값을 이용한 Paging객체를 생성하고 반환
	 * 
	 * @param req - 요청정보 객체
	 * @return Paging - 페이징 정보
	 */
	public Paging getPaging(HttpServletRequest req);
	
	/**
	 * 프로젝트 게시판 List
	 * 
	 * @param paging
	 * @return List<ProjectBoard> - 프로젝트 게시판 목록
	 */
	public List<ProjectBoard> getBoardList(Paging paging);
	
	/**
	 * 요청 파마리터로 게시판 번호를 파싱한다.
	 * 
	 * @param req - 요청정보 객체
	 * @return ProjectBoard - 게시판 번호
	 */
	public ProjectBoard getProjectBoardno(HttpServletRequest req);
	
	/**
	 * 게시판 상세 보기
	 * 
	 * @param projectBoard
	 * @return ProjectBoard
	 */
	public ProjectBoard view(ProjectBoard projectBoard);
	
	/**
	 * 게시판 삭제
	 * @param projectBoard
	 */
	public void delete(ProjectBoard projectBoard);
	
	/**
	 * 추천 정보 파라미터 얻기
	 * 
	 * @param req - 요청 정보 객체
	 * @return LikePost - 추천 정보 객체
	 */
	public LikePost getLike(HttpServletRequest req);
	
	/**
	 * 추천 토글
	 * 
	 * @param like - 추천 정보 객체
	 * @return boolean - true:추천 성공, false:추천취소 성공
	 */
	public boolean like(LikePost like);
	
	/**
	 * 게시글의 총 추천 수 구하기
	 * 
	 * @param board - 해당 게시글
	 * @return int - 총 추천 수
	 */
	public int getTotalCntLike(LikePost like);
	
	public boolean isLike(LikePost like);
	
}
