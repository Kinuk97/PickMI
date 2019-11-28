package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.face.CompBoardDao;
import dbutil.DBConn;
import dto.CompBoard;
import dto.Files;
import util.Paging;

public class CompBoardDaoImpl implements CompBoardDao {
	
	private Connection conn = null; //DB연결객체
	private PreparedStatement ps = null; //SQL수행객체
	private ResultSet rs = null; //SQL수행결과객체

	@Override
	public int selectCntAll(String search, int searchno) {

		conn = DBConn.getConnection(); //DB연결

		//수행할 DB쿼리
		String sql = "";
		sql += "SELECT count(*) FROM compBoard ";

		//검색어가 존재한다면
		if (search != null) {
			
			//WHERE  추가
			sql += "WHERE 1 = 1";
			
			if (searchno == 2) {
				//팀이름으로 검색할 경우
				sql += " AND comp_name LIKE '%' || ? || '%'";

			} else if (searchno == 3) {
				sql += " AND comp_title LIKE '%' || ? || '%'";
				sql += " AND comp_content LIKE '%' || ? || '%'";

			} else {
				//제목으로 검색할 경우
				sql += " AND comp_title LIKE '%' || ? || '%'";
			}
			
		}
		
		int cnt = 0;

		try {
			//SQL수행객체
			ps = conn.prepareStatement(sql);
			
			if (search != null) {
				ps.setString(1, search);
				
				if (searchno == 3) {
					ps.setString(2, search);
					
				}
				
			}

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
		sql += "WHERE comp_no = ? ";
		
		CompBoard res = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, compBoard.getComp_no());
			
			rs = ps.executeQuery();
			
			//SQL 수행 결과 처리
			while( rs.next() ) {
				res = new CompBoard();
				
				res.setComp_no( rs.getInt("comp_no") );
				res.setUserno( rs.getInt("userno") );
				res.setComp_title( rs.getString("comp_title") );
				res.setComp_name( rs.getString("comp_name") );
				res.setComp_content( rs.getString("comp_content") );
				res.setComp_member( rs.getInt("comp_member") );
				res.setComp_date( rs.getDate("comp_date") );
				res.setComp_view( rs.getInt("comp_view") );
				res.setComp_reply( rs.getInt("comp_reply") );
				res.setComp_like( rs.getInt("comp_like") );
				res.setComp_startdate( rs.getInt("comp_startdate"));
				res.setComp_enddate( rs.getInt("comp_enddate"));
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

	@Override
	public List<CompBoard> compList(Paging paging) {
		
			conn = DBConn.getConnection(); //DB연결

			//수행할 DB쿼리
			String sql = "";
			sql += "SELECT * FROM (";
			sql += "    SELECT rownum rnum, B.* FROM (";
			sql += "		SELECT * FROM compBoard";
			
			//검색어가 존재할 때
			if (paging.getSearch() != null) {
				//WHERE 추가
				sql += " WHERE 1 = 1";
				
				if (paging.getSearchno() == 2) {
					//팀이름으로 검색할 경우
					sql += " AND comp_name LIKE '%' || ? || '%'";
				
				} else if (paging.getSearchno() == 3) {
					//제목&내용으로 검색할 경우
					sql += " AND comp_title LIKE '%' || ? || '%'";
					sql += " AND comp_content LIKE '%' || ? || '%'";
					
				} else {
					//제목으로 검색할 경우
					sql += " AND comp_title LIKE '%' || ? || '%'";
				}
			}
 
			sql += "        ORDER BY comp_no DESC";
			sql += "    )B";
			sql += "    ORDER BY rnum";
			sql += " ) compBoard";
			sql += " WHERE rnum BETWEEN ? AND ?";

//			System.out.println(sql);

			//결과 저장 리스트
			List<CompBoard> List = new ArrayList<CompBoard>();

			try {
				ps = conn.prepareStatement(sql);

				if (paging.getSearch() != null) {
					ps.setString(1, paging.getSearch());
					ps.setInt(2, paging.getStartNo());
					ps.setInt(3, paging.getEndNo());
					
					if (paging.getSearchno() == 3) {
						ps.setString(2, paging.getSearch());
						ps.setInt(3, paging.getStartNo());
						ps.setInt(4, paging.getEndNo());
					}
					
				} else {
					//검색어가 존재하지 않을 경우 (전체 리스트)
					ps.setInt(1, paging.getStartNo());
					ps.setInt(2, paging.getEndNo());
				}
				
				rs = ps.executeQuery();

				while(rs.next()) {
					CompBoard compBoard = new CompBoard();

					compBoard.setComp_no( rs.getInt("comp_no") );
					compBoard.setUserno( rs.getInt("userno") );
					compBoard.setComp_title( rs.getString("comp_title") );
					compBoard.setComp_name( rs.getString("comp_name") );
					compBoard.setComp_content( rs.getString("comp_content") );
					compBoard.setComp_member( rs.getInt("comp_member") );
					compBoard.setComp_date( rs.getDate("comp_date") );
					compBoard.setComp_view( rs.getInt("comp_view") );
					compBoard.setComp_reply( rs.getInt("comp_reply") );
					compBoard.setComp_like( rs.getInt("comp_like") );
					compBoard.setComp_startdate( rs.getInt("comp_startdate"));
					compBoard.setComp_enddate( rs.getInt("comp_enddate") );
					
					List.add(compBoard);
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
		
		return List;
	}

	@Override
	public void insert(CompBoard compBoard) {
		
		conn = DBConn.getConnection();
		
		String sql = "";
		sql +="INSERT INTO compBoard(comp_no, userno, comp_title, comp_name, ";
		sql +="		 				 comp_content, comp_member, comp_date, comp_view, ";
		sql +="						 comp_reply, comp_like, comp_startdate, comp_enddate )";
		sql +=" VALUES(?, ?, ?, ?, ?, ?, sysdate, 0, 0, 0, ?, ?)";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, compBoard.getComp_no());
			ps.setInt(2, compBoard.getUserno());
			ps.setString(3, compBoard.getComp_title());
			ps.setString(4, compBoard.getComp_name());
			ps.setString(5, compBoard.getComp_content());
			ps.setInt(6, compBoard.getComp_member());
			ps.setInt(7, compBoard.getComp_startdate());
			ps.setInt(8, compBoard.getComp_enddate());
			
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

	@Override
	public void countViews(CompBoard compBoard) {
		
		conn = DBConn.getConnection();
		
		//수행할 SQL쿼리
		String sql = "";
		sql += "UPDATE compBoard SET comp_view = comp_view + 1 ";
		sql += "WHERE comp_no = ? ";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, compBoard.getComp_no());
			
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
	}

	@Override
	public int selectCompBoardno() {
		
		//DB연결
		conn = DBConn.getConnection();

		String sql = "";
		sql += "SELECT compBoard_seq.nextval FROM dual";

		int nextval = -1;

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			rs.next();

			nextval = rs.getInt(1); //조회된 결과의 1번째 컬럼

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			try {
				if(ps!=null) ps.close();
				if(rs!=null) rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}



		return nextval;
	}

	@Override
	public void insertFile(Files files) {
		
		//DB연결
		conn = DBConn.getConnection();

		//수행할 SQL쿼리
		String sql = "";
		sql += "INSERT INTO files(fileno, postno, boardno, originname, storedname, filesize)";
		sql += " VALUES(files_seq.nextval, 4, ?, ?, ?, ?)";


		try {
			ps = conn.prepareStatement(sql);

			//SQL쿼리의 ?채우기
			ps.setInt(1, files.getBoardno());
			ps.setString(2, files.getOriginName());
			ps.setString(3, files.getStoredName());
			ps.setString(4, files.getFileSize());
			
			//System.out.println(files.getBoardno());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			try {
				if(ps!=null) ps.close();

			} catch (SQLException e) {
				e.printStackTrace();

			}
		}

		
	}


}
