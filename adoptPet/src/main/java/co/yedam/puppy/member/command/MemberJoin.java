package co.yedam.puppy.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.member.service.MemberService;
import co.yedam.puppy.member.service.MemberServiceImpl;
import co.yedam.puppy.vo.MemberVO;

public class MemberJoin implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 회원가입 처리
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setMemberId(request.getParameter("memberId"));
		vo.setMemberPassword(request.getParameter("memberPassword"));
		vo.setMemberName(request.getParameter("memberName"));
		vo.setMemberTel(request.getParameter("memberTel"));
		vo.setMemberEmail(request.getParameter("memberEmail"));
		vo.setMemberJob(request.getParameter("memberJob"));
		vo.setMemberGender(request.getParameter("memberGender"));
		System.out.println(request.getParameter("memberGender"));
		vo.setMemberAuthor("USER");
		
		int n = memberDao.memberInsert(vo);
		if(n != 0) {
			request.setAttribute("message", "회원가입 완!");
			return "member/memberLoginForm";
		}else {
			request.setAttribute("message", "회원가입 실패");
			return "member/memberJoinForm";
		}
	}

}
