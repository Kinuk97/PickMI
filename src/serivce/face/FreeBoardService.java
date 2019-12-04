package serivce.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.FreeBoard;
import util.Paging;

public interface FreeBoardService {

	/**
	 * 요청에서 받은 FreeBoard 꺼내주는 메소드
	 * 
	 * @param req - 서블릿이 받은 요청
	 * @return - 요청 파라미터가 담김 FreeBoard
	 */
	public FreeBoard getParam(HttpServletRequest req);

	/**
	 * 현재 페이지와 검색어, 카테고리를 Paging으로 반환하는 메소드
	 * 
	 * @param req - 받은 요청
	 * @return Paging - 페이징 처리 객체
	 */
	public Paging getPaging(HttpServletRequest req);

	/**
	 * 자유게시판의 게시글 목록
	 * 
	 * @param paging - 페이징 처리 객체
	 * @return List<FreeBoard> - 게시글 목록
	 */
	public List<FreeBoard> getBoardList(Paging paging);

	/**
	 * 게시글 작성 기능
	 * 파일 처리가 없는 글쓰기 (사용 x, FileService로 대체)
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

	/**
	 * 게시글 조회수 올리기
	 * 
	 * @param freeBoard - 게시글 번호가 담긴 DTO
	 */
	public void viewCounting(FreeBoard freeBoard);

	public List<FreeBoard> getMainFreeList();
}
