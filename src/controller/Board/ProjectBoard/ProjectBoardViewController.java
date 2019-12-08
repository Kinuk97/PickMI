package controller.Board.ProjectBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Alert;
import dto.Files;
import dto.LikePost;
import dto.Mate;
import dto.ProjectBoard;
import serivce.face.AlertService;
import serivce.face.FileService;
import serivce.face.MateService;
import serivce.face.ProjectBoardService;
import serivce.impl.AlertServiceImpl;
import serivce.impl.FileServiceImpl;
import serivce.impl.MateServiceImpl;
import serivce.impl.ProjectBoardServiceImpl;


@WebServlet("/projectBoard/view")
public class ProjectBoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProjectBoardService projectBoardService = new ProjectBoardServiceImpl();
	private FileService fileService = FileServiceImpl.getInstance();
	private MateService mateService = MateServiceImpl.getInstance();
	private AlertService alertService = AlertServiceImpl.getInstance();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		ProjectBoard projectBoard = projectBoardService.getProjectBoardno(req);
//		projectBoard.setUserno((int) session.getAttribute("userno"));
		
		projectBoard = projectBoardService.view(projectBoard);
		
		// 찜 상태 전달
		LikePost like = new LikePost();
		like.setBoardno(projectBoard.getProj_no());
		like.setPostno(2);
		try {
		like.setUserno((int)session.getAttribute("userno"));
		} catch (NullPointerException e) {
			
		}
		
//		System.out.println(like);
		int countLike = projectBoardService.countLike(like);
		req.setAttribute("countLike", countLike);
//		System.out.println(countLike);
		
		boolean check = projectBoardService.checkLike(like);
		if(check) {
			req.setAttribute("canLike", true);
		} else {
			req.setAttribute("canLike", false);
		}
		//기존에 프로젝트 참가 신청 했는지 확인하기
		Mate mate = new Mate();
		try {
		mate.setUserno((int)session.getAttribute("userno"));
		} catch (NullPointerException e) {	
			System.out.println("로그인 안됬음");
		}
		mate.setProj_no(projectBoard.getProj_no());
		int check1 = mateService.checkJoin(mate);
		if(check1 == 0) {
			req.setAttribute("waiting", true); //팀 신청 가능
		} else if (check1 == 1) {
			req.setAttribute("already", true); // 이미 팀가입되있음
		} else if( check1 ==2 ) {
			req.setAttribute("leader", true); //팀장임
		}
		//기존에 프로필 작성을 했었는지 확인하기
		Alert alert = new Alert();
		try {
		alert.setSender((int)session.getAttribute("userno"));
		} catch (NullPointerException e) {
			System.out.println("로그인 안됬음");
		}
		int checkprofile = alertService.checkProfile(alert);
		if ( checkprofile == 0) {
			req.setAttribute("checkprofile", false);//프로필작성한적 없음
		} else {
			req.setAttribute("checkprofile", true); //프로필 작성했음
		}
	
		
		if (projectBoard != null) {
			Files files = new Files();
			files.setPostno(2);
			files.setBoardno(projectBoard.getProj_no());
			
			req.setAttribute("files", fileService.getFiles(files));
			
			req.setAttribute("projectBoard", projectBoard);
			
			req.getRequestDispatcher("/WEB-INF/views/board/projectBoard/view.jsp").forward(req, resp);
			
		} else {
			resp.sendRedirect("projectBoard/list");
		}
		
	}

}
