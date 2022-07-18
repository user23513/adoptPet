package co.yedam.puppy.petList.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.files.service.FilesService;
import co.yedam.puppy.files.service.FilesServiceImpl;
import co.yedam.puppy.petList.service.PetListService;
import co.yedam.puppy.petList.service.PetListServiceImpl;
import co.yedam.puppy.vo.PetListVO;

public class PetListView implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//리스트에 제목클릭했을때 게시물 보여주는 페이지
		PetListService dao = new PetListServiceImpl();
		PetListVO vo = new PetListVO();
		
		String petListNo = request.getParameter("petListNo");
		vo.setPetListNo(Integer.parseInt(petListNo));
		
		vo = dao.petListSelectOne(vo); //클릭한 제목 단건조회
		
		//파일가져와야함
		FilesService fdao = new FilesServiceImpl();
		List<String> filePathList = fdao.filesSelect(vo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("filePathList", filePathList);
		
		return "petList/petListView";
	}

}
