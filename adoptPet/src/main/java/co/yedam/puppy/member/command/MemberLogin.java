package co.yedam.puppy.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.member.service.MemberService;
import co.yedam.puppy.member.service.MemberServiceImpl;
import co.yedam.puppy.vo.MemberVO;

public class MemberLogin implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 로그인 처리
		HttpSession session = request.getSession();
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setMemberId(request.getParameter("memberId"));
		vo.setMemberPassword(request.getParameter("memberPassword"));
		
		vo = memberDao.memberLogin(vo);
		if(vo.getMemberName() != null) {
			session.setAttribute("id", vo.getMemberId());
			session.setAttribute("author", vo.getMemberAuthor());
			session.setAttribute("name", vo.getMemberName());
//			request.setAttribute("message", vo.getMemberName() + " 님 환영합니다.");
			return "main/main";
		}else {
			request.setAttribute("message", "아이디 또는 패스워드가 일치하지 않습니다.");
			return "member/memberLoginForm";
		}
	}

}
