package co.yedam.puppy.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.yedam.puppy.comm.Command;

public class MemberLogout implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 로그아웃
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		request.setAttribute("message", name + " 님 정상적으로 로그아웃 완!");
		session.invalidate(); 
		return "member/memberLogout";
	}

}
