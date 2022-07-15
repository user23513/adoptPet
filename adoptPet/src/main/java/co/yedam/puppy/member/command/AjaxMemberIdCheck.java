package co.yedam.puppy.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.member.service.MemberService;
import co.yedam.puppy.member.service.MemberServiceImpl;

public class AjaxMemberIdCheck implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 아이디 중복체크(ajax)
		MemberService memberDao = new MemberServiceImpl();
		String id = request.getParameter("id");
		boolean b = memberDao.isMemberIdcheck(id);
		String result = "Used";
		if(!b) {
			result = "Un Used";
		}
		return "ajax:"+result;
	}

}
