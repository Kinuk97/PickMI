package controller.Board.profileBoard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Files;
import dto.LikePost;
import dto.Mate;
import dto.ProfileBoard;
import dto.ProjectBoard;
import serivce.face.FileService;
import serivce.face.MateService;
import serivce.face.ProfileBoardService;
import serivce.impl.FileServiceImpl;
import serivce.impl.MateServiceImpl;
import serivce.impl.ProfileBoardServiceImpl;


@WebServlet("/profileBoard/view")
public class ProfileBoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProfileBoardService profileBoardService = ProfileBoardServiceImpl.getInstance();
	private FileService fileService = FileServiceImpl.getInstance();
	private MateService mateService = MateServiceImpl.getInstance();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//게시글 정보 얻기
		ProfileBoard profile = profileBoardService.getProfileno(req);
//		System.out.println("profile view controller :" + profile);
		//게시판 상세정보 얻기
		ProfileBoard detailProfile = profileBoardService.view(profile);
		
		//찜 정보 받아오기
		HttpSession session = req.getSession();
		LikePost like = new LikePost();
		like.setPostno(1);
		like.setBoardno(profile.getProf_no());
		try {
			like.setUserno((int)session.getAttribute("userno"));
		} catch (NullPointerException e) {
			System.out.println("프로필 상세보기 : 로그인하지 않은 유저가 글을 읽는 중");
		}
//		System.out.println(like);
		int countLike = profileBoardService.countLike(like);
		req.setAttribute("countLike", countLike);
		
//		System.out.println("profile view controller :" + like);
		System.out.println(countLike);
		//찜버튼 보여주기
		boolean check = profileBoardService.checkLike(like);
		if(check) {
			req.setAttribute("canLike", true); //like할수있음
		} else {
			req.setAttribute("canLike", false); //like할수없음, unlike 가능

		}
//		System.out.println("profile view controller: " + check);
		
		//프로젝트 정보 모달로 보내주기
		Mate mate = new Mate();
		ProfileBoard profile1 = new ProfileBoard();
		//게시판 번호받기
		profile1.setProf_no(Integer.parseInt(req.getParameter("prof_no")));

//		mate.setProj_no(Integer.parseInt(req.getParameter("proj_no")));
		try {
			mate.setUserno((int)session.getAttribute("userno")); //초대자 정보저장
		} catch (NullPointerException e) {
			System.out.println("로그인 안했음");
		}
		
		boolean checkLeader = mateService.checkLeader(mate);
//		System.out.println("프로필뷰 :" + checkLeader);
		if(checkLeader) {
			req.setAttribute("leader", true);
			//로그인 정보로, 팀장이 가지고 있는 프로젝트 조회
			List<ProjectBoard> project = mateService.selectMyproject(mate);
			req.setAttribute("project", project);
//			System.out.println("dkdkdk" + project);
			
		} else {
			req.setAttribute("leader", false);
		}
		
//		resp.sendRedirect("/profileBoard/view?prof_no="+req.getParameter("prof_no"));
//		req.getRequestDispatcher("/WEB-INF/views/board//profileBoard/view?prof_no=${prof_no}.jsp").forward(req, resp);


		
		
		//파일 보여주기
		if (profile != null) {
			Files file = new Files();
			file.setPostno(1);
			file.setBoardno(profile.getProf_no());
			
			req.setAttribute("file", fileService.getFiles(file));
			//모델값 지정하기
			req.setAttribute("profile", detailProfile);
			System.out.println("컨트롤러 뷰 : " + detailProfile);
			//view 지정
			req.getRequestDispatcher("/WEB-INF/views/board/profileBoard/view.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("/profileBoard/list");
		 //file end
		}
		
	} //doget end

} //class end
