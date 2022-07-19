package co.yedam.puppy.member.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.adoptSubscription.service.AdoptSubscriptionService;
import co.yedam.puppy.adoptSubscription.service.AdoptSubscriptionServiceImpl;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.petAdd.service.PetAddService;
import co.yedam.puppy.petAdd.service.PetAddServiceImpl;
import co.yedam.puppy.vo.AdoptSubscriptionVO;
import co.yedam.puppy.vo.PetAddVO;

public class MemberAdopt implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 나의 입양신청
		
		AdoptSubscriptionService adao = new AdoptSubscriptionServiceImpl();
		AdoptSubscriptionVO avo = new AdoptSubscriptionVO();
		//avo.setMemberId(request.getParameter("memberId"));
		avo.setMemberId("kim");
		List<AdoptSubscriptionVO>list =  adao.myAdoptSubList(avo);
		
		PetAddService pdao = new PetAddServiceImpl();
		PetAddVO pvo = new PetAddVO();
		//pvo.setPetAddNo(Integer.parseInt(request.getParameter("petAddNo")));
		pvo.setPetAddNo(1);
		pdao.petAddSelectOne(pvo);
		
		request.setAttribute("pvo", pvo);
		request.setAttribute("list", list);
		return "member/memberAdopt";
	}

}
