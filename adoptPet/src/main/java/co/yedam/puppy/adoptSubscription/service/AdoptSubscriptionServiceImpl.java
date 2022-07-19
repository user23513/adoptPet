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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int myAdoptSubUpdate(AdoptSubscriptionVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int myAdoptSubDelete(AdoptSubscriptionVO vo) {
		// TODO Auto-generated method stub
		return 0;
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
