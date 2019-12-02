package serivce.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

}
