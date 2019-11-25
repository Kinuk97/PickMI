package serivce.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.FreeBoard;

public interface FreeBoardService {

	/**
	 * 요청에서 받은 FreeBoard 꺼내주는 메소드
	 * 
	 * @param req - 서블릿이 받은 요청
	 * @return - 요청 파라미터가 담김 FreeBoard
	 */
	public FreeBoard getParam(HttpServletRequest req);

	/**
	 * 자유게시판의 게시글 목록
	 * 
	 * @return List<FreeBoard> - 게시글 목록
	 */
	public List<FreeBoard> getBoardList();

	/**
	 * 카테고리로 분류한 게시글 목록
	 * 
	 * @param categoryno - 카테고리 종류
	 * @return List<FreeBoard> - 게시글 목록
	 */
	public List<FreeBoard> getBoardListByCategory(int categoryno);

	/**
	 * 게시글 검색하는 기능
	 * 
	 * @param searchStr - 검색 문자열
	 * @param filter    - 제목, 작성자, 제목&&내용 필터
	 * @return List<FreeBoard> - 검색 게시글 목록
	 */
	public List<FreeBoard> searchBoard(String searchStr, int filter);

	/**
	 * 게시글 작성 기능
	 * 
	 * @param freeBoard - 게시글 작성 내용이 담겨있는 객체
	 * @return 작성 성공 여부 반환
	 */
	public boolean writeBoard(FreeBoard freeBoard);

	/**
	 * 게시글 수정하는 기능
	 * 
	 * @param freeBoard - 수정 내용이 담겨있는 객체
	 * @return 수정 성공 여부 반환
	 */
	public boolean modifyBoard(FreeBoard freeBoard);

	/**
	 * 게시글 삭제하는 기능
	 * 
	 * @param FreeBoard - 게시글 번호가 담겨있는 객체
	 * @return 삭제 성공 여부 반환
	 */
	public boolean removeBoard(FreeBoard freeBoard);

	/**
	 * 게시글 상세보기
	 * 
	 * @param freeBoard
	 * @return FreeBoard - 선택한 게시글 상세내용
	 */
	public FreeBoard FreeBoardDetail(FreeBoard freeBoard);
}
