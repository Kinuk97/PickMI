package serivce.face;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Files;

public interface FileService {

	/**
	 * 게시글과 파일을 작성하는 기능
	 * 
	 * @param req    - 파일과 파라미터가 담겨있는 요청객체
	 * @param postno - 게시판 번호
	 */
	public void writeBoard(HttpServletRequest req, int postno);

	/**
	 * 파일을 삭제하는 기능 (DB + 서버)
	 * 
	 * @param path    - 서버의 업로드 폴더
	 * @param postno  - 게시판 번호
	 * @param boardno - 게시글 번호
	 */
	public void deleteFile(String path, int postno, int boardno);

	/**
	 * 게시글 수정하는 기능
	 * 
	 * @param req    - 파일과 파라미터가 담겨있는 요청
	 * @param postno - 게시판 번호
	 */
	public void modifyBoard(HttpServletRequest req, int postno);

	/**
	 * 게시글의 파일정보를 반환하는 메소드
	 * 
	 * @param file - 게시판번호와 게시글번호가 담겨있는 DTO
	 * @return Files - 파일 DTO
	 */
	public Files getFiles(Files file);

	/**
	 * 파일 다운로드 응답을 하는 기능
	 * 
	 * @param req - 요청객체
	 * @param resp - 응답객체
	 */
	public void fileDownload(HttpServletRequest req, HttpServletResponse resp);
}
