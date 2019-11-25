package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.face.CompBoardDao;
import dbutil.DBConn;
import dto.CompBoard;

public class CompBoardDaoImpl implements CompBoardDao {
	
	private Connection conn = null; //DB연결객체
	private PreparedStatement ps = null; //SQL수행객체
	private ResultSet rs = null; //SQL수행결과객체

	@Override
	public int selectCntAll() {

		conn = DBConn.getConnection(); //DB연결

		//수행할 DB쿼리
		String sql = "";
		sql += "SELECT count(*) FROM compBoard ORDER BY comp_no ";

		int cnt = 0;

		//결과 저장 리스트
		//List<Board> getList = new ArrayList<>();

		try {
			//SQL수행객체
			ps = conn.prepareStatement(sql);

			//SQL수행 및 결과 저장
			rs = ps.executeQuery();

			while( rs.next() ) {
				cnt = rs.getInt(1);
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

		return cnt;
	}
	
	@Override
	public CompBoard boardViewByComp_no(CompBoard compBoard) {
		
		conn = DBConn.getConnection(); //DB연결
		
		//수행할 SQL쿼리
		String sql ="";
		sql += "SELECT * FROM compBoard ";
		sql += "WHERE comp_no = ?";
		
		CompBoard res = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, compBoard.getComp_no());
			
			rs = ps.executeQuery();
			
			//SQL 수행 결과 처리
			while( rs.next() ) {
				res = new CompBoard();
				
				res.setComp_no( rs.getInt("comp_no") );
				res.setCategoryno( rs.getInt("categoryno") );
				res.setFileno( rs.getInt("fileno") );
				res.setUserno( rs.getInt("userno") );
				res.setComp_title( rs.getString("comp_title") );
				res.setComp_name( rs.getString("comp_name") );
				res.setComp_time( rs.getDate("comp_time") );
				res.setComp_content( rs.getString("comp_content") );
				res.setComp_member( rs.getInt("comp_member") );
				res.setComp_date( rs.getDate("comp_date") );
				res.setComp_view( rs.getInt("comp_view") );
				res.setComp_reply( rs.getInt("comp_reply") );
				res.setComp_like( rs.getInt("comp_like") );
			}
			
			ps.executeUpdate();
			
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
		
		return res;
	}


}
