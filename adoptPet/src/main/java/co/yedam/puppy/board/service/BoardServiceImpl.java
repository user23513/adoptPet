package co.yedam.puppy.board.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.puppy.comm.DataSource;
import co.yedam.puppy.vo.BoardVO;
import co.yedam.puppy.vo.FilesVO;

public class BoardServiceImpl implements BoardService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<BoardVO> volReviewSelectList(int currentPage, int startRow, int pageSize) {
		// 봉사(board_id=40)후기 게시판 전체조회 목록 (페이징처리) 
		List<BoardVO> list = new ArrayList<>();
		BoardVO vo;
		String sql = "SELECT *\r\n"
				+ "  FROM (\r\n"
				+ "        SELECT ROW_NUMBER() OVER (ORDER BY BOARD_NO DESC) NUM\r\n" 
				+ "             , A.*\r\n"
				+ "          FROM BOARD A\r\n" 
				+ "         ORDER BY BOARD_NO DESC\r\n" 
				+ "        ) \r\n"
				+ " WHERE NUM BETWEEN ? AND ? " 
				+ "AND BOARD_ID = 40";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, startRow); // 시작행-1(시작 row 인덱스 번호)
			psmt.setInt(2, pageSize * currentPage); // 페이지크기(한번에 출력되는 수)
			rs = psmt.executeQuery();

			while (rs.next()) {
				vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardId(rs.getInt("board_id"));
				vo.setBoardTitle(rs.getString("board_title"));
				vo.setBoardWriter(rs.getString("board_writer"));
				vo.setBoardContent(rs.getString("board_content"));
				vo.setBoardDate(rs.getDate("board_date"));
				vo.setBoardHit(rs.getInt("board_hit"));
				
				System.out.println(rs.getInt("board_no")+rs.getInt("board_id"));
				
				list.add(vo);
			}
			System.out.println("DAO: 봉사활동후기 저장완료!" + list.size());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	@Override
	public int volReviewCount() {
		// DB에 있는 봉사후기 list 글 갯수 확인하여 페이징 처리할때 사용
		int n = 0;
		String sql = "SELECT * FROM BOARD";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				n++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public BoardVO volReviewSelectOne(BoardVO vo) {
		// 단건조회
		String sql = "SELECT * FROM BOARD WHERE BOARD_NO = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getBoardNo());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardId(rs.getInt("board_Id"));
				vo.setBoardTitle(rs.getString("board_title"));
				vo.setBoardWriter(rs.getString("board_writer"));
				vo.setBoardContent(rs.getString("board_content"));
				vo.setBoardDate(rs.getDate("board_date"));
				vo.setBoardHit(rs.getInt("board_hit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	@Override
	public int volReviewInsert(BoardVO vo) {
		// 등록
		int r = 0;
		String sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL,40,?,?,?,SYSDATE,0)";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getBoardTitle());
			psmt.setString(2, vo.getBoardWriter());
			psmt.setString(3, vo.getBoardContent());

			r = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return r;
	}

	@Override
	public int volReviewUpdate(BoardVO vo) {
		// 수정
		int r = 0;
		String sql = "UPDATE BOARD SET BOARD_TITLE=?, BOARD_CONTENT=?  WHERE BOARD_NO=?";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, vo.getBoardTitle());
			psmt.setString(2, vo.getBoardContent());
			psmt.setInt(3, vo.getBoardNo());
			psmt.executeUpdate();
			
			r = 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return r;
	}

	@Override
	public int volReviewDelete(int boardNo) {
		// 봉사 삭제
		int r = 0;
		String sql = "DELETE FROM BOARD WHERE BOARD_NO=?";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			psmt.executeUpdate();
			r = 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return r;
	}

	@Override
	public List<BoardVO> volReviewSearchList(String key, String val) {
		// 봉사검색

		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo;
		String sql = "SELECT * FROM BOARD WHERE " + key + " LIKE '%" + val + "%'";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardId(rs.getInt("board_id"));
				vo.setBoardTitle(rs.getString("board_Title"));
				vo.setBoardWriter(rs.getString("board_Writer"));
				vo.setBoardContent(rs.getString("board_content"));
				vo.setBoardDate(rs.getDate("board_date"));
				vo.setBoardHit(rs.getInt("board_hit"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}



	@Override
	public List<BoardVO> boardSelectList(int currentPage, int startRow, int pageSize) {
		// 공지 전체목록
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo;
		String sql = "SELECT *\r\n"
				+ "  FROM (\r\n"
				+ "        SELECT ROW_NUMBER() OVER (ORDER BY BOARD_NO DESC) NUM\r\n"
				+ "             , A.*\r\n"
				+ "          FROM BOARD A\r\n"
				+ "         ORDER BY BOARD_NO DESC\r\n"
				+ "        ) \r\n"
				+ " WHERE NUM BETWEEN ? AND ?"
				+ "AND BOARD_ID = 10";

		conn = dao.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1,startRow);
			psmt.setInt(2,pageSize*currentPage);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardId(rs.getInt("board_Id"));
				vo.setBoardTitle(rs.getString("board_title"));
				vo.setBoardWriter(rs.getString("board_writer"));
				vo.setBoardContent(rs.getString("board_content"));
				vo.setBoardDate(rs.getDate("board_date"));
				vo.setBoardHit(rs.getInt("board_hit"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	@Override
	public BoardVO boardSelect(BoardVO vo) {
		// 공지 상세보기
		String sql = "SELECT * FROM BOARD WHERE BOARD_NO = ?";

		conn = dao.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getBoardNo());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardId(rs.getInt("board_Id"));
				vo.setBoardTitle(rs.getString("board_title"));
				vo.setBoardWriter(rs.getString("board_writer"));
				vo.setBoardContent(rs.getString("board_content"));
				vo.setBoardDate(rs.getDate("board_date"));
				vo.setBoardHit(rs.getInt("board_hit"));
//				fvo.setFilesNo(rs.getInt("files_no"));
//				fvo.setFilesName(rs.getString("files_name"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}
	@Override
	public int boardCount() {
		// DB에 있는 공지 글 갯수 확인
		int n = 0;
		String sql = "select * from board";
		
		conn = dao.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				n++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return n;
	}

	
	@Override
	public BoardVO boardSelectOne(BoardVO vo) {
		// 공지 단건 조회
		String sql = "select * from board where board_no=?";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getBoardNo());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardId(rs.getInt("board_Id"));
				vo.setBoardTitle(rs.getString("board_title"));
				vo.setBoardWriter(rs.getString("board_writer"));
				vo.setBoardContent(rs.getString("board_content"));
				vo.setBoardDate(rs.getDate("board_date"));
				vo.setBoardHit(rs.getInt("board_hit"));
//				fvo.setFilesNo(rs.getInt("files_no"));
//				fvo.setFilesName(rs.getString("files_name"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return null;
	}

	@Override
	public int noticeInsert(BoardVO vo) {
		// 공지 글쓰기
		int n = 0;
		String sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL,?,?,?,?,SYSDATE,0)";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
//			psmt.setInt(1, bvo.getBoardNo());  
			psmt.setInt(1, vo.getBoardId());
			psmt.setString(2, vo.getBoardTitle());
			psmt.setString(3, vo.getBoardWriter());
			psmt.setString(4, vo.getBoardContent());
//			psmt.setInt(5, fvo.getFilesNo());
//			psmt.setInt(6, vo.getBoardId());
//			psmt.setString(7, fvo.getFilesName());
//			psmt.setString(8, fvo.getFilesPath());
//			psmt.setString(9, fvo.getFilesType());

			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return n;
	}

	@Override
	public int noticeUpdate(BoardVO vo) {
		// 공지 수정
		int n = 0;
		String sql = "UPDATE BOARD SET  BOARD_ID=?, BOARD_TITLE=?, BOARD_CONTENT=? WHERE BOARD_NO=?";
//		String sqlF = "UPDATE FILES SET  FILES_NAME=?, FILES_PATH=?, FILES_TYPE=? WHERE FILES_NO=?";

		try {
			conn = dao.getConnection();
			conn.setAutoCommit(false);

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getBoardId());
			psmt.setString(2, vo.getBoardTitle());
			psmt.setString(3, vo.getBoardContent());
			psmt.setInt(4, vo.getBoardNo());

//			psmt = conn.prepareStatement(sqlF);
//			psmt.setString(1, fvo.getFilesName());
//			psmt.setString(2, fvo.getFilesPath());
//			psmt.setInt(3, fvo.getBoardNo());
			psmt.executeUpdate();

			conn.commit();
			n = 1;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException ee) {
				ee.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close();
		}
		return n;
	}

	@Override
	public int noticeDelete(BoardVO bvo, FilesVO fvo) {
		// 공지 삭제
		int n = 0;
		String sql = "DELETE FROM BOARD WHERE BOARD_NO=?";
//		String sqlF = "DELETE FROM FILES WHERE FILES_NO=?";
		conn = dao.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bvo.getBoardNo());

//			psmt = conn.prepareStatement(sqlF);
//			psmt.setInt(1, fvo.getFilesNo());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public List<BoardVO> noticeSerarchList(String key, String val) {
		// 공지 검색
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo;
		String sql = "select * from where" + key + "like'%" + val + "%'";

		conn = dao.getConnection();
		try {
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardId(rs.getInt("board_id"));
				vo.setBoardTitle(rs.getString("board_Title"));
				vo.setBoardWriter(rs.getString("board_Writer"));
				vo.setBoardContent(rs.getString("board_content"));
				vo.setBoardDate(rs.getDate("board_date"));
				vo.setBoardHit(rs.getInt("board_hit"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	@Override
	public List<BoardVO> adoptReviewSelectList(int currentPage, int startRow, int pageSize) {
		// 후기게시판 목록
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo;
		String sql = "FROM (SELECT ROW_NUMBER() OVER (ORDER BY BOARD_NO DESC) NUM\r\n"
				+ " , A.*\r\n"
				+ " FROM board A\r\n"
				+ "ORDER BY BOARD_NO DESC)\r\n"
				+ " WHERE NUM BETWEEN ? AND ?"
				+ "AND BOARD_ID = 20";

		conn = dao.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, startRow); 
			psmt.setInt(2, pageSize*currentPage);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardId(rs.getInt("board_Id"));
				vo.setBoardTitle(rs.getString("board_title"));
				vo.setBoardWriter(rs.getString("board_writer"));
				vo.setBoardContent(rs.getString("board_content"));
				vo.setBoardDate(rs.getDate("board_date"));
				vo.setBoardHit(rs.getInt("board_hit"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;

	}
	@Override
	public int apodtReviewCount() {
		// DB공지 갯수 확인
		int n = 0;
		String sql="select * from board where  board_id=20";
		
		conn = dao.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				n++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return n;
	}

	@Override
	public BoardVO adoptReviewSelect(BoardVO bvo,FilesVO fvo) {
		//후기 상세보기
		String sql = "SELECT * FROM BOARD WHERE BOARD_NO = ?";
		
		conn = dao.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bvo.getBoardNo());
			rs = psmt.executeQuery();
			if(rs.next()) {
				bvo.setBoardNo(rs.getInt("board_no"));
				bvo.setBoardId(rs.getInt("board_Id"));
				bvo.setBoardTitle(rs.getString("board_title"));
				bvo.setBoardWriter(rs.getString("board_writer"));
				bvo.setBoardContent(rs.getString("board_content"));
				bvo.setBoardDate(rs.getDate("board_date"));
				bvo.setBoardHit(rs.getInt("board_hit"));
				fvo.setFilesNo(rs.getInt("files_no"));
				fvo.setFilesName(rs.getString("files_name"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return bvo;
	}
	@Override
	public BoardVO adoptFeviewSelectOne(BoardVO vo) {
		// 후기 단건 조회
		return null;
	}

	@Override
	public int adoptReviewInsert(BoardVO bvo,FilesVO fvo) {
		// 후기 글쓰기
		int n = 0;
		String sql = "INSERT ALL INTO BOARD VALUES(BOARD_SEQ.NEXTVAL,?,?,?,?,SYSDATE,0)\r\n"
				+ "            INTO FILES VALUES(?,?,?,?,?)\r\n"
				+ "SELECT * FROM DUAL";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
//			psmt.setInt(1, bvo.getBoardNo());
			psmt.setInt(1, bvo.getBoardId());
			psmt.setString(2, bvo.getBoardTitle());
			psmt.setString(3, bvo.getBoardWriter());
			psmt.setString(4, bvo.getBoardContent());
			psmt.setInt(5, fvo.getFilesNo());
			psmt.setInt(6, bvo.getBoardId());
			psmt.setString(7, fvo.getFilesName());
			psmt.setString(8, fvo.getFilesPath());
			psmt.setString(9, fvo.getFilesType());
			
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}
	

	@Override
	public int adoptReviewUpdate(BoardVO bvo,FilesVO fvo) {
		// 후기 수정
		int n = 0;
		String sql = "UPDATE BOARD SET  BOARD_ID=?, BOARD_TITLE=?, BOARD_CONTENT=? WHERE BOARD_NO=?";
		String sqlF ="UPDATE FILES SET  FILES_NAME=?, FILES_PATH=?, FILES_TYPE=? WHERE FILES_NO=?";
		
		try {
			conn = dao.getConnection();
			conn.setAutoCommit(false);
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bvo.getBoardId());
			psmt.setString(2, bvo.getBoardTitle());
			psmt.setString(3, bvo.getBoardContent());
			psmt.setInt(4, bvo.getBoardNo());
			
			psmt = conn.prepareStatement(sqlF);
			psmt.setString(1, fvo.getFilesName());
			psmt.setString(2, fvo.getFilesPath());
			psmt.setInt(3, fvo.getBoardNo());
			psmt.executeUpdate();
			
			conn.commit();
			n = 1;
		} catch (SQLException e) {
			try {
				conn.rollback();
			}catch(SQLException ee) {
				ee.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close();
		}
		return n;
	}

	@Override
	public int adoptReviewDelete(BoardVO bvo,FilesVO fvo) {
		// 후기 삭제
		int n = 0;
		String sqlB = "DELETE FROM BOARD WHERE BOARD_NO=?";
		String sqlF ="DELETE FROM FILES WHERE FILES_NO=?";
		conn = dao.getConnection();
		try {
			psmt = conn.prepareStatement(sqlB);
			psmt.setInt(1, bvo.getBoardNo());
			
			psmt = conn.prepareStatement(sqlF);
			psmt.setInt(1, fvo.getFilesNo());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

	@Override
	public List<BoardVO> adoptReviewSearchList(String key, String val) {
		// 후기 검색
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo;
		String sql = "select * from where"+key+"like'%" + val +"%'";
		
		conn = dao.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardId(rs.getInt("board_id"));
				vo.setBoardTitle(rs.getString("board_Title"));
				vo.setBoardWriter(rs.getString("board_Writer"));
				vo.setBoardContent(rs.getString("board_content"));
				vo.setBoardDate(rs.getDate("board_date"));
				vo.setBoardHit(rs.getInt("board_hit"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}



	
	
	//문의게시판
	@Override
	public List<BoardVO> qnaBoardSelectList(int currentPage, int startRow, int pageSize) {
		// 문의게시판 전체 목록
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo;
		
		String sql = "SELECT *\r\n"
				+ "  FROM (\r\n"
				+ "        SELECT ROW_NUMBER() OVER (ORDER BY BOARD_NO DESC) NUM\r\n"
				+ "             , A.*\r\n"
				+ "          FROM BOARD A\r\n"
				+ "         ORDER BY BOARD_NO DESC\r\n"
				+ "        ) \r\n"
				+ " WHERE NUM BETWEEN ? AND ?"
				+ "AND BOARD_ID = 30";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, startRow);
			psmt.setInt(2, pageSize*currentPage);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardId(rs.getInt("board_id"));
				vo.setBoardTitle(rs.getString("board_title"));
				vo.setBoardWriter(rs.getString("board_writer"));
				vo.setBoardContent(rs.getString("board_content"));
				vo.setBoardDate(rs.getDate("board_date"));
				vo.setBoardHit(rs.getInt("board_hit"));
				list.add(vo);
			}
			System.out.println("DAO:글 정보 저장 완료. " + list.size()); 
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	
	
	
	
	

	@Override
	public int qnaBoardCount() {
		// db 갯수 확인
		int n = 0;
		String sql = "SELECT * FROM BOARD"; // ! ! 쿼리문 확인해보기 ! !
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				n++;
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

	@Override
	public BoardVO qnaBoardSelect(BoardVO vo) {
		// 문의글 상세보기
		String sql = "SELECT * FROM BOARD WHERE BOARD_NO=?"; // ! ! 쿼리문 확인하기 ! ! 
		
		try {
				conn = dao.getConnection();
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, vo.getBoardNo());
				rs = psmt.executeQuery();
				if(rs.next()) {
					vo.setBoardNo(rs.getInt("board_no"));
					vo.setBoardId(rs.getInt("board_Id"));
					vo.setBoardTitle(rs.getString("board_title"));
					vo.setBoardWriter(rs.getString("board_writer"));
					vo.setBoardContent(rs.getString("board_content"));
					vo.setBoardDate(rs.getDate("board_date"));
					vo.setBoardHit(rs.getInt("board_hit"));
				}
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
	}


	@Override
	public int qnaBoardInsert(BoardVO vo) {
		// 문의게시판 글 쓰기
		int n = 0;
		String sql = "INSERT INTO BOARD VALUES(notice_seq.nextval,30,?,?,?,SYSDATE,0)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
//			psmt.setInt(1, vo.getBoardId());
			psmt.setString(1, vo.getBoardTitle());
			psmt.setString(2, vo.getBoardWriter());
			psmt.setString(3, vo.getBoardContent());

			n = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

	@Override
	public int qnaBoardUpdate(BoardVO vo) {
		// 문의게시판 글 수정
		int n = 0;
		String sql = "UPDATE BOARD SET BOARD_ID=?, BOARD_TITLE=?, BOARD_CONTENT=? WHERE BOARD_NO=?";
		
		try {
			conn=dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getBoardId());
			psmt.setString(2, vo.getBoardTitle());
			psmt.setString(3, vo.getBoardWriter());
			psmt.setString(4, vo.getBoardContent());
			psmt.executeUpdate();
			
			n=1;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

	@Override
	public int qnaBoardDelete(BoardVO vo) { 
		// 문의게시판 글 삭제
		int n = 0;
		String sql = "DELETE FROM BOARD WHERE BOARD_NO=?"; 
		
		try {
			conn=dao.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, vo.getBoardNo());
			psmt.executeUpdate();
			
			n = 1;  
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

	@Override
	public List<BoardVO> qnaBoardSearchList(String key, String val) {
		// 문의게시판 글 검색
		List<BoardVO>list = new ArrayList<BoardVO>();
		BoardVO vo;
		String sql = "SELECT * FROM WHERE" + key + "LIKE'%" + val + "%'";
		try {
			conn=dao.getConnection();
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return null;
	}

	
	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}