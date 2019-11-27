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
import dto.User;
import util.Paging;

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
//		sql += " And mgrpw = ?";
		
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
				e.printStackTrace();
			}
		}
		
		return mgr;
	}


	@Override
	public int selectCntAll(String search) {
		
		conn = DBConn.getConnection(); // DB연결
		// 수행할 SQL
		String sql = "";
		sql += "SELECT ";
		sql += " count(*)";
		sql += " FROM profile";
		if(search!=null && !"".equals(search)) {
			sql+= "   WHERE prof_no LIKE '%'||'"+search+"'||'%'";
		}

		
		
		// 최종 결과 변수
		int cnt = 0;
		
		try {
			// SQL 수행 객체
			ps = conn.prepareStatement(sql);
			
			// SQL 수행 및 결과 저장
			rs = ps.executeQuery();
			
			// SQL 수행 결과 처리
			while( rs.next()) {
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
		
		// 최종 결과 반환
		return cnt;
	}

	@Override
	public List<User> userselectAll() {
		conn = DBConn.getConnection(); // DB연결
		
		//수행할 SQL
		String sql="";
		sql += "SELECT";
		sql += "	userno, email, pw,"; 
		sql += "    name, photo_originname, photo_storedname";
		sql += "    FROM user_table";
		
		// 결과 저장 리스트
		List<User> userlist = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				User user = new User();
				
				user.setUserno(rs.getInt("user_no"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
				user.setPhoto_originname(rs.getString("photo_originaname"));
				user.setPhoto_storedname(rs.getString("photo_storedname"));
				
				userlist.add(user);				
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
	
		return userlist;
	}

	@Override
	public List<User> userselectAll(Paging paging) {
		conn = DBConn.getConnection(); //DB 연결
		
		//수행할 SQL
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "    SELECT rownum rnum, B.* FROM (";
		sql += "        SELECT";
		sql += "             userno, email, pw,"; 
		sql += "           name, photo_originname, photo_storedname";
		sql += "        FROM user_table";
		if(paging.getSearch()!=null && !"".equals(paging.getSearch())) {
			sql+= "   WHERE email LIKE '%'||'"+paging.getSearch()+"'||'%'";
			sql+= "   AND userno LIKE '%'||'"+paging.getSearch()+"'||'%'";
			sql+= "   AND name LIKE '%'||'"+paging.getSearch()+"'||'%'";
		}

		sql += "        ORDER BY userno";
		sql += "    ) B";
		sql += "    ORDER BY rnum";
		sql += " ) user_table";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		
		//최종 결과를 저장할 List
		List<User> userlist = new ArrayList<>();		
		try {
			//SQL 수행 객체
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			//SQL 수행 및 결과 저장
			rs = ps.executeQuery();
			
			//SQL 수행 결과 처리
			while( rs.next() ) {
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
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//최종 결과 반환
		return userlist;
	}
}
