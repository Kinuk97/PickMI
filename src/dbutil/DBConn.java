package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



//DB 연결 객체 - 싱글톤
public class DBConn {
	
	//DB연결정보
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERNAME = "scott";
	private static final String PASSWORD = "tiger";
	
	//DB연결 객체
	private static Connection conn = null;
	
	//private 생성자
	private DBConn( ) {  }
	
	//Conncetion 객체 반환 - Singleton Pattern
	public static Connection getConnection() {
		
	
	if(conn == null) {
		
		try {
			Class.forName(DRIVER);
			
			//DB연결 객체 생성
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}		 
	
	return conn; //DB 연결객체 반환
	
	}
	

}
