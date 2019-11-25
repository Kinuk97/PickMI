package dao.face;

import dto.CompBoard;

public interface CompBoardDao {
	
	/**
	 * 게시글의 총 개수 
	 * 
	 * @return int - 총 게시글 수
	 */
	public int selectCntAll();
	
	/**
	 * comp_no를 이용해 게시글 세부사항 조회
	 * 
	 * @param compBoard - 게시글 번호
	 * @return compBoard - 게시글 상세 정보
	 */
	public CompBoard boardViewByComp_no(CompBoard compBoard);

}
