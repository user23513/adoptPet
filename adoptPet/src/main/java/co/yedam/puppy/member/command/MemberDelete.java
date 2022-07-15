package co.yedam.puppy.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.member.service.MemberService;
import co.yedam.puppy.member.service.MemberServiceImpl;

public class MemberDelete implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 회원탈퇴하기
		MemberService memberDao = new MemberServiceImpl();
		HttpSession session = request.getSession();
		//MemberVO vo = new MemberVO();
		String id = request.getParameter("memberId");
		int n = memberDao.memberDelete(id);
		System.out.println(n);
		if(n != 0) {
			session.invalidate();
			request.setAttribute("memberDeleteComplete", "회원탈퇴가 완료되었습니다.");
		}
		
		return "member/memberMyPageConfirm"; //삭제후이동할페이지
	}

}
