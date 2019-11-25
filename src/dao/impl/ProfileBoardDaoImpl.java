package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.face.ProfileBoardDao;
import dbutil.DBConn;
import dto.ProfileBoard;

public class ProfileBoardDaoImpl implements ProfileBoardDao {

	//DB 연결 객체
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
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
		
		System.out.println("profile board dao impl : " + list);
		return list;
	} //selectProfileList() end

}// class end
