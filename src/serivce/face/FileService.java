package serivce.face;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Files;
import dto.User;

public interface FileService {

	/**
	 * 게시글과 파일을 작성하는 기능
	 * 
	 * @param req    - 파일과 파라미터가 담겨있는 요청객체
	 * @param postno - 게시판 번호
	 * @return int - 수정한 게시글 번호
	 */
	public int writeBoard(HttpServletRequest req, int postno);

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
	 * @return int - 수정한 게시글 번호
	 */
	public int modifyBoard(HttpServletRequest req, int postno);

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
	 * @param req  - 요청객체
	 * @param resp - 응답객체
	 */
	public void fileDownload(HttpServletRequest req, HttpServletResponse resp);
	
	/**
	 * 마이페이지에서 프로필사진 올리는 기능
	 * @param req, User user - 파일과 파라미터가 담겨있는 객체
	 */
	public void myPhotoFile(HttpServletRequest req, User user); 
	
	/**
	 * 사용사 사진 DB,서버에서 지우기
	 * @param user - userno, 사진경로
	 */
	public void myPhotoDelete(String path, User user);
	
	
}
