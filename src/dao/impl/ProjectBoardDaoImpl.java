package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.face.FreeBoardDao;
import dao.face.ProjectBoardDao;
import dbutil.DBConn;
import dto.FreeBoard;
import dto.LikePost;
import dto.ProjectBoard;
import util.Paging;

public class ProjectBoardDaoImpl implements ProjectBoardDao {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private ProjectBoardDaoImpl() {
		conn = DBConn.getConnection();
	}

	private static class Singleton {
		private static final ProjectBoardDao instance = new ProjectBoardDaoImpl();
	}

	public static ProjectBoardDao getInstance() {

		return Singleton.instance;
	}
	
	@Override
	public int getNextBoardno() {
		String sql = "SELECT proj_no_seq.nextval FROM dual";

		int result = 0;

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				result = rs.getInt(1);
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

		return result;
	}

	@Override
	public int selectCntAll(String proj_loc, String proj_progress, String proj_job, String proj_career) {
		
		conn = DBConn.getConnection();
		
		//수행할 sql 쿼리
		String sql ="";
			
		sql += "SELECT ";
		sql += " count(*)";
		sql += " FROM projboard";
		
		
		if((proj_loc != null && !proj_loc.equals("")) || (proj_progress != null && !proj_progress.equals("")) 
				|| (proj_job != null && !proj_job.equals("")) || (proj_career != null && !proj_career.equals(""))) {
			
			sql += " WHERE 1 = 1";
			
			if(proj_loc != null && !proj_loc.equals("")) {
				sql += " AND proj_loc =";
				if(proj_loc.equals("서울")) {
					sql += " '서울'";
				} else if(proj_loc.equals("인천")) {
					sql += " '인천'";
				} else if(proj_loc.equals("경기")) {
					sql += " '경기'";
				} else if(proj_loc.equals("강원")) {
					sql += " '강원'";
				} else if(proj_loc.equals("충청")) {
					sql += " '충청'";
				} else if(proj_loc.equals("경상")) {
					sql += " '경상'";
				} else if(proj_loc.equals("전라")) {
					sql += " '전라'";
				} 
			
			} else if(proj_progress != null && !proj_progress.equals("")) {
				sql += " AND proj_progress =";
				if(proj_progress.equals("설계단계")) {
					sql += " '설계단계'";
				} else if(proj_progress.equals("구현단계")) {
					sql += " '구현단계'";
				}
			
			} else if(proj_job != null && !proj_job.equals("")) {
				sql += " AND proj_job =";
				if(proj_job.equals("개발자")) {
					sql += " '개발자'";
				} else if(proj_job.equals("프리랜서")) {
					sql += " '프리랜서'";
				} else if(proj_job.equals("디자이너")) {
					sql += " '디자이너'";
				} else if(proj_job.equals("무직")) {
					sql += " '무직'";
				}
			
			} else if(proj_career != null && !proj_career.equals("")) {
				sql += " AND proj_career =";
				if(proj_career.equals("1년차")) {
					sql += " '1년차'";
				} else if(proj_career.equals("3년차")) {
					sql += " '3년차'";
				} else if(proj_career.equals("5년차")) {
					sql += " '5년차'";
				} else if(proj_career.equals("7년차")) {
					sql += " '7년차'";
				} else if(proj_career.equals("8년차이상")) {
					sql += " '8년차이상'";
				}
			}


		}
			
		//결과 저장 리스트
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql); //쿼리 수행 객체 얻기
			
			rs = ps.executeQuery();
			
