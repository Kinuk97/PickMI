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
	public int selectCntAll(String search, int categoryno) {

		conn = DBConn.getConnection(); //DB연결

		//수행할 DB쿼리
		String sql = "";
		sql += "SELECT count(*) FROM compBoard ";

		//검색어가 존재하거나 카테고리가 존재한다면
		if (search != null || categoryno != 0) {
			
			//WHERE  추가
			sql += "WHERE 1 = 1";
			
			//검색어가 존재한다면 검색조건 추가
			if (search != null) {
				sql += " AND comp_title LIKE '%' || ? || '%'";
			}
			
			//카테고리가 존재한다면 카테고리 추가
			if (categoryno != 0) {
				sql += " AND categoryno = ?";
			}
		}
		
		int cnt = 0;

		//결과 저장 리스트
		//List<Board> getList = new ArrayList<>();

		try {
			//SQL수행객체
			ps = conn.prepareStatement(sql);
			
			if (search != null && categoryno == 0) {
				ps.setString(1, search);
				
			} else if (categoryno != 0 && search == null) {
				ps.setInt(1, categoryno);
				
			} else if (search != null && categoryno != 0) {
				ps.setString(1, search);
				ps.setInt(2, categoryno);
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
				res.setCategoryno( rs.getInt("categoryno") );
				res.setFileno( rs.getInt("fileno") );
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
			if (paging.getSearch() != null || paging.getCategoryno() != 0) {
				sql += " WHERE 1 = 1";
				
				if (paging.getSearch() != null) {
					sql += " AND comp_title LIKE '%' || ? || '%'";
				}
				
				if (paging.getCategoryno() != 0) {
					sql += " AND categoryno = ?";
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

				if (paging.getSearch() != null && paging.getCategoryno() == 0) {
					ps.setString(1, paging.getSearch());
					ps.setInt(2, paging.getStartNo());
					ps.setInt(3, paging.getEndNo());
					
				} else if (paging.getCategoryno() != 0 && paging.getSearch() == null) {
					ps.setInt(1, paging.getCategoryno());
					ps.setInt(2, paging.getStartNo());
					ps.setInt(3, paging.getEndNo());
					
				} else if (paging.getSearch() != null && paging.getCategoryno() != 0) {
					ps.setString(1, paging.getSearch());
					ps.setInt(2, paging.getCategoryno());
					ps.setInt(3, paging.getStartNo());
					ps.setInt(4, paging.getEndNo());
					
				} else {
					ps.setInt(1, paging.getStartNo());
					ps.setInt(2, paging.getEndNo());
				}

				rs = ps.executeQuery();

				while(rs.next()) {
					CompBoard compBoard = new CompBoard();

					compBoard.setComp_no( rs.getInt("comp_no") );
					compBoard.setCategoryno( rs.getInt("categoryno") );
					compBoard.setFileno( rs.getInt("fileno") );
					compBoard.setUserno( rs.getInt("userno") );
					compBoard.setComp_title( rs.getString("comp_title") );
					compBoard.setComp_name( rs.getString("comp_name") );
					compBoard.setComp_content( rs.getString("comp_content") );
					compBoard.setComp_member( rs.getInt("comp_member") );
					compBoard.setComp_date( rs.getDate("comp_date") );
					compBoard.setComp_view( rs.getInt("comp_view") );
					compBoard.setComp_reply( rs.getInt("comp_reply") );
					compBoard.setComp_like( rs.getInt("comp_like") );
					
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
		sql +="INSERT INTO compBoard(comp_no, categoryno, fileno, userno, comp_title, comp_name, ";
		sql +="		 				 comp_content, comp_member, comp_date, comp_view, ";
		sql +="						 comp_reply, comp_like )";
		sql +=" VALUES(?, ?, ?, ?, )";
				
		
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
		sql += "INSERT INTO files(fileno, postno, filename, boardno)";
		sql += " VALUES(files_seq.nextval, 4, ?, ?)";


		try {
			ps = conn.prepareStatement(sql);

			//SQL쿼리의 ?채우기
			ps.setString(1, files.getFilename());
			ps.setInt(2, files.getBoardno());

			//					System.out.println(files.getBoardno());

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
