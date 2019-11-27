package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.face.ProfileBoardDao;
import dbutil.DBConn;
import dto.Files;
import dto.ProfileBoard;
import util.Paging;

public class ProfileBoardDaoImpl implements ProfileBoardDao {

	//DB 연결 객체
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public ProfileBoard selectNameByUserno(ProfileBoard profile) {
		
		conn = DBConn.getConnection(); //db연결
		
		String sql="";
		sql += "SELECT name, userno FROM user_table";
		sql += " WHERE userno = ?";
		
		try {
			ps = conn.prepareStatement(sql); //쿼리 수행 객체
			
			ps.setInt(1, profile.getProf_no()); //? 채우기
			
			rs = ps.executeQuery(); //sql 쿼리 수행 및 resultset 반환
			
			//sql 수행결과 처리
			while (rs.next()) {
				profile.setUserno(rs.getInt("userno"));
				profile.setUsername(rs.getString("name"));
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
		
		System.out.println("profileBoardDaoImpl : " + profile);
		return profile;
	}
	
	
	@Override
	public void insertProfile(ProfileBoard profile) {
		
		conn = DBConn.getConnection();
		
		String sql="";
		sql += "INSERT INTO profile(prof_no, userno, prof_interest, prof_job, prof_state, prof_loc, prof_career, prof_content, username)";
		sql += " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, profile.getProf_no());
			ps.setInt(2, profile.getUserno());
			ps.setString(3, profile.getProf_interest());
			ps.setString(4, profile.getProf_job());
			ps.setString(5, profile.getProf_state());
			ps.setString(6, profile.getProf_loc());
			ps.setString(7, profile.getProf_career());
			ps.setString(8, profile.getProf_content());
			ps.setString(9, profile.getUsername());
			
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
	public void insertFile(Files file) {
		
		conn = DBConn.getConnection(); //db연결
		
		//수행할 sql 쿼리
		String sql ="";
		sql += "INSERT INTO files (fileno, postno, filename, boardno)";
		sql += " VALUES (files_seq.nextval, 1, ?, ?)";
		
		try {
			ps = conn.prepareStatement(sql);
			
//			ps.setString(1, file.getFilename());
			ps.setInt(2, file.getBoardno());
			
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
	public int selectProfileno() {
		
		conn = DBConn.getConnection(); //db연결
		
		int profileno = 0;
		
		String sql ="";
		sql += "SELECT profile_seq.nextval FROM dual";
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				profileno = rs.getInt(1);
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
//		System.out.println(profileno);
			return profileno;
	}
	
	@Override
	public ProfileBoard selectProfileByProfileno(ProfileBoard profile) {
		conn = DBConn.getConnection(); //db연결
		
		String sql="";
		sql += "SELECT * FROM profile";
		sql += " WHERE prof_no = ?";
		
		try {
			ps = conn.prepareStatement(sql); //쿼리 수행 객체
			
			ps.setInt(1, profile.getProf_no()); //? 채우기
			
			rs = ps.executeQuery(); //sql 쿼리 수행 및 resultset 반환
			
			//sql 수행결과 처리
			while (rs.next()) {
				profile.setProf_no(rs.getInt("prof_no"));
				profile.setUserno(rs.getInt("userno"));
				profile.setProf_interest(rs.getString("prof_interest"));
				profile.setProf_loc(rs.getString("prof_loc"));
				profile.setProf_job(rs.getString("prof_job"));
				profile.setProf_state(rs.getString("prof_state"));
				profile.setProf_career(rs.getString("prof_career"));
				profile.setProf_content(rs.getString("prof_content"));
				profile.setProf_like(rs.getInt("prof_like"));
				profile.setProf_time(rs.getDate("prof_time"));
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
		
//		System.out.println("profileBoardDaoImpl : " + profile);
		return profile;
	}
	
	
	@Override
	public List<ProfileBoard> selectAll(Paging paging) {
	//	System.out.println("test");
		String sql = "";
		sql += "select * from (";
		sql += "  select rownum rnum, B.* FROM(";
		sql += "   select * from profile";
		
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
		}try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	/**
	 * 총 게시글 수 조회
	 */
	@Override
	public int selectCntAll() {
	conn = DBConn.getConnection(); //db연결
		

	//수행할 sql 쿼리
	String sql ="";
		
	sql += "SELECT ";
	sql += " count(*)";
	sql += " FROM board";
	
		
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
	
	/**
	 * 프로필게시판 모든 글 불러오기
	 */
	@Override
	public List<ProfileBoard> selectProfileList() {
		
		conn = DBConn.getConnection(); //db연결
		
		//수행할 쿼리 
		String sql="";
		sql += "SELECT";
		sql += " prof_no, userno, prof_time,";
		sql += " prof_interest, prof_job, prof_state, prof_loc, prof_career,";
		sql += " prof_content, prof_like FROM profile";
		sql += " ORDER BY prof_no";
		
		//결과 저장 리스트
		List<ProfileBoard> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProfileBoard proboard = new ProfileBoard();
				proboard.setProf_no(rs.getInt("prof_no"));
				proboard.setUserno(rs.getInt("userno"));
				proboard.setProf_time(rs.getDate("prof_time"));
				proboard.setProf_interest(rs.getString("prof_interest"));
				proboard.setProf_job(rs.getString("prof_job"));
				proboard.setProf_state(rs.getString("prof_state"));
				proboard.setProf_loc(rs.getString("prof_loc"));
				proboard.setProf_career(rs.getString("prof_career"));
				proboard.setProf_content(rs.getString("prof_content"));
				proboard.setProf_like(rs.getInt("prof_like"));
				
				list.add(proboard);
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
		
//		System.out.println("profile board dao impl : " + list);
		return list;
	} //selectProfileList() end

}// class end
