package co.yedam.puppy.petAdd.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.comm.Command;

public class PetAddForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//입양동물 등록 폼으로 이동
		return "petAdd/petAddForm";
	}

}
