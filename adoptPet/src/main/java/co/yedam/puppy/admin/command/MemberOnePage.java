package co.yedam.puppy.admin.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.member.service.MemberService;
import co.yedam.puppy.member.service.MemberServiceImpl;
import co.yedam.puppy.vo.MemberVO;

public class MemberOnePage implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 내정보가져오기
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		
		
		vo.setMemberId(request.getParameter("memberId"));
		
		vo = memberDao.memberSelectOne(vo);
		
		request.setAttribute("member", vo);
		
		return "admin/memberOnePage";
	}

}
