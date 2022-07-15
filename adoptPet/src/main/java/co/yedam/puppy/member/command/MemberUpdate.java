package co.yedam.puppy.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.member.service.MemberService;
import co.yedam.puppy.member.service.MemberServiceImpl;
import co.yedam.puppy.vo.MemberVO;

public class MemberUpdate implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 수정완료
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setMemberTel(request.getParameter("memberTel"));
		vo.setMemberEmail(request.getParameter("memberEmail"));
		vo.setMemberJob(request.getParameter("memberJob"));
		vo.setMemberAuthor(request.getParameter("memberAuthor"));
		vo.setMemberId(request.getParameter("memberId"));
		

		System.out.println(request.getParameter("memberId"));
		System.out.println(request.getParameter("memberJob"));
		System.out.println(request.getParameter("memberTel"));
		System.out.println(request.getParameter("memberEmail"));
		System.out.println(request.getParameter("memberAuthor"));
		
		
		int n = memberDao.memberUpdate(vo);
		
		request.setAttribute("memberUpdateComplete", "회원정보 수정이 완료되었습니다.");
		if(n>0) {
			System.out.println("수정성공");
		}else {
			System.out.println("수정실패");
		}
		return "member/memberMyPageConfirm";
	}

}
