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
		System.out.println("마이페이지 씨엔티 올 : " + userno);
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

	
	// 사용자가 작성한 게시글 목록 불러오기
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
//					System.out.println(freeBoard);
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
	
	// ----- 찜한 게시글 불러오기

	@Override
	public List likeboard(Paging paging, User user, int i) {

		conn = DBConn.getConnection();

		String sql = "";
		if(i == 1) {
			sql += "select * from (";
			sql += "  select rownum rnum, B.* FROM(";
			sql += "   select P.* from likepost L, profile P";
			sql += "	where L.boardno = P.prof_no";
			sql += "	and L.postno = 1 and L.userno = ?"	;		
			sql += "   order by prof_no desc";
			sql += "  ) B";
			sql += "  ORDER BY rnum";
			sql += " ) BOARD";
			sql += " WHERE rnum BETWEEN ? AND ?";
		} else if (i == 2) {
			sql += "select * from (";
			sql += "  select rownum rnum, B.* FROM(";
			sql += "   select P.* from likepost L, projboard P";
			sql += "	where L.boardno = P.proj_no";
			sql += "	and L.postno = 2 and P.userno = ?"	;		
			sql += "   order by proj_no desc";
			sql += "  ) B";
			sql += "  ORDER BY rnum";
			sql += " ) BOARD";
			sql += " WHERE rnum BETWEEN ? AND ?";
		} else if (i == 3) {
			sql += "select * from (";
			sql += "  select rownum rnum, B.* FROM(";
			sql += "   select C.* from likepost L, compboard C";
			sql += "	where L.boardno = C.comp_no";
			sql += "	and L.postno = 3 and L.userno = ?"	;		
			sql += "   order by comp_no desc";
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
//					System.out.println(list);
				}
			}
		}
		catch (SQLException e) {
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
	
	// 찜한 게시글 불러오기 ----------------------------------
	
	
	
	// ----- 작성한 댓글 불러오기
	@Override
	public List writeReply(Paging paging, User user, int i) {
		
		System.out.println("DAOIMPL : " + paging);
		System.out.println("DAOIMPL : " + user);
		System.out.println("DAOIMPL : " + i);
		
		conn = DBConn.getConnection();

		String sql = "";
		if(i == 1) {
			sql += "select * from (";
			sql += "  select rownum rnum, B.* FROM(";
			sql += "   select C.comp_no, C.Comp_title, R.reply, R.replytime  from reply R, compboard C";
			sql += "	where r.boardno = c.comp_no";
			sql += "	and r.postno = 3 and r.userno = ?"	;		
			sql += "   order by comp_no desc";
			sql += "  ) B";
			sql += "  ORDER BY rnum";
			sql += " ) BOARD";
			sql += " WHERE rnum BETWEEN ? AND ?";
		} else if (i == 2) {
			sql += "select * from (";
			sql += "  select rownum rnum, B.* FROM(";
			sql += "   select F.* from reply R, freeboard F";
			sql += "	where r.boardno = f.free_no";
			sql += "	and r.postno = 4 and r.userno = ?"	;		
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

					CompBoard compBoard = new CompBoard();
					Reply reply = new Reply();
					compBoard.setComp_no( rs.getInt("comp_no") );
					compBoard.setComp_title( rs.getString("comp_title") );
					reply.setReply(rs.getString("reply"));
					reply.setReplytime(rs.getDate("reply_time"));
					list.add(compBoard);
					System.out.println("MyPageDaoIMPL완성된게시판정보 : " + compBoard);
					list.add(reply);
					System.out.println("MyPageDaoIMPL완성된게시판댓글 정보 : " + reply);
					

				}

			}  else if ( i== 2) {
				while(rs.next()) {
					FreeBoard freeBoard = new FreeBoard();
					Reply reply = new Reply();
					freeBoard.setFree_no(rs.getInt("free_no"));
					freeBoard.setCategoryno(rs.getInt("categoryno"));
					freeBoard.setUserno(rs.getInt("userno"));
					freeBoard.setFree_title(rs.getString("free_title"));
					freeBoard.setFree_content(rs.getString("free_content"));
					freeBoard.setFree_time(rs.getDate("free_time"));
					freeBoard.setViews(rs.getInt("views"));

//					System.out.println("while --");
					System.out.println(freeBoard);
					list.add(freeBoard);

				}
			}
		}
		catch (SQLException e) {
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
		System.out.println("최종 댓글 리스트 : " + list);
		
		return list;
		
	}

	
	//---------------------- TEST
	@Override
	public int selectCntAll(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return 0;
	}

}



