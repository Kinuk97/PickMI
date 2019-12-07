package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.face.MateDao;
import dbutil.DBConn;
import dto.Mate;
import dto.ProjectBoard;
import sun.java2d.cmm.Profile;

public class MateDaoImpl implements MateDao {
	
	//DB 연결 객체
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	private MateDaoImpl() {
		conn = DBConn.getConnection();
	}
	
	private static class Singleton {
		private static final MateDao instance = new MateDaoImpl();
	}
	
	public static MateDao getInstance() {
		return Singleton.instance;
	}
	
	@Override
	public List<Mate> selectUsers(Mate mate) {
		String sql="";
		sql += "SELECT userno FROM mate WHERE proj_no = ? AND mate = 0";
		
		return null;
	}
	
//	@Override
//	public List<Profile> selectAppliedUser(Mate mate) {
//		String sql="";
//		sql += "SELECT p.prof_no, p.prof_interest, p.prof_job, p.prof_time, p.prof_loc, p.prof_career,";
//				" p.prof_state, p.prof_content, p.userno,";
//				"(SELECT name FROM user_table WHERE userno = p.userno) AS username";
//				+ "FROM profile p WHERE p.proj_no = m.proj_no(+) AND m.mate in (1,2) AND m.userno = ?"
//		return null;
//	}
	
	@Override
	public int countMyTeam(Mate mate) {
		String sql ="";
		sql += "SELECT count(*) FROM mate WHERE userno =? and proj_no = ?";
		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, mate.getUserno());
			ps.setInt(2, mate.getProj_no());
		
			rs = ps.executeQuery();
		
			while(rs.next()) {
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
//		System.out.println(cnt);
		return cnt;
	}
	@Override
	public Mate selectMylog(Mate mate) {
		String sql="";
		sql += "SELECT userno, proj_no, mate FROM mate WHERE userno =? and proj_no = ?";
		try {
			ps= conn.prepareStatement(sql);
			
			ps.setInt(1, mate.getUserno());
			ps.setInt(2, mate.getProj_no());
			
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				mate.setUserno(rs.getInt("userno"));
				mate.setProj_no(rs.getInt("proj_no"));
				mate.setMate(rs.getInt("mate"));
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
		return mate;
	}
	@Override
	public List<Mate> selectWantToJoinList(Mate mate) {
		String sql="";
		sql += "select proj_no,";
		sql	+= " (SELECT proj_title FROM projboard WHERE proj_no = mate.proj_no) AS proj_title";  
		sql += " from mate where userno = ? AND mate = 0";
		
		List<Mate> list = new ArrayList<Mate>();
		try {
			ps= conn.prepareStatement(sql);
			
			ps.setInt(1, mate.getUserno());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Mate mate1 = new Mate();
				mate1.setProj_no(rs.getInt("proj_no"));
				mate1.setProj_title(rs.getString("proj_title"));
				list.add(mate1);
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
//		System.out.println("왜왜왜 : " + list);
		return list;
	}
	@Override
	public void insertMate(Mate mate) {
		String sql="";
		sql += "INSERT INTO mate (userno, proj_no, mate)";
		sql += " VALUES (?, ?, 0)";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, mate.getUserno());
			ps.setInt(2, mate.getProj_no());
			
			ps.executeUpdate();
			
			
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
	}
	
	@Override
	public int countLeader(Mate mate) {
		String sql = "";
		sql += "SELECT count(*) FROM mate";
		sql += " WHERE mate.proj_no = (SELECT proj_no FROM projboard WHERE proj_no = mate.proj_no)";
		sql += " AND mate.userno = ? AND mate.mate = 2";
		
		//결과 저장 리스트
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql); //쿼리 수행 객체 얻기
			
			ps.setInt(1, mate.getUserno());
			
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
	public void insertTeam(ProjectBoard projectBoard) {
		String sql="";
		sql += "INSERT INTO mate (userno, proj_no, mate)";
		sql += " VALUES (?, ?, 2)";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, projectBoard.getUserno());
			ps.setInt(2, projectBoard.getProj_no());
			
			ps.executeUpdate();
			
			
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
		
	}
	@Override
	public void deleteUserFromTeam(Mate mate) {

		String sql ="";
		sql += "DELETE FROM mate WHERE userno = ? AND proj_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, mate.getUserno());
			ps.setInt(2, mate.getProj_no());
			
			
			ps.executeUpdate();
			
			
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
	}
	@Override
	public List<Mate> selectUsernoByProjectno(Mate mate) {
		String sql="";
		sql += "SELECT userno, proj_no FROM projboard WHERE proj_no = ?";
		
		List<Mate> list = new ArrayList<Mate>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, mate.getProj_no());
			
			rs = ps.executeQuery();
			while (rs.next()) {
				Mate temp = new Mate();
				
				temp.setUserno(rs.getInt("userno"));
				temp.setProj_no(rs.getInt("proj_no"));
				
				list.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	@Override
	public int selectProj_no(Mate mate) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<Mate> selectProj_noByUserno(Mate mate) {
		String sql="";
		sql += "select proj_no, ";
		sql	+= " (SELECT proj_title FROM projboard WHERE proj_no = mate.proj_no) AS proj_title";  
		sql += " from mate where userno = ? AND mate = 2";
		
		List<Mate> list = new ArrayList<Mate>();
		try {
			ps= conn.prepareStatement(sql);
			
			ps.setInt(1, mate.getUserno());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Mate mate1 = new Mate();
				mate1.setProj_no(rs.getInt("proj_no"));
				mate1.setProj_title(rs.getString("proj_title"));
				list.add(mate1);
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

		return list;
	}
	
	@Override
	public List<Mate> selectMateList(Mate mate) {
		
		
//		System.out.println("mate : " + mate);
		String sql="";
		sql += "SELECT";
		sql += " userno, proj_no, mate,";
		sql += " (SELECT proj_title FROM projboard WHERE proj_no = mate.proj_no) AS proj_title,";
		sql += " (SELECT name FROM user_table WHERE userno = mate.userno) AS username";
		sql += " FROM mate WHERE proj_no = ? AND mate in (1,2)";
		
		List<Mate> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, mate.getProj_no());
			
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Mate matedata = new Mate();
				
				matedata.setUserno(rs.getInt("userno"));
				matedata.setProj_no(rs.getInt("proj_no"));
				matedata.setMate(rs.getInt("mate"));
				matedata.setUsername(rs.getString("username"));
				matedata.setProj_title(rs.getString("proj_title"));
				
				list.add(matedata);
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
//		System.out.println("list : " + list);
		return list;
	}
	
	@Override
	public List<ProjectBoard> myProjectList(Mate mate) {
		String sql = "SELECT p.proj_no, p.proj_title, p.proj_name, p.proj_time, p.proj_loc, p.proj_career, p.proj_member, p.proj_apply, p.proj_sdate, p.proj_ddate, p.proj_rec_date, p.proj_progress, p.proj_content, p.proj_like, p.proj_job, m.userno mateuserno FROM projboard p, mate m WHERE p.proj_no = m.proj_no(+) AND m.mate in (1,2) AND m.userno = ?";
		
		List<ProjectBoard> list = new ArrayList<ProjectBoard>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, mate.getUserno());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				ProjectBoard projectBoard = new ProjectBoard();
				
				projectBoard.setProj_no(rs.getInt("proj_no"));
				projectBoard.setUserno(rs.getInt("mateuserno"));
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
		}
		
		return list;
	}

}
