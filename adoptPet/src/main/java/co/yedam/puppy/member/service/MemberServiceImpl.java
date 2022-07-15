package co.yedam.puppy.member.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.yedam.puppy.comm.DataSource;
import co.yedam.puppy.member.service.MemberService;
import co.yedam.puppy.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	@Override
	public int memberInsert(MemberVO vo) {
		// 회원등록(회원가입)
		int n = 0;
		String sql = "INSERT INTO MEMBER(MEMBER_ID, MEMBER_PASSWORD, MEMBER_NAME, "
				+ "MEMBER_TEL, MEMBER_EMAIL, MEMBER_JOB, MEMBER_GENDER, MEMBER_AUTHOR)"
				+ " VALUES(?,?,?,?,?,?,?,?)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			psmt.setString(2, vo.getMemberPassword());
			psmt.setString(3, vo.getMemberName());
			psmt.setString(4, vo.getMemberTel());
			psmt.setString(5, vo.getMemberEmail());
			psmt.setString(6, vo.getMemberJob());
			psmt.setString(7, vo.getMemberGender());
			psmt.setString(8, vo.getMemberAuthor());
			n = psmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

	@Override
	public boolean isMemberIdcheck(String id) {
		// 회원 아이디 중복체크
		boolean b = false;
		String sql = "SELECT MEMBER_ID FROM MEMBER WHERE MEMBER_ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(!rs.next()) {
				b=true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return b;
	}
	
	
	
	
	@Override
	public MemberVO memberSelectOne(MemberVO vo) {
		// 한명정보불러오기
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ? ";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			//psmt.setString(1, vo.getMemberId());
			psmt.setString(1,vo.getMemberId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setMemberId(rs.getString("member_id"));
				vo.setMemberPassword(rs.getString("member_password"));
				vo.setMemberName(rs.getString("member_name"));
				vo.setMemberTel(rs.getString("member_tel"));
				vo.setMemberEmail(rs.getString("member_email"));
				vo.setMemberJob(rs.getString("member_job"));
				vo.setMemberGender(rs.getString("member_gender"));
				vo.setMemberAuthor(rs.getString("member_author"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		// 한명정보수정하기
		String sql = "UPDATE MEMBER SET  "
				+ "MEMBER_TEL= ? , MEMBER_EMAIL= ? , MEMBER_JOB= ? , MEMBER_AUTHOR= ? "
				+ "WHERE MEMBER_ID= ? ";
		int r = 0;
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberTel());
			psmt.setString(2, vo.getMemberEmail());
			psmt.setString(3, vo.getMemberJob());
			psmt.setString(4, vo.getMemberAuthor());
			psmt.setString(5, vo.getMemberId());

			r = psmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return r;

	}

	@Override
	public int memberDelete(String id) {
		int n = 0;
		String sql = "DELETE FROM MEMBER WHERE MEMBER_ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			n = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

	@Override
	public MemberVO memberLogin(MemberVO vo) {
		//회원 로그인
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PASSWORD = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			psmt.setString(2, vo.getMemberPassword());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setMemberId(rs.getString("member_id"));
				vo.setMemberName(rs.getString("member_name"));
				vo.setMemberAuthor(rs.getString("member_author"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
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
