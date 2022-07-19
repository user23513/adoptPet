package co.yedam.puppy.petAdd.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.petList.service.PetListService;
import co.yedam.puppy.petList.service.PetListServiceImpl;

public class PetListDelete implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//입양동물 소개게시판 삭제처리(게시물만 지우면 파일도 같이 지워짐)
		PetListService dao = new PetListServiceImpl();
		String petListNo = request.getParameter("petListNo");
		int r = dao.petListDelete(Integer.parseInt(petListNo));
		String json = "0";
		if(r>0) {json="1";}
		return "ajax:"+ json;
	}

}
