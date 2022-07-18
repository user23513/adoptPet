package co.yedam.puppy.petList.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.comm.Command;

public class PetListUpdateForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//입양동물 소개게시판 수정폼으로 이동
		//단건조회해서 값을 넘겨줘야함
		
		return "petList/petListForm";
	}

}
