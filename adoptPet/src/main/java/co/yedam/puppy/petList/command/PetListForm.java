package co.yedam.puppy.petList.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.comm.Command;

public class PetListForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//입양동물 소개게시판 게시글 등록페이지로 이동
		return "petList/petListForm";
	}

}
