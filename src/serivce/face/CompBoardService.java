package serivce.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.CompBoard;

public interface CompBoardService {
	
	/**
	 * 요청에서 받은 CompBoard를 꺼내주는 메소드
	 * 
	 * @param req - Servlet이 받은 요청
	 * @return - 요청파라미터가 담긴 CompBoard
	 */
	public CompBoard getParam(HttpServletRequest req);
	
	/**
	 * 완성된 프로젝트 게시판의 게시글 목록 List
	 * 
	 * @return List<CompBoard> - 게시글 목록
	 */
	public List<CompBoard> getBoardList();
	
	/**
	 * 게시글을 검색할 수 있는 검색기능
	 * 
	 * @param searchStr - 검색 문자열
	 * @param categoryNo - 작성자, 제목, 제목+내용 카테고리
	 * @return List<CompBoard> - 검색 게시글 목록
	 */
	public List<CompBoard> searchBoard(String searchStr, int categoryNo);
	
	/**
	 * 게시글을 작성할 수 있는 기능
	 * 
	 * @param compBoard - 게시글을 작성한 내용이 담겨있는 객체
	 * @return - 작성 성공 여부 반환 (true/false)
	 */
	public boolean writeBoard(CompBoard compBoard);
	
	/**
	 * 게시글을 수정하는 기능
	 * 
	 * @param compBoard - 수정 내용이 담겨있는 객체
	 * @return - 수정 성공 여부 반환 (true/false)
	 */
	public boolean modifyBoard(CompBoard compBoard);
	
	/**
	 * 게시글을 삭제하는 기능
	 * 
	 * @param comp_no - 게시글의 번호가 담겨있는 객체
	 * @return - 삭제 성공 여부 반환 (true/false)
	 */
	public boolean deleteBoard(int comp_no);
	
	/**
	 * 게시글 상세보기
	 * 
	 * @param comp_no - 게시글의 번호가 담겨있는 객체
	 * @return CompBoard - 상세보기를 원하는 게시글의 상세 내용
	 */
	public CompBoard compBoardDetail(int comp_no);
	
	/**
	 * 찜한 게시글 목록
	 * 
	 * @param compBoard
	 */
	public void boardLikePost(CompBoard compBoard);
	

}
