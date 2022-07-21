package co.yedam.puppy.admin.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.admin.service.AdminService;
import co.yedam.puppy.admin.service.AdminServiceImple;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.member.service.MemberService;
import co.yedam.puppy.member.service.MemberServiceImpl;
import co.yedam.puppy.petAdd.command.PetAddList;
import co.yedam.puppy.petAdd.service.PetAddService;
import co.yedam.puppy.petAdd.service.PetAddServiceImpl;
import co.yedam.puppy.vo.AdoptSubscriptionVO;
import co.yedam.puppy.vo.MemberVO;
import co.yedam.puppy.vo.PetAddVO;
import co.yedam.puppy.vo.PetListVO;

public class AdoptOneView implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 입양신청 1건 상세보기
		AdminService dao = new AdminServiceImple();
		AdoptSubscriptionVO vo = new AdoptSubscriptionVO();

		vo.setPetAddNo(Integer.parseInt(request.getParameter("petAddNo")));
		vo.setMemberId(request.getParameter("memberId"));
		vo.setAdoptSubscriptionOk(request.getParameter("adoptSubscriptionOk"));
		vo.setAdoptSubscriptionReason(request.getParameter("adoptSubscriptionReason"));
		dao.adoptOneView(vo);
	
		MemberVO mvo = new MemberVO();
		mvo.setMemberId(request.getParameter("memberId"));
		MemberService mdao = new MemberServiceImpl();
		mdao.memberSelectOne(mvo);
		
		PetAddVO pvo = new PetAddVO();
		pvo.setPetAddNo(Integer.parseInt(request.getParameter("petAddNo")));
		PetAddService pdao = new PetAddServiceImpl();
		pdao.petAddSelectOne(pvo);
		

		
		//PetListService dao = new PetListServiceImpl();
//		PetListVO Plvo = new PetListVO();
//		String petAddNo = request.getParameter("petAddNo");
//		String petListNo = request.getParameter("petListNo");
//		System.out.println("동물리스트번호"+petAddNo);
//		Plvo.setPetAddNo(Integer.parseInt(petAddNo));
//		Plvo = dao.petListSelectOne(Plvo); //클릭한 동물 단건조회
//		request.setAttribute("Plvo", Plvo);
		
		
		
		request.setAttribute("vo", vo);
		request.setAttribute("mvo", mvo);
		request.setAttribute("pvo", pvo);
		return "admin/adoptOneView";
	}

}
