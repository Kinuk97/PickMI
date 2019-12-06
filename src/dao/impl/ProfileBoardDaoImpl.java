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
import dto.LikePost;
import dto.ProfileBoard;
import util.Paging;

public class ProfileBoardDaoImpl implements ProfileBoardDao {

	//DB 연결 객체
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	private ProfileBoardDaoImpl() {
		conn = DBConn.getConnection();
	}
	
	private static class Singleton {
		private static final ProfileBoardDao instance = new ProfileBoardDaoImpl();
	}
	
	public static ProfileBoardDao getInstance() {
		return Singleton.instance;
	}
	
	@Override
	public void deleteProfile(ProfileBoard profile) {
		String sql = "";
		sql += "DELETE FROM profile WHERE prof_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, profile.getProf_no());
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
	public void updateProfile(ProfileBoard profile) {

		String sql = "";
		sql += " UPDATE profile SET prof_interest = ?, prof_loc = ? , prof_job = ?, prof_state = ?, prof_career = ?, prof_content = ? WHERE prof_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, profile.getProf_interest());
			ps.setString(2, profile.getProf_loc());
			ps.setString(3, profile.getProf_job());
			ps.setString(4, profile.getProf_state());
			ps.setString(5, profile.getProf_career());
			ps.setString(6, profile.getProf_content());
			ps.setInt(7, profile.getProf_no());
			
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
	public void deleteLike(LikePost like) {
		String sql = "";
		sql += "DELETE FROM likepost WHERE postno = 1 AND boardno = ? AND userno = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, like.getBoardno());
			ps.setInt(2, like.getUserno());
			
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
	public void insertLike(LikePost like) {
		String sql="";
		sql += "INSERT INTO likepost (postno, userno, boardno)";
		sql += " VALUES (1, ?, ?)";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, like.getUserno());
			ps.setInt(2, like.getBoardno());
			
			ps.executeUpdate();
			
//			System.out.println("boardDaoImpl : " + like);
			
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
	public int checkCountUserByUserno(LikePost like) {
		
		String sql="";
		sql += "SELECT COUNT(*)";
		sql += " FROM likepost";
//		sql += " WHERE 1=1";
		sql += " WHERE postno = 1";
		sql += " AND userno = ?";
		sql += " AND boardno = ?";
		
		int check = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, like.getUserno());
			ps.setInt(2, like.getBoardno());
//			System.out.println("profile daoimpl : " + like);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				check = rs.getInt(1);
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
//		System.out.println("profile dao impl :" + check);
		return check;
	}
	
	@Override
	public int selectCountLike(LikePost like) {
		String sql="";
		sql += "SELECT ";
		sql += " count(userno)";
		sql += " FROM likepost";
		sql += " WHERE postno = 1";
		sql	+= " AND boardno = ?";
		
		int likeno = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,  like.getBoardno());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				likeno = rs.getInt(1);
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
		return likeno;
	}
	
	@Override
	public ProfileBoard selectNameByUserno(ProfileBoard profile) {
		
		
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
		
		
		String sql="";
		sql += "INSERT INTO profile(prof_no, userno, prof_interest, prof_job, prof_state, prof_loc, prof_career, prof_content)";
		sql += " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
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
		
		String sql="";
		sql += "SELECT prof_no, userno, prof_interest, prof_loc, prof_job, prof_state, prof_career, prof_content, prof_like,";
		sql	+= " prof_time, (SELECT name FROM user_table WHERE userno = profile.userno) AS username FROM profile";
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
				profile.setUsername(rs.getString("username"));
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
		sql += "   select prof_no, userno, prof_time, prof_interest, prof_job, prof_state, prof_loc, prof_career, prof_content,";
		sql += " (SELECT count(*) FROM likepost WHERE boardno = profile.prof_no) AS prof_like,";
		sql	+= " (SELECT name FROM user_table WHERE userno = profile.userno) AS username from profile";
		
		//filter 존재한다면 where절 추가
		if( paging.getInterestno() != 0 || paging.getLocationno() != 0 || paging.getJobno() != 0 || paging.getStateno() != 0 || paging.getCareerno() != 0) {
			sql += " WHERE 1 = 1";
			
			if(paging.getInterestno() != 0 ) {
				sql += " AND prof_interest =";
				if(paging.getInterestno() == 1) {
					sql += " '개발'";
				} else if(paging.getInterestno() ==2) {
					sql += " '디자인'";
				} else if(paging.getInterestno() ==3) {
					sql += " '스타트업'";
				} else { 
					sql += " 'IT언어'";
				}
			}
			if(paging.getLocationno() != 0 ) {
				sql += " AND prof_loc = ";
				if(paging.getLocationno() == 1) {
					sql += " '서울'";
				} else if( paging.getLocationno() ==2) {
					sql += " '경기'";
				} else {
					sql += " '그외'";
				}
			}
			if(paging.getJobno() != 0 ) {
				sql += " AND prof_job = ";
				if( paging.getJobno() == 1) {
					sql += " '개발자'";
				} else if( paging.getJobno() ==2 ) {
					sql += " '프리랜서'";
				} else if( paging.getJobno() == 3 ) {
					sql += " '디자이너'";
				} else {
					sql += " '무직'";
				}
			}
			if(paging.getStateno() != 0 ) {
				sql += " AND prof_state = ";
				if( paging.getStateno() == 1 ) {
					sql += " '구직중'";
				} else if(paging.getStateno() == 2 ) {
					sql += " '재직중'";
				} else {
					sql += " '프리랜서'";
				}
			}
			if(paging.getCareerno() != 0 ) {
				sql += " AND prof_career =";
				if( paging.getCareerno() == 1) {
					sql += " '1-2년차'";
				} else if ( paging.getCareerno() ==2 ) {
					sql += " '3-4년차'";
				} else if ( paging.getCareerno() == 3 ) {
					sql += " '5-7년차'";
				} else {
					sql += " '8년차이상'";
				}
			}
		}
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
				profileBoard.setUsername(rs.getString("username"));
				
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
	public int selectCntAll(int interestno, int locationno, int jobno, int stateno, int careerno) {
		

	//수행할 sql 쿼리
	String sql ="";
		
	sql += "SELECT ";
	sql += " count(*)";
	sql += " FROM profile";
	
	if (interestno != 0 || locationno !=0 || jobno !=0 || stateno !=0 || careerno !=0 ) {
		
		sql += " WHERE 1 = 1";
		
		if(interestno != 0 ) {
			sql += " AND prof_interest =";
			if(interestno == 1) {
				sql += " '개발'";
			} else if(interestno ==2) {
				sql += " '디자인'";
			} else if(interestno ==3) {
				sql += " '스타트업'";
			} else { 
				sql += " 'IT언어'";
			}
		}
		if(locationno != 0 ) {
			sql += " AND prof_loc =";
			if(locationno == 1) {
				sql += " '서울'";
			} else if( locationno ==2) {
				sql += " '경기'";
			} else {
				sql += " '그외'";
			}
		}
		if(jobno != 0 ) {
			sql += " AND prof_job = ";
			if( jobno == 1) {
				sql += " '개발자'";
			} else if( jobno ==2 ) {
				sql += " '프리랜서'";
			} else if( jobno == 3 ) {
				sql += " '디자이너'";
			} else {
				sql += " '무직'";
			}
		}
		if(stateno != 0 ) {
			sql += " AND prof_state = ";
			if( stateno == 1 ) {
				sql += " '구직중'";
			} else if(stateno == 2 ) {
				sql += " '재직중'";
			} else {
				sql += " '프리랜서'";
			}
		}
		if(careerno != 0 ) {
			sql += " AND prof_career =";
			if( careerno == 1) {
				sql += " '1-2년차'";
			} else if ( careerno ==2 ) {
				sql += " '3-4년차'";
			} else if ( careerno == 3 ) {
				sql += " '5-7년차'";
			} else {
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
	
	/**
	 * 프로필게시판 모든 글 불러오기
	 */
	@Override
	public List<ProfileBoard> selectProfileList() {
		
		
		//수행할 쿼리 
		String sql="";
		sql += "SELECT";
		sql += " prof_no, prof_time, userno,";
		sql += " prof_interest, prof_job, prof_state, prof_loc, prof_career,";
		sql += " prof_content, prof_like";
		sql += ", (SELECT name FROM user_table WHERE user_table.userno = profile.userno) username ";
		sql += " FROM (SELECT * FROM profile ORDER BY prof_time DESC) profile";
		sql += " WHERE ROWNUM <= 3";
		
		//결과 저장 리스트
		List<ProfileBoard> list = new ArrayList<ProfileBoard>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProfileBoard proboard = new ProfileBoard();
				proboard.setProf_no(rs.getInt("prof_no"));
//				proboard.setUserno(rs.getInt("userno"));
				proboard.setProf_time(rs.getDate("prof_time"));
				proboard.setProf_interest(rs.getString("prof_interest"));
				proboard.setProf_job(rs.getString("prof_job"));
				proboard.setProf_state(rs.getString("prof_state"));
				proboard.setProf_loc(rs.getString("prof_loc"));
				proboard.setProf_career(rs.getString("prof_career"));
				proboard.setProf_content(rs.getString("prof_content"));
				proboard.setProf_like(rs.getInt("prof_like"));
				proboard.setUsername(rs.getString("username"));
				
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
