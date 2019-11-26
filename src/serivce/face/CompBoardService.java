package serivce.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CompBoard;
import util.Paging;

public interface CompBoardService {
	/**
	 * 요청 파라미터 curPage를 파싱한다.
	 * compBoard테이블과 curPage값을 이용한 Paging객체를 생성하고 반환
	 * 
	 * @param req - 요청정보 객체
	 * @return Paging - 페이징 정보
	 */
	public Paging getPaging(HttpServletRequest req);
	
	/**
	 * 요청에서 받은 CompBoard를 꺼내주는 메소드
	 * 
	 * @param req - Servlet이 받은 요청
	 * @return - 요청파라미터가 담긴 CompBoard
	 */
	public CompBoard getParam(HttpServletRequest req);
	
	/**
	 * 완성된 프로젝트 게시판의 게시글 목록 List
	 * @param paging 
	 * 
	 * @return List<CompBoard> - 게시글 목록
	 */
	public List<CompBoard> getBoardList(Paging paging);
	
	/**
	 * 게시글 상세 보기
	 * 
	 * @param compBoard
	 * @return compBoardDao.
	 */
	public CompBoard compBoardDetail(CompBoard compBoard);
	
	/**
	 * 게시글 작성
	 * 
	 * @param compBoard
	 */
	public void write(CompBoard compBoard);

	
}
