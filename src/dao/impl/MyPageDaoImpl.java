package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.face.MyPageDao;
import dbutil.DBConn;
import dto.CompBoard;
import dto.FreeBoard;
import dto.ProfileBoard;
import dto.ProjectBoard;
import dto.Reply;
import dto.User;
import util.Paging;

public class MyPageDaoImpl implements MyPageDao{

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	private MyPageDaoImpl() {
		conn = DBConn.getConnection();
	}
	
	private static class Singleton{
		private static final MyPageDao instance = new MyPageDaoImpl();
	}
	
	public static MyPageDao getInstance() {
		return Singleton.instance;
	}

// ----- User Info -----

	@Override
	public User selectOne(String email) {

		String sql = "select * from user_table where email = ?";
		
		User user = null;

		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, email);
			
			
			rs=ps.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setUserno(rs.getInt("userno"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPw(rs.getString("pw"));
				user.setPhoto_originname(rs.getString("photo_originname"));
				user.setPhoto_storedname(rs.getString("photo_storedname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)ps.close();
				if(rs!=null)rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return user;
	}

	@Override
	public void updateUser(User user) {
		
		String sql = "update user set pw = ?, name = ? email = ? where email = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getPw());
			ps.setString(2, user.getName());
			ps.setString(3, user.getEmail());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public User selectUserbyUserno(HttpServletRequest req) {
		String sql = "";
		sql +="SELECT email, userno, name FROM user_table WHERE userno=?";
		
		User user = new User();
		
		try {
			ps=conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

// ----- 내가 작성한 게시글	
	@Override
	public int selectpfCntAll(HttpServletRequest req) {
		conn = DBConn.getConnection(); // DB연결

		// 수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT count(*) FROM projboard";
		
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
	public List<ProfileBoard> selectPf(Paging paging, User user) {
		conn = DBConn.getConnection();

		String sql = "";
		sql += "select * from (";
		sql += "  select rownum rnum, B.* FROM(";
		sql += "   select * from profile WHERE userno = ?";
		if (paging.getSearch() != null && !"".equals(paging.getSearch())) {
			sql += "   WHERE prof_no LIKE '%'||'" + paging.getSearch() + "'||'%'";
		}
		sql += "   order by prof_no desc";
		sql += "  ) B";
		sql += "  ORDER BY rnum";
		sql += " ) BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";

		List<ProfileBoard> pflist = new ArrayList<ProfileBoard>();

		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getUserno());
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());

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

				pflist.add(profileBoard);
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
		return pflist;
	}
	

	@Override
	public List<ProjectBoard> selectPj(Paging paging, User user) {
		conn = DBConn.getConnection();

		String sql = "";
		sql += "select * from (";
		sql += "  select rownum rnum, B.* FROM(";
		sql += "   select * from projboard WHERE userno = ?";
		if (paging.getSearch() != null && !"".equals(paging.getSearch())) {
			sql += "   WHERE proj_title LIKE '%'||'" + paging.getSearch() + "'||'%'";
		}

		sql += "   order by proj_no desc";
		sql += "  ) B";
		sql += "  ORDER BY rnum";
		sql += " ) BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";

		List<ProjectBoard> pjlist = new ArrayList<ProjectBoard>();

		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getUserno());
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());

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

				pjlist.add(projectBoard);
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
		return pjlist;
	}
	

	@Override
	public List<CompBoard> selectComp(Paging paging, User user) {

		conn = DBConn.getConnection(); //DB연결

		//수행할 DB쿼리
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "    SELECT rownum rnum, B.* FROM (";
		sql += "		SELECT comp_no, userno, comp_title, comp_name, ";
		sql += "			   comp_content, comp_member, TO_CHAR(comp_date, 'YYYY/MM/DD HH:MI:SS') comp_date, comp_view, ";
		sql += "			   comp_startdate, comp_enddate, ";
		sql += " 			(SELECT name FROM user_table WHERE compBoard.userno = userno) username";
		sql += "		FROM compBoard WHERE userno=?";
		
		//검색어가 존재할 때
		if (paging.getSearch() != null) {
			//WHERE 추가
			sql += " WHERE 1 = 1";
			
			if (paging.getSearchno() == 2) {
				//팀이름으로 검색할 경우
				sql += " AND comp_name LIKE '%' || ? || '%'";
			
			} else if (paging.getSearchno() == 3) {
				//제목&내용으로 검색할 경우
				sql += " AND (comp_title LIKE '%' || ? || '%'";
				sql += " OR comp_content LIKE '%' || ? || '%')";
				
			} else {
				//제목으로 검색할 경우
				sql += " AND comp_title LIKE '%' || ? || '%'";
			}
		}

		sql += "        ORDER BY comp_no DESC";
		sql += "    )B";
		sql += "    ORDER BY rnum";
		sql += " ) compBoard";
		sql += " WHERE rnum BETWEEN ? AND ?";

//		System.out.println(sql);

		//결과 저장 리스트
		List<CompBoard> complist = new ArrayList<CompBoard>();

		try {
			ps = conn.prepareStatement(sql);

			if (paging.getSearch() != null) {
				ps.setString(1, paging.getSearch());
				ps.setInt(2, paging.getStartNo());
				ps.setInt(3, paging.getEndNo());
				
				if (paging.getSearchno() == 3) {
					ps.setString(2, paging.getSearch());
					ps.setInt(3, paging.getStartNo());
					ps.setInt(4, paging.getEndNo());
				}
				
			} else { // 내가쓴게시물 검색 X
				//검색어가 존재하지 않을 경우 (전체 리스트)
				ps.setInt(1, user.getUserno());
				ps.setInt(2, paging.getStartNo());
				ps.setInt(3, paging.getEndNo());
			}
			
			rs = ps.executeQuery();

			while(rs.next()) {
				CompBoard compBoard = new CompBoard();

//				compBoard.setRownum( rs.getInt("rnum"));
				compBoard.setComp_no( rs.getInt("comp_no") );
				compBoard.setUserno( rs.getInt("userno") );
				compBoard.setUsername( rs.getString("username"));
				compBoard.setComp_title( rs.getString("comp_title") );
				compBoard.setComp_name( rs.getString("comp_name") );
				compBoard.setComp_content( rs.getString("comp_content") );
				compBoard.setComp_member( rs.getInt("comp_member") );
				compBoard.setComp_date( rs.getString("comp_date") );
				compBoard.setComp_view( rs.getInt("comp_view") );
				compBoard.setComp_startdate( rs.getDate("comp_startdate"));
				compBoard.setComp_enddate( rs.getDate("comp_enddate") );
				
				complist.add(compBoard);
				System.out.println(complist);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();

			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
	
	return complist;
		
		
	}

	@Override
	public List<FreeBoard> selectFree(Paging paging, User user) {
		System.out.println("mypagedaoimpl user객체 : " + user); 
		String sql = "";
		sql += "select * from (";
		sql += "  select rownum rnum, B.* FROM(";
		sql += "   select free_no, categoryno, userno, free_title, free_content, free_time, views, "
				+ " (SELECT name FROM user_table WHERE freeboard.userno = userno) username"
				+ " from freeboard WHERE userno = ?";

		// 검색어나 카테고리가 존재한다면 WHERE절 추가
		if (paging.getSearch() != null || paging.getCategoryno() != 0) {
			sql += " WHERE 1 = 1";

			// 검색어가 존재할 경우
			if (paging.getSearch() != null) {
				if (paging.getSearchno() == 2) {
					// 내용으로 검색할 경우
					sql += " AND free_content LIKE '%' || ? || '%'";
				} else if (paging.getSearchno() == 3) {
					// 제목 & 내용으로 검색할 경우
					sql += " AND (free_title LIKE '%' || ? || '%'";
					sql += " OR free_content LIKE '%' || ? || '%')";
				} else {
					// 제목으로 검색하거나 searchno가 2,3이 아닐 때
					sql += " AND free_title LIKE '%' || ? || '%'";
				}
			}

			if (paging.getCategoryno() != 0) {
				sql += " AND categoryno = ?";
			}
		}

		sql += "   order by free_no desc";
		sql += "  ) B";
		sql += "  ORDER BY rnum";
		sql += " ) BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";

		
		System.out.println(sql);
		List<FreeBoard> freelist = new ArrayList<FreeBoard>();

		try {
			ps = conn.prepareStatement(sql);

			if (paging.getSearch() != null && paging.getCategoryno() == 0) {
				ps.setString(1, paging.getSearch());
				if (paging.getSearchno() == 3) {
					// 제목 & 내용으로 검색할 때
					ps.setString(2, paging.getSearch());
					ps.setInt(3, paging.getStartNo());
					ps.setInt(4, paging.getEndNo());
				} else {
					// 제목이나 내용으로 검색할 때
					ps.setInt(2, paging.getStartNo());
					ps.setInt(3, paging.getEndNo());
				}
			} else if (paging.getCategoryno() != 0 && paging.getSearch() == null) {
				// 검색어가 없고 카테고리만 존재한다면
				ps.setInt(1, paging.getCategoryno());
				ps.setInt(2, paging.getStartNo());
				ps.setInt(3, paging.getEndNo());
			} else if (paging.getSearch() != null && paging.getCategoryno() != 0) {
				// 검색어가 존재하고 카테고리가 존재할 때
				ps.setString(1, paging.getSearch());
				if (paging.getSearchno() == 3) {
					// 제목 & 내용으로 검색할 때
					ps.setString(2, paging.getSearch());
					ps.setInt(3, paging.getCategoryno());
					ps.setInt(4, paging.getStartNo());
					ps.setInt(5, paging.getEndNo());
				} else {
					// 제목으로 검색하거나 내용으로 검색할 때
					ps.setInt(2, paging.getCategoryno());
					ps.setInt(3, paging.getStartNo());
					ps.setInt(4, paging.getEndNo());
				}
			} else {
				// 검색어, 카테고리가 존재하지 않는 경우 (전체 리스트)
				System.out.println("here");
				ps.setInt(1, user.getUserno());
				ps.setInt(2, paging.getStartNo());
				ps.setInt(3, paging.getEndNo());
			}

			rs = ps.executeQuery();

			while (rs.next()) {
				FreeBoard freeBoard = new FreeBoard();

				freeBoard.setFree_no(rs.getInt("free_no"));
				freeBoard.setCategoryno(rs.getInt("categoryno"));
				freeBoard.setUserno(rs.getInt("userno"));
				freeBoard.setUsername(rs.getString("username"));
				freeBoard.setFree_title(rs.getString("free_title"));
				freeBoard.setFree_content(rs.getString("free_content"));
				freeBoard.setFree_time(rs.getDate("free_time"));
				freeBoard.setViews(rs.getInt("views"));

				System.out.println("while --");
				System.out.println(freeBoard);
				freelist.add(freeBoard);
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

		System.out.println("aaa");
		for(FreeBoard f : freelist) System.out.println(f);
		return freelist;

	}
// 내가 작성한 게시글 --------------------------------------------------
		
		
// ----- 비밀번호 수정

	@Override
	public int selectCntUserByupw(User pwparam) {
		conn = DBConn.getConnection();

		if ((Integer)pwparam.getUserno() == null || pwparam.getPw() == null) {
			return -1;

		}

		int cnt = -1;

		String sql = "";
		sql += "SELECT count(*) FROM user_table";
		sql += " WHERE userno = ? AND pw = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, pwparam.getUserno());
			ps.setString(2, pwparam.getPw());

			rs = ps.executeQuery();

			while (rs.next()) {
				cnt = rs.getInt("count(*)");
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
		System.out.println("비밀번호 일치 여부 확인 : " + cnt); //
		return cnt;
	}

	@Override
	public int deleteUser(User user) {

//		System.out.println("myPageDao(deleteUser)에서의 user 매개변수가 갖고 있는 것 : " + user);
		
		conn = DBConn.getConnection();
		
		String sql = "";
		
		sql += "DELETE FROM user_table WHERE userno = ?";
		
		int result = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getUserno());
			result=ps.executeUpdate();
			
			
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

	@Override

	public int selectCntAll(HttpServletRequest req, int i) {
		conn = DBConn.getConnection();
		
		String sql = "";
		sql += "SELECT count(*)"; 
		if(i == 1) {
			System.out.println("프로필");
			sql += "		FROM profile ";
		}else if(i == 2) {
			System.out.println("프로젝트");
			sql += "		FROM projboard ";
		}else if(i == 3) {
			System.out.println("완성");
			sql += "		FROM compboard ";
		}else if(i == 4) {
			System.out.println("자유");
			sql += "		FROM freeboard ";
		}
		sql += "	WHERE userno = ?";
		
		int userno = Integer.parseInt((req.getSession().getAttribute("userno").toString()));
		
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, userno);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				cnt = rs.getInt(1);
			}
			
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
		return cnt;
	}

	@Override
	public List selectboard(Paging paging, User user, int i) {
		
		conn = DBConn.getConnection();
		
		String sql = "";
		if(i == 1) {
			sql += "select * from (";
			sql += "  select rownum rnum, B.* FROM(";
			sql += "   select * from profile WHERE userno = ?";
			sql += "   order by prof_no desc";
			sql += "  ) B";
			sql += "  ORDER BY rnum";
			sql += " ) BOARD";
			sql += " WHERE rnum BETWEEN ? AND ?";
		} else if (i == 2) {
			sql += "select * from (";
			sql += "  select rownum rnum, B.* FROM(";
			sql += "   select * from projboard WHERE userno = ?";
			sql += "   order by proj_no desc";
			sql += "  ) B";
			sql += "  ORDER BY rnum";
			sql += " ) BOARD";
			sql += " WHERE rnum BETWEEN ? AND ?";
		} else if (i == 3) {
			sql += "SELECT * FROM (";
			sql += "    SELECT rownum rnum, B.* FROM (";
			sql += "		SELECT comp_no, userno, comp_title, comp_name, ";
			sql += "			   comp_content, comp_member, TO_CHAR(comp_date, 'YYYY/MM/DD HH:MI:SS') comp_date, comp_view, ";
			sql += "			   comp_startdate, comp_enddate, ";
			sql += " 			(SELECT name FROM user_table WHERE compBoard.userno = userno) username";
			sql += "		FROM compBoard WHERE userno=?";
			sql += "        ORDER BY comp_no DESC";
			sql += "    )B";
			sql += "    ORDER BY rnum";
			sql += " ) compBoard";
			sql += " WHERE rnum BETWEEN ? AND ?";
		} else if(i == 4) {
			sql += "select * from (";
			sql += "  select rownum rnum, B.* FROM(";
			sql += "   select free_no, categoryno, userno, free_title, free_content, free_time, views, "
					+ " (SELECT name FROM user_table WHERE freeboard.userno = userno) username"
					+ " from freeboard WHERE userno = ?";
			sql += "   order by free_no desc";
			sql += "  ) B";
			sql += "  ORDER BY rnum";
			sql += " ) BOARD";
			sql += " WHERE rnum BETWEEN ? AND ?";
		}
		List list = new ArrayList();
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getUserno());
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			
			rs = ps.executeQuery();
			if (i == 1) {
				
				while(rs.next()) {
					
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
			} else if ( i== 2) {
				while(rs.next()) {
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
			}  else if ( i== 3) {
				while(rs.next()) {
					CompBoard compBoard = new CompBoard();

//					compBoard.setRownum( rs.getInt("rnum"));
					compBoard.setComp_no( rs.getInt("comp_no") );
					compBoard.setUserno( rs.getInt("userno") );
					compBoard.setUsername( rs.getString("username"));
					compBoard.setComp_title( rs.getString("comp_title") );
					compBoard.setComp_name( rs.getString("comp_name") );
					compBoard.setComp_content( rs.getString("comp_content") );
					compBoard.setComp_member( rs.getInt("comp_member") );
					compBoard.setComp_date( rs.getString("comp_date") );
					compBoard.setComp_view( rs.getInt("comp_view") );
					compBoard.setComp_startdate( rs.getDate("comp_startdate"));
					compBoard.setComp_enddate( rs.getDate("comp_enddate") );
					
					list.add(compBoard);
					System.out.println(list);
				}
			} else if ( i== 4) {
				while(rs.next()) {
					FreeBoard freeBoard = new FreeBoard();

					freeBoard.setFree_no(rs.getInt("free_no"));
					freeBoard.setCategoryno(rs.getInt("categoryno"));
					freeBoard.setUserno(rs.getInt("userno"));
					freeBoard.setUsername(rs.getString("username"));
					freeBoard.setFree_title(rs.getString("free_title"));
					freeBoard.setFree_content(rs.getString("free_content"));
					freeBoard.setFree_time(rs.getDate("free_time"));
					freeBoard.setViews(rs.getInt("views"));

//					System.out.println("while --");
					System.out.println(freeBoard);
					list.add(freeBoard);

				}
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			try {

				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
		return list;
	}

	
	//--------------------------------------------------- 주석처리 도훈씨꺼
	@Override
	public List<Reply> selectReply(Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}
}

//	public List<Reply> selectReply(Paging paging) {
//
//		conn = DBConn.getConnection();
//		
//		String sql = "";
//		sql += "SELECT replyno, boardno, postno, ";
//		sql	+= "	(SELECT userno FROM user_table WHERE userno = reply.userno)userno, ";
//		sql	+= "		username, reply, replytime";
//		sql += "			FROM reply";
//		
//		
//		return null;
//	}
//}

