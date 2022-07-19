package co.yedam.puppy.adoptSubscription.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import co.yedam.puppy.comm.DataSource;
import co.yedam.puppy.vo.AdoptSubscriptionVO;
import co.yedam.puppy.vo.PetListVO;

public class AdoptSubscriptionServiceImpl implements AdoptSubscriptionService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<AdoptSubscriptionVO> myAdoptSubList(AdoptSubscriptionVO vo) {
		//나의입양신청
		List<AdoptSubscriptionVO> list = new ArrayList<AdoptSubscriptionVO>();
		String sql = "select * from adopt_subscription where member_id=?";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,vo.getMemberId());
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new AdoptSubscriptionVO();
				vo.setMemberId(rs.getString("member_id"));
				vo.setPetAddNo(rs.getInt("pet_add_no"));
				vo.setAdoptSubscriptionOk(rs.getString("adopt_subscription_ok"));
				vo.setAdoptSubscriptionReason(rs.getString("adopt_subscription_reason"));
				list.add(vo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}




	@Override
	public int AdoptSubInsert(AdoptSubscriptionVO vo) {
		//입양동물 신청 form내용 db에 등록
		int r = 0;
		String sql = "INSERT INTO ADOPT_SUBSCRIPTION VALUES(?,?,?,?)";
		
		try {
			    conn = dao.getConnection();
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, vo.getMemberId());
				psmt.setInt(2, vo.getPetAddNo());
				psmt.setString(3, vo.getAdoptSubscriptionOk());
				psmt.setString(4, vo.getAdoptSubscriptionReason());
				
				r = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return r;
	}

	@Override
	public int myAdoptSubUpdate(AdoptSubscriptionVO vo) {

		// 나의 입양신청 수정

		return 0;
	}

	@Override
	public int myAdoptSubDelete(AdoptSubscriptionVO vo) {

		// 나의 입양신청 삭제
		return 0;
	}
	
	@Override
	public boolean isAdoptSubCheck(String memberId, int petAddNo) {
		//같은동물 입양신청했는지 확인하는(true면 신청한적이 없고 false면 신청한적이 있음=>true면 신청버튼보이게)
		boolean check = true;
		String sql = "select member_id from adopt_subscription where member_id=? and pet_add_no=?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, memberId);
			psmt.setInt(2, petAddNo);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				check=false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return check;
	}
	

	private void close() {
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
