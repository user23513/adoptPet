package co.yedam.puppy.petList.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.petAdd.service.PetAddService;
import co.yedam.puppy.petAdd.service.PetAddServiceImpl;
import co.yedam.puppy.petList.service.PetListService;
import co.yedam.puppy.petList.service.PetListServiceImpl;

public class PetList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//입양동물 소개 게시판 페이지로 이동
		PetListService dao = new PetListServiceImpl();
		dao.petListCount(); //게시판 DB에 있는 글 개수를 확인
		
		//한 페이지에 출력될 글 수
		int pageSize = 10;
		
		//현재 페이지 정보 설정
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		
		//첫행번호를 계산
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize+1;
		
		return "petList/petList";
	}

}
