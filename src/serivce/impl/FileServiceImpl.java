package serivce.impl;

import java.io.BufferedInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import dao.face.CompBoardDao;
import dao.face.FileDao;
import dao.face.FreeBoardDao;
import dao.face.ProfileBoardDao;
import dao.impl.CompBoardDaoImpl;
import dao.impl.FileDaoImpl;
import dao.impl.FreeBoardDaoImpl;
import dao.impl.ProfileBoardDaoImpl;
import dto.CompBoard;
import dto.Files;
import dto.FreeBoard;
import dto.ProfileBoard;
import serivce.face.FileService;

public class FileServiceImpl implements FileService {
	private FreeBoardDao freeBoardDao = FreeBoardDaoImpl.getInstance();
	private ProfileBoardDao profileBoardDao = ProfileBoardDaoImpl.getInstance();
	private CompBoardDao compBoardDao = new CompBoardDaoImpl();

	private FileDao fileDao = FileDaoImpl.getInstance();

	private FileServiceImpl() {
	}

	private static class Singleton {
		private static final FileService instance = new FileServiceImpl();
	}

	public static FileService getInstance() {
		return Singleton.instance;
	}

	@Override
	public int writeBoard(HttpServletRequest req, int postno) {
		// 1. 파일업로드 형태의 데이터가 맞는지 확인
		// enctype이 multipart/form-data가 맞는지 확인
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);

		// 1-1 multipart/form-data 인코딩으로 전송되지 않았을 경우
		if (!isMultipart) {
			return - 1;
		}

		// 각자 만들어야하는 부분
		// =========================================================================================================
		CompBoard compBoard = null;
		FreeBoard freeBoard = null;
		ProfileBoard profile = null;
		Files uploadFile = null;
		
		int resultBoardno = 0;

		// 1-2 여기 이후는 multipart/form-data로 전송된 상황
		// 파일이 전송된 상황

		// 2. 업로드된 파일을 처리하는 아이템팩토리 객체 생성
		// ItemFactory : 업로드된 파일을 처리하는 방식을 정하는 클래스

		// FileItem : 클라이언트로부터 전송된 데이터를 객체화시킨 것

		// DiskFileItemFactory class - > 디스크기반(HDD)의 파일 아이템 처리 API
		// 업로드된 파일을 디스크에 임시 저장하고 후처리한다.
		DiskFileItemFactory factory = null;
		// 생성자로 필요한 객체를 넘겨줄 수 있지만 따로 설정
		factory = new DiskFileItemFactory();

		// 3. 업로드된 아이템이 용량이 작으면 메모리에서 처리
		int maxMem = 1 * 1024 * 1024; // 1MB
		factory.setSizeThreshold(maxMem);

		// 4. 용량이 적당히 크면 임시파일을 만들어서 처리 (디스크)
		ServletContext context = req.getServletContext();
		// 가상경로인 매개변수를 실제 경로로 만들어준다.
		String path = context.getRealPath("tmp");
		File repository = new File(path);
		factory.setRepository(repository);

		// 5. 업로드 허용 용량 기준을 넘지 않을 경우에만 업로드 처리
		int maxFile = 10 * 1024 * 1024; // 10MB
		// 파일 업로드 객체 생성 - DiskFileItemFactory 이용
		ServletFileUpload upload = null;
		upload = new ServletFileUpload(factory);
		// 파일 업로드 용량 제한 설정 : 10MB
		upload.setFileSizeMax(maxFile);

		// --- 파일 업로드 준비 완료 ---

		// 6. 업로드된 데이터 추출(파싱)
		// 임시 파일 업로드도 같이 수행함
		List<FileItem> items = null;

		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		// 7. 파싱된 데이터 처리하기
		// items 리스트에 요청 파라미터가 파싱되어있음

		// 요청정보의 형태 3가지
		// 1. 빈 파일 (용량이 0인 파일)
		// 2. form-data (일반적인 요청 파라미터)
		// 3. 파일

		Iterator<FileItem> iter = items.iterator();

