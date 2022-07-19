package co.yedam.puppy.petAdd.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.petAdd.service.PetAddService;
import co.yedam.puppy.petAdd.service.PetAddServiceImpl;
import co.yedam.puppy.vo.PetAddVO;

public class PetAddDelete implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//입양동물 삭제 처리(petAddNo받아서)
		PetAddService dao = new PetAddServiceImpl();
		PetAddVO vo = new PetAddVO();
		String petAddNo = request.getParameter("petAddNo");
		vo.setPetAddNo(Integer.parseInt(petAddNo));
		int r = dao.petAddDelete(vo);
		String json = "0";
		if(r>0) {
			json = "1";
		}
		
		return "ajax:"+ json;
	}

}
