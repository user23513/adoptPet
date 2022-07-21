package co.yedam.puppy.admin.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.yedam.puppy.comm.DataSource;
import co.yedam.puppy.vo.AdoptSubscriptionVO;
import co.yedam.puppy.vo.MemberVO;
import co.yedam.puppy.vo.PetListVO;
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
		String sql = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY MEMBER_ID) NUM  , M.* FROM MEMBER M ) WHERE NUM BETWEEN ? AND ?";
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
		String sql = "UPDATE MEMBER SET MEMBER_AUTHOR = ? WHERE MEMBER_ID=?";
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
	public List<AdoptSubscriptionVO> allAdoptList(int currentPage, int startRow, int pageSize) {
		// 모든입양신청리스트 
		List<AdoptSubscriptionVO> list = new ArrayList<AdoptSubscriptionVO>();
		AdoptSubscriptionVO vo;
		String sql = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY PET_ADD_NO DESC) NUM  , A.* FROM ADOPT_SUBSCRIPTION A ) WHERE NUM BETWEEN ? AND ?";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, startRow);
			psmt.setInt(2, currentPage*pageSize);
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
	public int adoptListUpdate(AdoptSubscriptionVO vo) {
		//입양상태수정 
		int n = 0;
		String sql = "UPDATE ADOPT_SUBSCRIPTION SET ADOPT_SUBSCRIPTION_OK=? WHERE MEMBER_ID=? AND PET_ADD_NO=?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getAdoptSubscriptionOk());
			psmt.setString(2, vo.getMemberId());
			psmt.setInt(3, vo.getPetAddNo());
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
			psmt.setInt(2, currentPage*pageSize);
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

	@Override
	public int memberListCount() {
		// 회원수확인 
		int n = 0;
		String sql = "SELECT * FROM MEMBER";
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


	@Override
	public int adoptListCount() {
		//봉사신청수확인
		int n = 0;
		String sql = "SELECT * FROM ADOPT_SUBSCRIPTION";
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


	@Override
	public List<AdoptSubscriptionVO> AdoptSubscriptionSearchList(String key, String val) {
		// 입양승인완료,입양승인대기,입양승인불가 검색정렬하기
		List<AdoptSubscriptionVO> list = new ArrayList<AdoptSubscriptionVO>();
		AdoptSubscriptionVO vo;
		String sql = "SELECT * FROM ADOPT_SUBSCRIPTION WHERE "+key+" LIKE '%"+val+"%' ORDER BY PET_ADD_NO";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
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
	public int volunteerListCount() {

		//봉사신청수확인
		int n = 0;
		String sql = "SELECT * FROM VOLUNTEER_SUBSCRIPTION";
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

	@Override
	public List<VolunteerSubscriptionVO> volunteerSubscriptionSearchList(String key, String val) {
		// 봉사신청승인완료,승인대기,승인불가 검색정렬하기
		List<VolunteerSubscriptionVO> list = new ArrayList<VolunteerSubscriptionVO>();
		VolunteerSubscriptionVO vo;
		String sql = "select * from volunteer_SUBSCRIPTION where "+key+" like '%"+val+"%' order by calendar_no";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new VolunteerSubscriptionVO();
				vo.setMemberId(rs.getString("member_id"));
				vo.setCalendarNo(rs.getInt("calendar_no"));
				vo.setVolunteerSubscriptionOk(rs.getString("volunteer_subscription_ok"));
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
	public AdoptSubscriptionVO adoptOneView(AdoptSubscriptionVO vo) {
		// 입양신청 단건조회
		String sql = "SELECT * FROM  ADOPT_SUBSCRIPTION "
				+ "WHERE MEMBER_ID=? AND PET_ADD_NO=?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			psmt.setInt(2, vo.getPetAddNo());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo = new AdoptSubscriptionVO();
				vo.setMemberId(rs.getString("member_id"));
				vo.setPetAddNo(rs.getInt("pet_add_no"));
				vo.setAdoptSubscriptionOk(rs.getString("adopt_subscription_ok"));
				vo.setAdoptSubscriptionReason(rs.getString("adopt_subscription_reason"));
		
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
	}


	@Override
	public PetListVO petListSelectOne(PetListVO vo) {
		//입양동물소개게시판 단건조회
		String sql = "SELECT * FROM PET_LIST WHERE PET_ADD_NO=?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getPetAddNo());
			rs = psmt.executeQuery();
			
			if(rs.next()) {
					vo.setPetListNo(rs.getInt("PET_LIST_NO"));
					vo.setBoardId(rs.getInt("BOARD_ID"));
					vo.setPetListTitle(rs.getString("PET_LIST_TITLE"));
					vo.setPetListContent(rs.getString("PET_LIST_CONTENT"));
					vo.setPetListWriter(rs.getString("PET_LIST_WRITER"));
					vo.setPetListState(rs.getString("PET_LIST_STATE"));
					vo.setPetListType(rs.getString("PET_LIST_TYPE"));
					vo.setPetAddNo(rs.getInt("PET_ADD_NO"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return vo;
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
