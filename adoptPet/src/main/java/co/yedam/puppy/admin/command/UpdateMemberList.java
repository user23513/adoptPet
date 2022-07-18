package co.yedam.puppy.admin.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.admin.service.AdminService;
import co.yedam.puppy.admin.service.AdminServiceImple;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.MemberVO;

public class UpdateMemberList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		AdminService adminDao = new AdminServiceImple();
		MemberVO vo = new MemberVO();
		vo.setMemberId(request.getParameter("memberId"));
		vo.setMemberAuthor(request.getParameter("memberAuthor"));
		
		System.out.println(request.getParameter("memberId"));
		System.out.println(request.getParameter("memberAuthor"));
		
		adminDao.updateMemberList(vo);
	//	request.setAttribute("memberList", vo);
		
		//return "admin/memberList";
		return "main/main";
	}

}
