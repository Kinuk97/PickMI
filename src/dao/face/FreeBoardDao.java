package dao.face;

import java.util.List;

import dto.FreeBoard;

public interface FreeBoardDao {

	/**
	 * 게시글 작성 전 다음 게시글 번호 가져오는 메소드
	 * 
	 * @return 다음 게시글 번호
	 */
	public int getNextBoardno();

	/**
	 * 자유게시판 게시글 전체목록 가져오는 메소드
	 * 
	 * @return List<FreeBoard> - 전체 게시글 목록
	 */
	public List<FreeBoard> selectAll();

	/**
	 * 자유게시판 제목으로 검색하는 메소드
	 * 
	 * @return List<FreeBoard> - 검색 게시글 목록
	 */
	public List<FreeBoard> selectBoardByTitle(FreeBoard freeBoard);

	/**
	 * 자유게시판 작성자로 검색하는 메소드
	 * 
	 * @return List<FreeBoard> - 검색 게시글 목록
	 */
	public List<FreeBoard> selectBoardByUser(FreeBoard freeBoard);

	/**
	 * 자유게시판 제목&&내용으로 검색하는 메소드
	 * 
	 * @return List<FreeBoard> - 검색 게시글 목록
	 */
	public List<FreeBoard> selectBoardByTitleContent(FreeBoard freeBoard);

	/**
	 * 자유게시판 카테고리로 게시글 가져오는 메소드
	 * 
	 * @return List<FreeBoard> - 카테고리 게시글 목록
	 */
	public List<FreeBoard> selectBoardByCategory(FreeBoard freeBoard);

	/**
	 * 게시글 작성하는 메소드
	 * 
	 * @param freeBoard - 게시글 내용
	 * @return 쿼리 수행 결과
	 */
	public int insertBoard(FreeBoard freeBoard);

	/**
	 * 게시글 수정하는 메소드
	 * 
	 * @param freeBoard - 게시글 수정 내용
	 * @return 쿼리 수행 결과
	 */
	public int updateBoard(FreeBoard freeBoard);

	/**
	 * 게시글 삭제하는 메소드
	 * 
	 * @param freeBoard - 게시글 번호가 담겨있는 객체
	 * @return 쿼리 수행 결과
	 */
	public int deleteBoard(FreeBoard freeBoard);

	/**
	 * 게시글 내용을 가져오는 메소드
	 * 
	 * @param freeBoard
	 * @return
	 */
	public FreeBoard boardView(FreeBoard freeBoard);

	/**
	 * 조회수 올리는 메소드
	 * 
	 * @param freeBoard - 게시글 번호가 담겨있는 객체
	 */
	public void countViews(FreeBoard freeBoard);

}
