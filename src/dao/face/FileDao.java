package dao.face;

import dto.Files;

public interface FileDao {

	/**
	 * 파일정보를 DB에 넣는 쿼리
	 * 
	 * @param uploadFile - 파일정보
	 */
	public void insertFile(Files uploadFile);

	/**
	 * 파일 정보를 불러오는 쿼리
	 * 
	 * @param files - 게시판 번호, 게시글 번호가 담겨있는 객체
	 * @return Files - 조회된 파일 정보
	 */
	public Files selectFile(Files file);

	/**
	 * 파일번호로 파일 조회
	 * 
	 * @param file - 파일번호가 담긴 DTO
	 * @return Files - 조회결과
	 */
	public Files selectFileByFileno(Files file);

	/**
	 * 파일을 DB에서 삭제하는 쿼리
	 * 
	 * @param files - 게시판 번호, 게시글 번호가 담겨있는 객체
	 */
	public void deleteFile(Files file);
}
