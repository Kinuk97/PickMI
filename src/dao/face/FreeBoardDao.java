package dao.face;

import java.util.List;

import dto.FreeBoard;
import util.Paging;

public interface FreeBoardDao {

	/**
	 * 게시글 작성 전 다음 게시글 번호 가져오는 메소드
	 * 
	 * @return 다음 게시글 번호
	 */
	public int getNextBoardno();
	
	/**
	 * 페이징 처리를 위한 게시글 수 가져오기
	 * 
	 * @param search - 검색어
	 * @param categoryno - 카테고리 번호
	 * @return int - 게시글 수
	 */
	public int selectCntAll(String search, int categoryno);

	/**
	 * 게시글 목록 불러오기
	 * 
	 * @param paging - 페이징 처리를 위한 객체
	 * @return List<FreeBoard> - 게시글 리스트
	 */
	public List<FreeBoard> selectAll(Paging paging);

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
