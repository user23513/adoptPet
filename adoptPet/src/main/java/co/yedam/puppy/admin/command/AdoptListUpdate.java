package co.yedam.puppy.admin.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.admin.service.AdminService;
import co.yedam.puppy.admin.service.AdminServiceImple;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.AdoptSubscriptionVO;

public class AdoptListUpdate implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 관리자 - 입양신청리스트 승인(수정)
		
		AdminService adminDao = new AdminServiceImple();
		AdoptSubscriptionVO vo = new AdoptSubscriptionVO();
		vo.setMemberId(request.getParameter("memberId"));
		vo.setPetAddNo(Integer.parseInt(request.getParameter("petAddNo")));
		vo.setAdoptSubscriptionOk(request.getParameter("adoptSubscriptionOk"));
		
		int n = adminDao.adoptListUpdate(vo);
		if(n>0) {
			System.out.println("수정성공");
		}else {
			System.out.println("수정실패");
		}
		return "admin/adoptList";
	}

}
