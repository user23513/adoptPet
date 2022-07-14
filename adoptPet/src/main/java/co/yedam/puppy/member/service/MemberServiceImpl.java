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
		return 0;
	}

	@Override
	public MemberVO memberSelectOne(MemberVO vo) {
		// 한명정보불러오기
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID= ? ";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo.setMemberId(rs.getString("member_id"));
				vo.setMemberPassword(rs.getString("member_password"));
				vo.setMemberName(rs.getString("member_name"));
				vo.setMemberTel(rs.getString("member_tel"));
				vo.setMemberEmail(rs.getString("member_email"));
				vo.setMemberJob(rs.getString("member_job"));
				vo.setMemberGender(rs.getString("member_gender"));
				vo.setMemberAuthor(rs.getString("member_author"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		// 한명정보수정하기
		String sql = "UPDATE MEMBER SET MEMBER_PASSWORD=? , "
				+ "MEMBER_TEL= ? , MEMBER_EMAIL= ? , MEMBER_JOB= ? , MEMBER_AUTHOR= ? "
				+ "WHERE MEMBER_ID= ? ";
		int r = 0;
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberPassword());
			psmt.setString(2, vo.getMemberTel());
			psmt.setString(3, vo.getMemberEmail());
			psmt.setString(4, vo.getMemberJob());
			psmt.setString(5, vo.getMemberAuthor());
			psmt.setString(6, vo.getMemberId());
			r = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return r;
	}

	@Override
	public int memberDelete(MemberVO vo) {
		// 한명정보삭제(탈퇴)
		String sql = "DELETE FROM MEMBER WHERE MEMBER_ID=?";
		int r =0;
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			r = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return r;
	}

	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
