package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.face.UserDao;
import dbutil.DBConn;
import dto.User;

public class UserDaoImpl implements UserDao {
	
	private Connection conn = null; // DB연결 객체
	private PreparedStatement ps = null; // SQL 수행 객체
	private ResultSet rs = null; // SQL 수행 결과 객체
	
	@Override
	public int selectCntUserByUserid(User user) {
		
		conn = DBConn.getConnection();
		
		String sql = "";
		sql += "SELECT count(*) FROM user";
		sql += "WHERE emial = ? AND pw = ?";
		
		
		return 0;
	}

}
