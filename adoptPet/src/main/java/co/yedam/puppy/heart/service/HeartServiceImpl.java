package co.yedam.puppy.heart.service;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import co.yedam.puppy.comm.DataSource;
import co.yedam.puppy.vo.HeartVO;

public class HeartServiceImpl implements HeartService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<HeartVO> myHeartList(int startRow, int pageSize) {
		//내가누른하트리스트
		return null;
	}

	@Override
	public int heartCheck(HeartVO vo) {
		//하트클릭여부 체크(1이면 체크되어있는거 0이면 체크안되어있는거)
		int r = 0;
		String sql = "SELECT MEMBER_ID FROM HEART WHERE MEMBER_ID=? AND PET_LIST_NO=?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			psmt.setInt(2, vo.getPetListNo());
			rs = psmt.executeQuery();
			if(rs.next()) {
				r++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return r;
	}

	@Override
	public int heartInsert(HeartVO vo) {
		//하트클릭여부가 0이면 하트디비에 정보가 입력되게
		int r = 0;
		String sql = "insert into heart values(?,?)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			psmt.setInt(2, vo.getPetListNo());
			r = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return r;
	}
	
	@Override
	public int heartDelete(HeartVO vo) {
		//하트클릭여부가 1이면 하트디비에 정보삭제
		int r = 0;
		String sql = "DELETE FROM HEART WHERE MEMBER_ID=? AND PET_LIST_NO=?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			psmt.setInt(2, vo.getPetListNo());
			r = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return r;
	}

	@Override
	public int heartCount(int petListNo) {
		//입양동물소개게시판 번호를 가지고 하트수를 카운트해서 뿌려주기
		int cnt = 0;
		String sql = "SELECT COUNT(*) FROM HEART WHERE PET_LIST_NO=? ";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, petListNo);
			rs = psmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt("count(*)");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return cnt;
	}
	
	private void close() {
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