		// 모든 요청 정보 처리
		while (iter.hasNext()) {
			FileItem item = iter.next();

			// 1) 빈 파일 처리
			if (item.getSize() <= 0)
				continue;

			if (item.isFormField()) {
				String key = item.getFieldName();

				// 각자 만들어야하는 부분
				// =========================================================================================================
				if (postno == 1) {
					// 프로필 게시판
					if(profile == null)
						profile = new ProfileBoard();
					
					if ("prof_interest".equals(key)) {
						try {
							profile.setProf_interest(item.getString("utf-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					} else if ("prof_job".equals(key)) {
						try {
							profile.setProf_job(item.getString("utf-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					} else if ("prof_state".equals(key)) {
						try {
							profile.setProf_state(item.getString("utf-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					} else if ("prof_loc".equals(key)) {
						try {
							profile.setProf_loc(item.getString("utf-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					} else if ("prof_career".equals(key)) {
						try {
							profile.setProf_career(item.getString("utf-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					} else if ("prof_content".equals(key)) {
						try {
							profile.setProf_content(item.getString("utf-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}

				} else if (postno == 2) {
					// 프로젝트 게시판

				} else if (postno == 3) {
					// 자유게시판이라면...
					if (freeBoard == null)
						freeBoard = new FreeBoard();
					if ("free_title".equals(key)) {
						try {
							freeBoard.setFree_title(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}

					} else if ("free_content".equals(key)) {
						try {
							freeBoard.setFree_content(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}

					} else if ("categoryno".equals(key)) {
						try {
							freeBoard.setCategoryno(Integer.parseInt(item.getString("UTF-8")));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
					}
					
				} else if (postno == 4) {
					if (compBoard == null)
						compBoard = new CompBoard();

					if ("comp_title".equals(key)) {
						try {
							compBoard.setComp_title(item.getString("utf-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					} else if ("comp_name".equals(key)) {
						try {
							compBoard.setComp_name(item.getString("utf-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					} else if ("comp_member".equals(key)) {
						try {
							compBoard.setComp_member(Integer.parseInt(item.getString("utf-8")));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}

					} else if ("comp_startdate".equals(key)) {
						try {
							compBoard.setComp_startdate(Integer.parseInt(item.getString("utf-8")));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}

					} else if ("comp_enddate".equals(key)) {
						try {
							compBoard.setComp_enddate(Integer.parseInt(item.getString("utf-8")));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}

					} else if ("comp_content".equals(key)) {
						try {
							compBoard.setComp_content(item.getString("utf-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					} // key값 비교 if
				}

			} else {
				UUID uuid = UUID.randomUUID(); // 랜덤 UID 생성

				String u = uuid.toString().split("-")[4];

				File up = new File(context.getRealPath("upload"), item.getName() + "_" + u);

				uploadFile = new Files();

				uploadFile.setOriginName(item.getName());
				uploadFile.setStoredName(item.getName() + "_" + u);
				uploadFile.setFileSize(item.getSize());

				try {
					item.write(up);
					item.delete(); // 임시 파일 삭제
					
				} catch (Exception e) {
					e.printStackTrace();
					resultBoardno = -4;
				} // 실제 업로드

			} // 파일 처리 if 끝

		} // 요청 파라미터 처리 while

		// 각자 만들어야하는 부분
		// =========================================================================================================

		if (postno == 1) {
			// 프로필 게시판
			int profileno = profileBoardDao.selectProfileno();
			
			HttpSession session = req.getSession();
			
			profile.setUserno((int)session.getAttribute("userno"));
			profile.setUsername((String)session.getAttribute("name"));
			profile.setProf_no(profileno);
			profileBoardDao.insertProfile(profile);
			if(uploadFile != null) {
				uploadFile.setPostno(1);
				uploadFile.setBoardno(profileno);
			}
	

		} else if (postno == 2) {
			// 프로젝트 게시판

		} else if (postno == 3) {
			// 게시글 내용이 전부 비어있다면
			if (freeBoard == null) {
				freeBoard = new FreeBoard();
			}
			// 자유게시판

			// 제목이 비어있는 경우 제목없음
			if (freeBoard.getFree_title() == null || "".equals(freeBoard.getFree_title().trim()))
				freeBoard.setFree_title("제목없음");

			// 내용이 비어있는 경우 내용없음
			if (freeBoard.getFree_content() == null || "".equals(freeBoard.getFree_content().trim()))
				freeBoard.setFree_content("내용없음");

			try {
				// 세션에서 userno를 가져오는데 없다면 catch블록으로
				freeBoard.setUserno((Integer) req.getSession().getAttribute("userno"));
				// nextval 가져오기
				freeBoard.setFree_no(freeBoardDao.getNextBoardno());
				// 게시글 작성
				freeBoardDao.insertBoard(freeBoard);
				resultBoardno = freeBoard.getFree_no();
				
				

				// 업로드하는 파일이 있다면
				if (uploadFile != null) {
					// 파일에 게시글 번호와 게시판 번호를 설정
					uploadFile.setBoardno(freeBoard.getFree_no());
					uploadFile.setPostno(3);
				}
			} catch (ClassCastException e) {
				// 세션에서 userno를 가져오지 못했을 때
				if (uploadFile != null) {
					// 업로드하는 파일이 있었다면 서버 디스크에 저장된 파일 삭제
					new File(context.getRealPath("upload"), uploadFile.getStoredName()).delete();
					resultBoardno =  -2;
				}
			}

		} else if (postno == 4) {
			// 완성된 게시판
			compBoard.setComp_no(compBoardDao.selectCompBoardno());
			
			if (compBoard.getComp_title() == null) {
				compBoard.setComp_title("제목없음");
				
			}
			
			compBoard.setUserno((Integer) req.getSession().getAttribute("userno"));
			
			compBoardDao.insert(compBoard);
			
			if (uploadFile != null) {
				uploadFile.setBoardno(compBoard.getComp_no());
				uploadFile.setPostno(4);
			}
		}

		// 공통적인 파일 처리, 파일이 있다면 db에 저장, postno와 boardno는 각자의 if문에서 설정
		if (uploadFile != null) {
			fileDao.insertFile(uploadFile);
		}
		
		return resultBoardno; 
	}

	@Override
	public void deleteFile(String path, int postno, int boardno) {
		Files files = new Files();
		files.setPostno(postno);
		files.setBoardno(boardno);

		Files selectFile = fileDao.selectFile(files);

		if (selectFile != null) {

			String filename = selectFile.getStoredName(); // 파일이름

			File file = new File(path, filename);

			file.delete();
			fileDao.deleteFile(selectFile);
		}
	}

	@Override
	public int modifyBoard(HttpServletRequest req, int postno) {
		int resultBoardno = 0;
		
		// 1. 파일업로드 형태의 데이터가 맞는지 확인
		// enctype이 multipart/form-data가 맞는지 확인
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);

		// 1-1 multipart/form-data 인코딩으로 전송되지 않았을 경우
		if (!isMultipart) {
			resultBoardno =  -1;
			return resultBoardno;
		}

		FreeBoard freeBoard = null;
		CompBoard compBoard = null;
		Files uploadFile = null;
		

		// 1-2 여기 이후는 multipart/form-data로 전송된 상황
		// 파일이 전송된 상황

		// 2. 업로드된 파일을 처리하는 아이템팩토리 객체 생성
		// ItemFactory : 업로드된 파일을 처리하는 방식을 정하는 클래스

		// FileItem : 클라이언트로부터 전송된 데이터를 객체화시킨 것

		// DiskFileItemFactory class - > 디스크기반(HDD)의 파일 아이템 처리 API
		// 업로드된 파일을 디스크에 임시 저장하고 후처리한다.
		DiskFileItemFactory factory = null;
		// 생성자로 필요한 객체를 넘겨줄 수 있지만 따로 설정
		factory = new DiskFileItemFactory();

		// 3. 업로드된 아이템이 용량이 작으면 메모리에서 처리
		int maxMem = 1 * 1024 * 1024; // 1MB
		factory.setSizeThreshold(maxMem);

		// 4. 용량이 적당히 크면 임시파일을 만들어서 처리 (디스크)
		ServletContext context = req.getServletContext();
		// 가상경로인 매개변수를 실제 경로로 만들어준다.
		String path = context.getRealPath("tmp");
		File repository = new File(path);
		factory.setRepository(repository);

		// 5. 업로드 허용 용량 기준을 넘지 않을 경우에만 업로드 처리
		int maxFile = 10 * 1024 * 1024; // 10MB
		// 파일 업로드 객체 생성 - DiskFileItemFactory 이용
		ServletFileUpload upload = null;
		upload = new ServletFileUpload(factory);
		// 파일 업로드 용량 제한 설정 : 10MB
		upload.setFileSizeMax(maxFile);

		// --- 파일 업로드 준비 완료 ---

		// 6. 업로드된 데이터 추출(파싱)
		// 임시 파일 업로드도 같이 수행함
		List<FileItem> items = null;

		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		// 7. 파싱된 데이터 처리하기
		// items 리스트에 요청 파라미터가 파싱되어있음

		// 요청정보의 형태 3가지
		// 1. 빈 파일 (용량이 0인 파일)
		// 2. form-data (일반적인 요청 파라미터)
		// 3. 파일

		Iterator<FileItem> iter = items.iterator();

		// 모든 요청 정보 처리
		while (iter.hasNext()) {
			FileItem item = iter.next();

			// 1) 빈 파일 처리
			if (item.getSize() <= 0)
				continue;

			if (item.isFormField()) {
				String key = item.getFieldName();
				if (postno == 1) {
					// 프로필 게시판

				} else if (postno == 2) {
					// 프로젝트 게시판

				} else if (postno == 3) {
					// 자유게시판이라면...
					if (freeBoard == null)
						freeBoard = new FreeBoard();

					if ("free_title".equals(key)) {
						try {
							freeBoard.setFree_title(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}

					} else if ("free_content".equals(key)) {
						try {
							freeBoard.setFree_content(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}

					} else if ("categoryno".equals(key)) {
						try {
							freeBoard.setCategoryno(Integer.parseInt(item.getString("UTF-8")));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
					} else if ("free_no".equals(key)) {
						try {
							freeBoard.setFree_no(Integer.parseInt(item.getString()));
						} catch (NumberFormatException e) {
							e.printStackTrace();
							// 게시글 번호를 가져오지 못했다면 리턴
							resultBoardno =  -2;
							return resultBoardno;
						}
					}
				} else if (postno == 4) {
					// 완성된 게시판
					if (compBoard == null) compBoard = new CompBoard();
					
					if ("comp_title".equals(key)) {
						try {
							compBoard.setComp_title(item.getString("utf-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					} else if ("comp_name".equals(key)) {
						try {
							compBoard.setComp_name(item.getString("utf-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					} else if ("comp_member".equals(key)) {
						try {
							compBoard.setComp_member(Integer.parseInt(item.getString("utf-8")));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}

					} else if ("comp_startdate".equals(key)) {
						try {
							compBoard.setComp_startdate(Integer.parseInt(item.getString("utf-8")));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}

					} else if ("comp_enddate".equals(key)) {
						try {
							compBoard.setComp_enddate(Integer.parseInt(item.getString("utf-8")));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}

					} else if ("comp_content".equals(key)) {
						try {
							compBoard.setComp_content(item.getString("utf-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						
					} else if ("comp_no".equals(key)) {
						
					}
						try {
							compBoard.setComp_no(Integer.parseInt(item.getString()));
						} catch (NumberFormatException e) {
							e.printStackTrace();
							// 게시글 번호를 가져오지 못했다면 리턴
							resultBoardno =  -2;
							return resultBoardno;
						}
					
					// key값 비교 if

				}

			} else {
				UUID uuid = UUID.randomUUID(); // 랜덤 UID 생성

				String u = uuid.toString().split("-")[4];

				File up = new File(context.getRealPath("upload"), item.getName() + "_" + u);

				uploadFile = new Files();

				uploadFile.setOriginName(item.getName());
				uploadFile.setStoredName(item.getName() + "_" + u);
				uploadFile.setFileSize(item.getSize());

				try {
					item.write(up);
					item.delete(); // 임시 파일 삭제
				} catch (Exception e) {
					e.printStackTrace();
				} // 실제 업로드

			} // 파일 처리 if 끝

		} // 요청 파라미터 처리 while

		if (postno == 1) {
			// 프로필 게시판

		} else if (postno == 2) {
			// 프로젝트 게시판

		} else if (postno == 3) {
			// 자유게시판

			// 게시글 내용이 전부 비어있다면
			if (freeBoard == null) {
				if (uploadFile != null) {
					// 업로드하는 파일만 존재한다면 만들어진 서버 디스크에 저장된 파일 삭제
					new File(context.getRealPath("upload"), uploadFile.getStoredName()).delete();
					return -4;
				}
				return -3;
			}
			
			// 제목이 비어있는 경우 제목없음
			if (freeBoard.getFree_title() == null || "".equals(freeBoard.getFree_title().trim()))
				freeBoard.setFree_title("제목없음");

			// 내용이 비어있는 경우 내용없음
			if (freeBoard.getFree_content() == null || "".equals(freeBoard.getFree_content().trim()))
				freeBoard.setFree_content("내용없음");

			if (freeBoardDao.updateBoard(freeBoard) != 1) {
				resultBoardno = -5;
			}
			
			resultBoardno = freeBoard.getFree_no();

			// 업로드하는 파일이 있다면
			if (uploadFile != null) {
				// 파일에 게시글 번호와 게시판 번호를 설정
				uploadFile.setBoardno(freeBoard.getFree_no());
				uploadFile.setPostno(3);
			}

		} else if (postno == 4) {
			// 완성된 게시판
			

		}

		// 공통적인 파일 처리, 업로드 파일이 있다면
		if (uploadFile != null) {

			Files prevFile = fileDao.selectFile(uploadFile);

			if (prevFile != null) {
				// 수정한 게시글의 원래 파일이 존재한다면 (전에 올렸던 파일)
				fileDao.deleteFile(prevFile);
			}

			fileDao.insertFile(uploadFile);
		}
		
		return resultBoardno;
	}

	@Override
	public Files getFiles(Files file) {
		return fileDao.selectFile(file);
	}

	@Override
	public void fileDownload(HttpServletRequest req, HttpServletResponse resp) {
		// 전달 파라미터 받기
		Files downFile = new Files();

		downFile.setFileno(Integer.parseInt(req.getParameter("fileno")));

		// 다운로드 대상 파일 정보 조회하기
		downFile = fileDao.selectFileByFileno(downFile);

		// 다운로드용 File 객체 만들기
		String path = req.getServletContext().getRealPath("upload"); // 경로
		String filename = downFile.getStoredName(); // 파일이름

		File file = new File(path, filename);

		// --- 파일 다운로드 시키기 ---
		// 응답 정보 객체를 설정한다.
		// Response Message의 Header를 수정한다.

		// 응답 body의 길이 설정

		// 파일의 길이를 알려줘야 계속 다운받지 않고 크기에 맞게 다운받는다.
		resp.setHeader("Content-Length", String.valueOf(file.length()));

		// 응답 데이터의 형식(MIME-Type)
		resp.setContentType("application/octet-stream"); // 이진 파일의 형태

		// 응답 파일의 저장위치 지정하기
		// (이름도 변경 가능)
		try {
			resp.setHeader("Content-Disposition",
					"attachment;fileName=" + new String(downFile.getOriginName().getBytes("UTF-8"), "8859_1") + ";");
			// --- 응답 메세지의 응답 Body(본문) 작성 ---
			// 파일의 내용을 응답으로 출력

			// 파일 입력 스트림 (서버의 로컬 저장소 파일)
			InputStream is = new BufferedInputStream(new FileInputStream(file));

			// 파일 출력 스트림 (브라우저)
			OutputStream os = resp.getOutputStream();

			// 파일 입력 -> 브라우저 출력
			IOUtils.copy(is, os);

			os.flush();

			is.close();
			os.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// ------------------------------------------
	}
}
