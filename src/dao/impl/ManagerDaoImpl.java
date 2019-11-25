package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.face.ManagerDao;
import dbutil.DBConn;
import dto.Manager;

public class ManagerDaoImpl implements ManagerDao{

	// DB관련 객체
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public int selectCntMemberByMgridAndMgrpw(Manager mgr) {

		// DB연결
		conn = DBConn.getConnection();
		
		if( mgr.getMgrid()==null || mgr.getMgrpw() == null ) {
			
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
			ps=conn.prepareStatement(sql);
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
				//DB객체 닫기
				rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
		 
	}

	@Override
	public Manager selectMgrByMgr(Manager mgr) {
		
		conn = DBConn.getConnection();
		
		if ( mgr.getMgrid() == null ) {			
			return null; //?
		}
		
		//쿼리 작성
		String sql = "";
		sql += "SELECT * FROM manager";
		sql += " WHERE 1=1";
		sql += " AND mgrid = ?";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,  mgr.getMgrid());
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return mgr;
	}

}
