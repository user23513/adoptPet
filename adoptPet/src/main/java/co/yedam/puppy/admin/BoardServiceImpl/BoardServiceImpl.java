package co.yedam.puppy.admin.BoardServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.yedam.puppy.board.service.BoardService;
import co.yedam.puppy.comm.DataSource;
import co.yedam.puppy.vo.BoardVO;

public class BoardServiceImpl implements BoardService{
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	List<BoardVO> list = new ArrayList<BoardVO>();
	BoardVO vo;
	String sql = "SELECT * FROM board ORDER BY NOTICE_ID DESC";//프라이머리키 순
	try {
		conn = dao.getConnection();
		psmt = conn.prepareStatement(sql);
		rs = psmt.executeQuery();
		while(rs.next()) {
			vo = new NoticeVO();
			vo.setNoticeId(rs.getInt("notice_id"));
			vo.setNoticeWriter(rs.getString("notice_writer"));
			vo.setNoticeTitle(rs.getString("notice_title"));
			vo.setNoticeDate(rs.getDate("notice_date"));
			vo.setNoticeAttech(rs.getString("notice_attech"));
			vo.setNoticeHit(rs.getInt("notice_hit"));
			list.add(vo);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		close();
	}
	return list;
	
	@Override
	public List<BoardVO> volReviewSelectList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardVO volReviewSelectOne(BoardVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int volReviewUpdate(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int volReviewDelete(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int volReviewInsert(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardVO> volReviewAddSelectList(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardVO> boardSelectList(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardVO boardSelect(BoardVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int noticeInsert(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int noticeUpdate(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int noticeDelete(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardVO> noticeSerarchList(String key, String val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardVO> adoptReviewSelectList(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardVO adoptReviewSelect(BoardVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int adoptReviewInsert(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int adoptReviewUpdate(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int adoptReviewDelete(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardVO> adoptReviewSearchList(String key, String val) {
		// TODO Auto-generated method stub
		return null;
	}

}