			while(rs.next() ) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
	}

	@Override
	public List<ProjectBoard> selectAll(Paging paging) {
		String sql = "";
		sql += "select * from (";
		sql += "  select rownum rnum, B.* FROM(";
		sql += "   select proj_no, userno, proj_title, proj_name, proj_time, proj_loc, proj_career, proj_member,";
		sql += "	proj_apply, proj_sdate, proj_ddate, proj_rec_date, proj_progress, proj_content, proj_like, proj_job";
		sql	+= "	 from projboard";
		
		// filter 존재한다면 where절 추가
		if((paging.getProj_loc() != null && !paging.getProj_loc().equals("")) || (paging.getProj_progress() != null && !paging.getProj_progress().equals("")) 
				|| (paging.getProj_job() != null && !paging.getProj_job().equals("")) || (paging.getProj_career() != null && !paging.getProj_career().equals(""))) {
			sql += " WHERE 1 = 1";
			
			if(paging.getProj_loc() != null && !paging.getProj_loc().equals("")) {
				
				sql += " AND proj_loc =";
				
				if(paging.getProj_loc().equals("서울")) {
					sql += " '서울'";
				} else if(paging.getProj_loc().equals("인천")) {
					sql += " '인천'";
				} else if(paging.getProj_loc().equals("경기")) {
					sql += " '경기'";
				} else if(paging.getProj_loc().equals("강원")) {
					sql += " '강원'";
				} else if(paging.getProj_loc().equals("충청")) {
					sql += " '충청'";
				} else if(paging.getProj_loc().equals("경상")) {
					sql += " '경상'";
				} else if(paging.getProj_loc().equals("전라")) {
					sql += " '전라'";
				}
			
			} else if(paging.getProj_progress() != null && !paging.getProj_progress().equals("")){
				sql += " AND proj_progress =";
				if(paging.getProj_progress().equals("설계단계")) {
					sql += " '설계단계'";
				} else if(paging.getProj_progress().equals("구현단계")) {
					sql += " '구현단계'";
				}
				
			} else if(paging.getProj_job() != null && !paging.getProj_job().equals("")) {
				sql += " AND proj_job =";
				if(paging.getProj_job().equals("개발자")) {
					sql += " '개발자'";
				} else if(paging.getProj_job().equals("프리랜서")) {
					sql += " '프리랜서'";
				} else if(paging.getProj_job().equals("디자이너")) {
					sql += " '디자이너'";
				} else if(paging.getProj_job().equals("무직")) {
					sql += " '무직'";
				}
			
			} else if(paging.getProj_career() != null && !paging.getProj_career().equals("")) {
				sql += " AND proj_career =";
				if(paging.getProj_career().equals("1년차")) {
					sql += " '1년차'";
				} else if(paging.getProj_career().equals("3년차")) {
					sql += " '3년차'";
				} else if(paging.getProj_career().equals("5년차")) {
					sql += " '5년차'";
				} else if(paging.getProj_career().equals("7년차")) {
					sql += " '7년차'";
				} else if(paging.getProj_career().equals("8년차이상")) {
					sql += " '8년차이상'";
				}
			}
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
				projectBoard.setProj_job(rs.getString("proj_job"));
				

				list.add(projectBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
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

	@Override
	public ProjectBoard selectBoardByProjectno(ProjectBoard projectBoard) {
		
		conn = DBConn.getConnection();
		
		String sql = "";
		sql += "SELECT proj_no, userno, proj_title, proj_name, proj_loc, proj_career, "
				+ "proj_apply, proj_content, proj_sdate, proj_ddate, proj_rec_date, proj_like, "
				+ "proj_time, proj_progress, proj_member, proj_job, "
				+ "(SELECT name FROM user_table WHERE projboard.userno = userno) username";
		sql += " FROM projboard";
		sql += " WHERE proj_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, projectBoard.getProj_no());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
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
				projectBoard.setProj_job(rs.getString("proj_job"));
				projectBoard.setUsername(rs.getString("username"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally{
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return projectBoard;
	}

	@Override
	public void insert(ProjectBoard projectBoard) {
		conn = DBConn.getConnection();

		String sql = "";
		sql +="INSERT INTO projBoard(proj_no, userno, proj_title, proj_name, ";
		sql +="		 				 proj_content, proj_member, proj_time, proj_loc, proj_job, ";
		sql +="						 proj_career, proj_sdate, proj_ddate, proj_rec_date, proj_progress, proj_like, proj_apply )";
		sql +=" VALUES(?, ?, ?, ?, ?, ?, sysdate, ?, ?, ?, ?, ?, ?, ?, 0, 0)";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, projectBoard.getProj_no());
			ps.setInt(2, projectBoard.getUserno());
			ps.setString(3, projectBoard.getProj_title());
			ps.setString(4, projectBoard.getProj_name());
			ps.setString(5, projectBoard.getProj_content());
			ps.setInt(6, projectBoard.getProj_member());
			ps.setString(7, projectBoard.getProj_loc());
			ps.setString(8, projectBoard.getProj_job());
			ps.setString(9, projectBoard.getProj_career());
			ps.setDate(10, (Date) projectBoard.getProj_sdate());
			ps.setDate(11, (Date) projectBoard.getProj_ddate());
			ps.setDate(12, (Date) projectBoard.getProj_rec_date());
			ps.setString(13, projectBoard.getProj_progress());

			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if(ps!=null) ps.close();

			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
		
	}

	@Override
	public void deleteProjBoard(ProjectBoard projectBoard) {
		conn = DBConn.getConnection();
		
		String sql = "";
		sql += "DELETE FROM projboard";
		sql += " WHERE proj_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, projectBoard.getProj_no());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();

			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
		
	}

	@Override
	public int updateBoard(ProjectBoard projectBoard) {
		conn = DBConn.getConnection();

		String sql = "";
		sql += "UPDATE projboard SET proj_title = ?, proj_name = ?, proj_content = ?, proj_member = ?,";
		sql += " proj_sdate = ?, proj_ddate = ?, proj_rec_date = ?, proj_loc = ?, proj_progress = ?, proj_job = ?, proj_career = ?";
		sql += " WHERE proj_no = ?";
		
		int result = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, projectBoard.getProj_title());
			ps.setString(2, projectBoard.getProj_name());
			ps.setString(3, projectBoard.getProj_content());
			ps.setInt(4, projectBoard.getProj_member());
			ps.setDate(5, (Date) projectBoard.getProj_sdate());
			ps.setDate(6, (Date) projectBoard.getProj_ddate());
			ps.setDate(7, (Date) projectBoard.getProj_rec_date());
			ps.setString(8, projectBoard.getProj_loc());
			ps.setString(9, projectBoard.getProj_progress());
			ps.setString(10, projectBoard.getProj_job());
			ps.setString(11, projectBoard.getProj_career());
			ps.setInt(12, projectBoard.getProj_no());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();

			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
		return result;
	}

	@Override
	public void deleteLike(LikePost like) {
		conn = DBConn.getConnection();
		
		String sql = "";
		sql += "DELETE FROM likepost";
		sql += " WHERE";
		sql += " boardno = ? AND userno = ? AND postno = 2";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, like.getBoardno());
			ps.setInt(2, like.getUserno());
			
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
	public void insertLike(LikePost like) {
		conn = DBConn.getConnection();

		String sql = "";
		sql += "INSERT INTO likepost(postno,userno, boardno)";
		sql += " VALUES (2, ?, ? )";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, like.getUserno());
			ps.setInt(2, like.getBoardno());
			
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
	public int selectCntLike(LikePost like) {
		conn = DBConn.getConnection();

		String sql = "";
		sql += "SELECT count(userno) FROM likepost";
		sql += " WHERE boardno = ?";
		sql += " 	AND postno = 2";
		
		int cnt = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, like.getBoardno());

			rs = ps.executeQuery();
			
			while(rs.next()) {
			
				cnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(ps!=null)	ps.close();
				if(rs!=null)	rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
	}
	
	@Override
	public int checkCntByUserno(LikePost like) {
		conn = DBConn.getConnection();
		
		String sql = "";
		sql += "SELECT count(*) FROM likepost";
		sql += " WHERE postno = 2 AND userno = ? AND boardno = ?";
		
		int check = -1;
		
		try {
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, like.getUserno());
			ps.setInt(2, like.getBoardno());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				check = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(ps!=null)	ps.close();
				if(rs!=null)	rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return check;
	}

	@Override
	public List<ProjectBoard> selectListToMain() {
		conn = DBConn.getConnection();
		
		String sql = "";
		sql += "SELECT * FROM (SELECT * FROM projboard ORDER BY proj_time DESC)";
		sql += " WHERE ROWNUM <= 3";
		
		List<ProjectBoard> list = new ArrayList<ProjectBoard>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
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
				projectBoard.setProj_job(rs.getString("proj_job"));
				
				list.add(projectBoard);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)	ps.close();
				if(rs!=null)	rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}


}
