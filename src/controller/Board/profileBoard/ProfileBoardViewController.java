package controller.Board.profileBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Files;
import dto.LikePost;
import dto.ProfileBoard;
import serivce.face.FileService;
import serivce.face.ProfileBoardService;
import serivce.impl.FileServiceImpl;
import serivce.impl.ProfileBoardServiceImpl;


@WebServlet("/profileBoard/view")
public class ProfileBoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProfileBoardService profileBoardService = ProfileBoardServiceImpl.getInstance();
	private FileService fileService = FileServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//게시글 정보 얻기
		ProfileBoard profile = profileBoardService.getProfileno(req);
		
		//게시판 상세정보 얻기
		ProfileBoard detailProfile = profileBoardService.view(profile);
		
		//찜 보여주기
		HttpSession session = req.getSession();
		LikePost like = new LikePost();
		like.setPostno(1);
		like.setBoardno(profile.getProf_no());
		like.setUserno((int)session.getAttribute("userno"));
		int countLike = profileBoardService.countLike(like);
		req.setAttribute("countLike", countLike);
		
//		System.out.println("profile view controller :" + like);
		
		//찜버튼 보여주기
		boolean check = profileBoardService.checkLike(like);
		if(check) {
			req.setAttribute("canLike", true); //like할수있음
		} else {
			req.setAttribute("canLike", false); //like할수없음, unlike 가능

		}
//		System.out.println("profile view controller: " + check);
		
		//파일 보여주기
		if (profile != null) {
			Files file = new Files();
			file.setPostno(1);
			file.setBoardno(profile.getProf_no());
			
			req.setAttribute("file", fileService.getFiles(file));
			//모델값 지정하기
			req.setAttribute("profile", detailProfile);
			//view 지정
			req.getRequestDispatcher("/WEB-INF/views/board/profileBoard/view.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("/profileBoard/list");
		 //file end
		}
		
	} //doget end

} //class end
