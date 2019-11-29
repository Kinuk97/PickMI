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
	public int selectCntAll() {
		
		conn = DBConn.getConnection();
		
		//수행할 sql 쿼리
		String sql ="";
			
		sql += "SELECT ";
		sql += " count(*)";
		sql += " FROM projboard";
		
			
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
		sql += "   select * from projboard";

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
		sql += "SELECT * FROM projboard";
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
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();

			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
		
	}


}
