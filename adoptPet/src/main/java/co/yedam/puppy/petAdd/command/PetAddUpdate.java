package co.yedam.puppy.petAdd.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.petAdd.service.PetAddService;
import co.yedam.puppy.petAdd.service.PetAddServiceImpl;
import co.yedam.puppy.vo.PetAddVO;
import co.yedam.puppy.vo.PetAddVO;

public class PetAddUpdate implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//입양동물 수정 form으로 이동
		//선택한 입양동물 정보 가져오기
		PetAddService dao = new PetAddServiceImpl();
		PetAddVO vo = new PetAddVO();
		vo.setPetAddNo(Integer.parseInt(request.getParameter("petAddNo")));
		vo = dao.petAddSelectOne(vo);
		
		request.setAttribute("petAddVO", vo);
		
		return "petAdd/petAddUpdateForm";
	}

}
