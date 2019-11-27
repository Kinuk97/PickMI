package dao.face;

import java.util.List;

import dto.CompBoard;
import dto.Files;
import util.Paging;

public interface CompBoardDao {
	
	/**
	 * 페이징 처리를 위한 게시글 수 가져오기
	 * 
	 * @param search - 검색어
	 * @param categoryno - 카테고리 번호
	 * @return int - 게시글 수
	 */
	public int selectCntAll(String search, int categoryno);
	
	/**
	 * comp_no를 이용해 게시글 세부사항 조회
	 * 
	 * @param compBoard - 게시글 번호
	 * @return compBoard - 게시글 상세 정보
	 */
	public CompBoard boardViewByComp_no(CompBoard compBoard);
	
	/**
	 * 페이징 대상 게시글 정보 조회
	 * 
	 * @param paging - 페이징 정보
	 * @return List - 조회된 게시글 목록
	 */
	public List<CompBoard> compList(Paging paging);
	
	/**
	 * 게시글 작성
	 * 
	 * @param compBoard
	 */
	public void insert(CompBoard compBoard);
	
	/**
	 * 각 게시글의 조회수
	 * 
	 * @param compBoard
	 */
	public void countViews(CompBoard compBoard);
	
	
	public int selectCompBoardno();
	
	
	public void insertFile(Files files);

}
