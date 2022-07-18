package co.yedam.puppy.admin.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.yedam.puppy.comm.DataSource;
import co.yedam.puppy.vo.AdoptSubscriptionVO;
import co.yedam.puppy.vo.MemberVO;
import co.yedam.puppy.vo.VolunteerSubscriptionVO;

public class AdminServiceImple implements AdminService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<MemberVO> allMemberList(int currentPage, int startRow, int pageSize) {
		// 모든회원리스트 
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO vo;
		String sql = "select * from (select row_number() over(order by member_id) num  , M.* from member M ) where num between ? and ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, startRow);
			psmt.setInt(2, pageSize*currentPage);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				vo = new MemberVO();
				vo.setMemberId(rs.getString("member_id"));
				vo.setMemberPassword(rs.getString("member_password"));
				vo.setMemberName(rs.getString("member_name"));
				vo.setMemberEmail(rs.getString("member_email"));
				vo.setMemberGender(rs.getString("member_gender"));
				vo.setMemberJob(rs.getString("member_job"));
				vo.setMemberTel(rs.getString("member_tel"));
				vo.setMemberAuthor(rs.getString("member_author"));
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
	public int updateMemberList(MemberVO vo) {
		//회원수정(권한만수정가능)
		int n = 0;
		String sql = "update member set member_author = ? where member_id=?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberAuthor());
			psmt.setString(2, vo.getMemberId());
			n = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

	@Override
	public int deleteMemberList(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public List<AdoptSubscriptionVO> allAdoptList(int currentPage, int startRow, int pageSize) {
		// 모든입양신청리스트 
		List<AdoptSubscriptionVO> list = new ArrayList<AdoptSubscriptionVO>();
		AdoptSubscriptionVO vo;
		String sql = "select * from (select row_number() over(order by ADOPT_SUBSCRIPTION_OK desc) num  , A.* from ADOPT_SUBSCRIPTION A ) where num between ? and ?";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, startRow);
			psmt.setInt(2, currentPage*currentPage);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				vo = new AdoptSubscriptionVO();
				vo.setMemberId(rs.getString("member_id"));
				vo.setPetListNo(rs.getInt("pet_list_no"));
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
	public int updateAdoptList(AdoptSubscriptionVO vo) {
		//입양상태수정 
		int n = 0;
		String sql = "update adopt_subscription set ADOPT_SUBSCRIPTION_OK where member_id=? and PET_LIST_NO=?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getAdoptSubscriptionOk());
			psmt.setString(2, vo.getMemberId());
			n = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

	
	@Override
	public List<VolunteerSubscriptionVO> allVolunteerList(int currentPage, int startRow, int pageSize) {
		// 모든봉사참여신청리스트 
		List<VolunteerSubscriptionVO> list = new ArrayList<VolunteerSubscriptionVO>();
		VolunteerSubscriptionVO vo;
		String sql = "select * from (select row_number() over(order by VOLUNTEER_SUBSCRIPTION_OK desc) num  , V.* from VOLUNTEER_SUBSCRIPTION V ) where num between ? and ?";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, startRow);
			psmt.setInt(2, currentPage*currentPage);
			rs = psmt.executeQuery();	
			
			while(rs.next()) {
				vo = new VolunteerSubscriptionVO();
				vo.setMemberId(rs.getString("member_id"));
				vo.setCalendarNo(rs.getInt("calendar_no"));
				vo.setVolunteerSubscriptionOk(rs.getString("VOLUNTEER_SUBSCRIPTION_OK"));
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
	public int updateVolunteerList(VolunteerSubscriptionVO vo) {
		//봉사신청상태수정 
		int n = 0;
		String sql = "update volunteer_subscription set volunteer_SUBSCRIPTION_OK where member_id=? and calendar_NO=?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getVolunteerSubscriptionOk());
			psmt.setString(2, vo.getMemberId());
			n = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
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


	@Override
	public int memberListCount() {
		// 회원수확인 
		int n = 0;
		String sql = "select * from member";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				n++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

}
