package co.yedam.puppy.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.member.service.MemberService;
import co.yedam.puppy.member.service.MemberServiceImpl;
import co.yedam.puppy.vo.MemberVO;

public class MemberMyPage implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 내정보가져오기
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		
		//로그인한 계정가져오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String author = (String) session.getAttribute("author");
		
		vo.setMemberId(id);
		vo.setMemberAuthor(author);
	
		vo = memberDao.memberSelectOne(vo);
		
		request.setAttribute("member", vo);
		
		return "member/memberMyPage";
	}

}
