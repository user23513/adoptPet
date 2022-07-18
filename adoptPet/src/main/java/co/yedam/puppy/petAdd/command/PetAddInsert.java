package co.yedam.puppy.petAdd.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.comm.DataSource;
import co.yedam.puppy.petAdd.service.PetAddService;
import co.yedam.puppy.petAdd.service.PetAddServiceImpl;
import co.yedam.puppy.vo.PetAddVO;

public class PetAddInsert implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//입양동물 등록 처리 후 뿌려주는 페이지로 이동
		PetAddService dao = new PetAddServiceImpl();
		PetAddVO vo = new PetAddVO();
		vo.setPetAddName(request.getParameter("petAddName"));
		vo.setPetAddAge(request.getParameter("petAddAge"));
		vo.setPetAddGender(request.getParameter("petAddGender"));
		vo.setPetAddWeight(request.getParameter("petAddWeight"));
		vo.setPetAddHealth(request.getParameter("petAddHealth"));
		vo.setPetAddAdoptState(request.getParameter("petAddAdoptState"));
		vo.setPetAddType(request.getParameter("petAddType"));
		
		int r = dao.petAddInsert(vo);
		
		if(r>0) {
			request.setAttribute("message", "입양등록처리가 되었습니다.");
		} else {
			request.setAttribute("message", "입양등록처리가 실패했습니다.");
		}
		
		return "petAddList.do";
	}

}
