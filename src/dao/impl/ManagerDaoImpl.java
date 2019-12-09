package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.face.ManagerDao;
import dbutil.DBConn;
import dto.Manager;
import dto.ProfileBoard;
import dto.ProjectBoard;
import dto.User;
import util.Paging;

public class ManagerDaoImpl implements ManagerDao {

	// DB관련 객체
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private ManagerDaoImpl() {
		conn = DBConn.getConnection();
	}

	private static class Singleton {
		private static final ManagerDao instance = new ManagerDaoImpl();
	}

	public static ManagerDao getInstance() {
		return Singleton.instance;
	}

	// ----- Login

	@Override
	public int selectCntMemberByMgridAndMgrpw(Manager mgr) {

//		// DB연결
//		conn = DBConn.getConnection();

		if (mgr.getMgrid() == null || mgr.getMgrpw() == null) {

			// -1 = 에러
			return -1;
		}

		// 쿼리작성
		String sql = "";
		sql += "SELECT COUNT(*) FROM manager";
		sql += " WHERE 1=1"; // 줄 맞추기
		sql += " AND mgrid = ?";
		sql += " AND mgrpw = ?";

		int cnt = -1;

		try {
			// DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, mgr.getMgrid());
			ps.setString(2, mgr.getMgrpw());

			rs = ps.executeQuery();

			while (rs.next()) {
				cnt = rs.getInt(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB객체 닫기
				rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return cnt;

	}

	@Override
	public Manager selectMgrByMgr(Manager mgr) {

//		conn = DBConn.getConnection();

		if (mgr.getMgrid() == null) {
			return null; // ?
		}

		// 쿼리 작성
		String sql = "";
		sql += "SELECT * FROM manager";
		sql += " WHERE 1=1";
		sql += " AND mgrid = ?";
//		sql += " And mgrpw = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, mgr.getMgrid());

			rs = ps.executeQuery();

			while (rs.next()) {
				mgr.setMgrid(rs.getString("mgrid"));
				mgr.setMgrpw(rs.getString("mgrpw"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return mgr;
	}
// Login -----

	@Override
	public int selectCntAll(String search, int i) {

//		conn = DBConn.getConnection(); // DB연결
		// 수행할 SQL
		String sql = "";
		if( i == 3 ) {			
			sql += "SELECT ";
			sql += " count(*)";
			sql += " FROM user_table";
			if (search != null && !"".equals(search)) {
				sql += "   WHERE EMAIL LIKE '%'||'" + search + "'||'%'";
			}
		} else if (i == 2 ) {
			sql += "SELECT ";
			sql += " count(*)";
			sql += " FROM projboard";
			if (search != null && !"".equals(search)) {
				sql += "   WHERE proj_title LIKE '%'||'" + search + "'||'%'";
			}

		} else if (i == 1) {
			sql += "SELECT ";
			sql += " count(*)";
			sql += " FROM profile";
			if (search != null && !"".equals(search)) {
				sql += "   WHERE prof_no LIKE '%'||'" + search + "'||'%'";
			}
		}

		// 최종 결과 변수
		int cnt = 0;

		try {
			// SQL 수행 객체
			ps = conn.prepareStatement(sql);

			// SQL 수행 및 결과 저장
			rs = ps.executeQuery();

			// SQL 수행 결과 처리
			while (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();	
			}
		}

		// 최종 결과 반환
		return cnt;
	}

// ----- User	

//	@Override
//	public List<User> userselectAll() {
//		conn = DBConn.getConnection(); // DB연결
//
//		// 수행할 SQL
//		String sql = "";
//		sql += "SELECT";
//		sql += "	userno, email, pw,";
//		sql += "    name, photo_originname, photo_storedname";
//		sql += "    FROM user_table";
//
//		// 결과 저장 리스트
//		List<User> userlist = new ArrayList<>();
//
//		try {
//			ps = conn.prepareStatement(sql);
//
//			rs = ps.executeQuery();
//
//			while (rs.next()) {
//				User user = new User();
//
//				user.setUserno(rs.getInt("user_no"));
//				user.setEmail(rs.getString("email"));
//				user.setName(rs.getString("name"));
//				user.setPhoto_originname(rs.getString("photo_originaname"));
//				user.setPhoto_storedname(rs.getString("photo_storedname"));
//
//				userlist.add(user);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (rs != null)
//					rs.close();
//				if (ps != null)
//					ps.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		System.out.println(userlist);
//		return userlist;
//	}

	@Override
	public List<User> userselectAll(Paging paging) {
		conn = DBConn.getConnection(); // DB 연결

		// 수행할 SQL
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "    SELECT rownum rnum, B.* FROM (";
		sql += "        SELECT";
		sql += "             userno, email, pw,";
		sql += "           name, photo_originname, photo_storedname";
		sql += "        FROM user_table";
		if (paging.getSearch() != null && !"".equals(paging.getSearch())) {
			sql += "   WHERE email LIKE '%'||'" + paging.getSearch() + "'||'%'";
//			sql += "   AND userno LIKE '%'||'" + paging.getSearch() + "'||'%'";
//			sql += "   AND name LIKE '%'||'" + paging.getSearch() + "'||'%'";
		}
		sql += "        ORDER BY userno";
		sql += "    ) B";
		sql += "    ORDER BY rnum";
		sql += " ) BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";

		// 최종 결과를 저장할 List
		List<User> userlist = new ArrayList<>();
		try {
			// SQL 수행 객체
			ps = conn.prepareStatement(sql);

			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			// SQL 수행 및 결과 저장
			rs = ps.executeQuery();

			// SQL 수행 결과 처리
			while (rs.next()) {
				User user = new User();
				user.setUserno(rs.getInt("userno"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
				user.setPhoto_originname(rs.getString("photo_originname"));
				user.setPhoto_storedname(rs.getString("photo_storedname"));

				userlist.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 최종 결과 반환
		return userlist;
	}

	@Override
	public int deleteUser(User user) {

		String sql = "DELETE FROM user_table WHERE userno = ?";

		int result = 0;

		try {
			conn.prepareStatement(sql);

			ps.setInt(1, user.getUserno());

			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

// User -----

// 플로필게시판, 프로젝트게시판 검색	

	@Override
	public int profileseleCtntAll(String search) { // 프로필게시판 검색
		conn = DBConn.getConnection(); // DB연결

		// 수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT count(*) FROM profile";
		if (search != null && !"".equals(search)) {
			sql += "   WHERE prof_no LIKE '%'||'" + search + "'||'%'";
		}

		// 수행 결과를 담을 리스트
		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql); // sql 수행객체 얻기

			rs = ps.executeQuery(); // SQL 수행결과 얻기
			while (rs.next()) {
				cnt = rs.getInt(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

//	@Override
//	public List<ProfileBoard> profileselectAll() {
//		conn = DBConn.getConnection(); // db연결
//
//		// 수행할 쿼리
//		String sql = "";
//		sql += "SELECT";
//		sql += " prof_no, userno, prof_time,";
//		sql += " prof_interest, prof_job, prof_state, prof_loc, prof_career,";
//		sql += " prof_content, prof_like FROM profile";
//		sql += " ORDER BY prof_no";
//
//		// 결과 저장 리스트
//		List<ProfileBoard> list = new ArrayList<>();
//
//		try {
//			ps = conn.prepareStatement(sql);
//
//			rs = ps.executeQuery();
//
//			while (rs.next()) {
//				ProfileBoard proboard = new ProfileBoard();
//				proboard.setProf_no(rs.getInt("prof_no"));
//				proboard.setUserno(rs.getInt("userno"));
//				proboard.setProf_time(rs.getDate("prof_time"));
//				proboard.setProf_interest(rs.getString("prof_interest"));
//				proboard.setProf_job(rs.getString("prof_job"));
//				proboard.setProf_state(rs.getString("prof_state"));
//				proboard.setProf_loc(rs.getString("prof_loc"));
//				proboard.setProf_career(rs.getString("prof_career"));
//				proboard.setProf_content(rs.getString("prof_content"));
//				proboard.setProf_like(rs.getInt("prof_like"));
//
//				list.add(proboard);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (rs != null)
//					rs.close();
//				if (ps != null)
//					ps.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
////		System.out.println("profile board dao impl : " + list);
//		return list;
//	}

	@Override
	public List<ProfileBoard> profileselectAll(Paging paging) {
		// DB연결
		conn = DBConn.getConnection();

		String sql = "";
		sql += "select * from (";
		sql += "  select rownum rnum, B.* FROM(";
		sql += "   select * from profile";
		if (paging.getSearch() != null && !"".equals(paging.getSearch())) {
			sql += "   WHERE prof_no LIKE '%'||'" + paging.getSearch() + "'||'%'";
		}
		sql += "   order by prof_no desc";
		sql += "  ) B";
		sql += "  ORDER BY rnum";
		sql += " ) BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";

		List<ProfileBoard> list = new ArrayList<ProfileBoard>();

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery();

			while (rs.next()) {
				ProfileBoard profileBoard = new ProfileBoard();

				profileBoard.setProf_no(rs.getInt("prof_no"));
				profileBoard.setUserno(rs.getInt("userno"));
				profileBoard.setProf_interest(rs.getString("prof_interest"));
				profileBoard.setProf_loc(rs.getString("prof_loc"));
				profileBoard.setProf_job(rs.getString("prof_job"));
				profileBoard.setProf_state(rs.getString("prof_state"));
				profileBoard.setProf_career(rs.getString("prof_career"));
				profileBoard.setProf_time(rs.getDate("prof_time"));
				profileBoard.setProf_like(rs.getInt("prof_like"));

				list.add(profileBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int projecteleCtntAll(String search) {
		conn = DBConn.getConnection(); // DB연결

		// 수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT count(*) FROM projboard";
		if (search != null && !"".equals(search)) {
			sql += "   WHERE proj_title LIKE '%'||'" + search + "'||'%'";
		}

		// 수행 결과를 담을 리스트
		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql); // sql 수행객체 얻기

			rs = ps.executeQuery(); // SQL 수행결과 얻기
			while (rs.next()) {
				cnt = rs.getInt(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	@Override
	public List<ProjectBoard> projectselectAll() {
		conn = DBConn.getConnection(); // db연결

		// 수행할 쿼리
		String sql = "";
		sql += "SELECT";
		sql += " proj_no, userno, proj_title,";
		sql += " proj_name, proj_time, proj_loc, proj_career, proj_member,";
		sql += " proj_apply, proj_sdate, proj_ddate, proj_rec_date,";
		sql += " proj_progress, proj_content, proj_like FROM projboard";
		sql += " ORDER BY proj_no";

		// 결과 저장 리스트
		List<ProjectBoard> list = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				ProjectBoard projectBoard = new ProjectBoard();

				projectBoard.setProj_no(rs.getInt("proj_no"));
				projectBoard.setUserno(rs.getInt("userno"));
				projectBoard.setProj_title(rs.getString("proj_title"));
				projectBoard.setProj_name(rs.getString("proj_name"));
				projectBoard.setProj_loc(rs.getString("proj_loc"));
				projectBoard.setProj_career(rs.getString("proj_career"));
				projectBoard.setProj_apply(rs.getInt("proj_apply"));
				projectBoard.setProj_content(rs.getString("proj_content"));
				projectBoard.setProj_sdate(rs.getDate("proj_sdate"));
				projectBoard.setProj_ddate(rs.getDate("proj_ddate"));
				projectBoard.setProj_rec_date(rs.getDate("proj_rec_date"));
				projectBoard.setProj_like(rs.getInt("proj_like"));
				projectBoard.setProj_time(rs.getDate("proj_time"));
				projectBoard.setProj_progress(rs.getString("proj_progress"));
				projectBoard.setProj_member(rs.getInt("proj_member"));

				list.add(projectBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

//		System.out.println("project board dao impl : " + list);
		return list;
	}

	@Override
	public List<ProjectBoard> projectselectAll(Paging paging) {
		// DB연결
		conn = DBConn.getConnection();

		String sql = "";
		sql += "select * from (";
		sql += "  select rownum rnum, B.* FROM(";
		sql += "   select * from projboard";
		if (paging.getSearch() != null && !"".equals(paging.getSearch())) {
			sql += "   WHERE proj_title LIKE '%'||'" + paging.getSearch() + "'||'%'";
		}

		sql += "   order by proj_no desc";
		sql += "  ) B";
		sql += "  ORDER BY rnum";
		sql += " ) BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";

		List<ProjectBoard> list = new ArrayList<ProjectBoard>();

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery();

			while (rs.next()) {
				ProjectBoard projectBoard = new ProjectBoard();

				projectBoard.setProj_no(rs.getInt("proj_no"));
				projectBoard.setUserno(rs.getInt("userno"));
				projectBoard.setProj_title(rs.getString("proj_title"));
				projectBoard.setProj_name(rs.getString("proj_name"));
				projectBoard.setProj_loc(rs.getString("proj_loc"));
				projectBoard.setProj_career(rs.getString("proj_career"));
				projectBoard.setProj_apply(rs.getInt("proj_apply"));
				projectBoard.setProj_content(rs.getString("proj_content"));
				projectBoard.setProj_sdate(rs.getDate("proj_sdate"));
				projectBoard.setProj_ddate(rs.getDate("proj_ddate"));
				projectBoard.setProj_rec_date(rs.getDate("proj_rec_date"));
				projectBoard.setProj_like(rs.getInt("proj_like"));
				projectBoard.setProj_time(rs.getDate("proj_time"));
				projectBoard.setProj_progress(rs.getString("proj_progress"));
				projectBoard.setProj_member(rs.getInt("proj_member"));

				list.add(projectBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		System.out.println("project board dao impl : " + list);
		return list;
	}

}
