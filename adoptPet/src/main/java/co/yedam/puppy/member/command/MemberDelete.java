package co.yedam.puppy.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.member.service.MemberService;
import co.yedam.puppy.member.service.MemberServiceImpl;
import co.yedam.puppy.vo.MemberVO;

public class MemberDelete implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 회원탈퇴하기
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setMemberId("kim");
		vo.getMemberId();
		int n = memberDao.memberDelete(vo);
		if(n != 0) {
			//request.setAttribute("message", "탈퇴완료");
		}else {
			//request.setAttribute("message", "탈퇴실패.");	
		}
		
		return "main/main"; //삭제후이동할페이지
	}

}
