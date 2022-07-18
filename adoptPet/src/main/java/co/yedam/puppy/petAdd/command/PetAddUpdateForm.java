package co.yedam.puppy.petAdd.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.petAdd.service.PetAddService;
import co.yedam.puppy.petAdd.service.PetAddServiceImpl;
import co.yedam.puppy.vo.PetAddVO;

public class PetAddUpdateForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//입양동물등록 수정 처리
		PetAddService dao = new PetAddServiceImpl();
		PetAddVO vo = new PetAddVO();
		vo.setPetAddName(request.getParameter("petAddName"));
		vo.setPetAddAge(request.getParameter("petAddAge"));
		vo.setPetAddGender(request.getParameter("petAddGender"));
		vo.setPetAddWeight(request.getParameter("petAddWeight"));
		vo.setPetAddHealth(request.getParameter("petAddHealth"));
		vo.setPetAddAdoptState(request.getParameter("petAddAdoptState"));
		vo.setPetAddType(request.getParameter("petAddType"));
		vo.setPetAddNo(Integer.parseInt(request.getParameter("petAddNo")));
		
		int r = dao.petAddUpdate(vo);
		if(r>0) {
			System.out.println("수정완료");
		}else {System.out.println("수정안됨");}
		
		return "petAddList.do";
	}

}
