package co.yedam.puppy.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.member.service.MemberService;
import co.yedam.puppy.member.service.MemberServiceImpl;
import co.yedam.puppy.vo.MemberVO;

public class MemberUpdateForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 회원정보수정
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setMemberId("kim");
		
		vo = memberDao.memberSelectOne(vo);
		request.setAttribute("member", vo);

		//int n = memberDao.memberUpdate(vo);
		
		return "member/memberUpdateForm";
	}

}
