package co.yedam.puppy.petList.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.files.service.FilesService;
import co.yedam.puppy.files.service.FilesServiceImpl;
import co.yedam.puppy.petList.service.PetListService;
import co.yedam.puppy.petList.service.PetListServiceImpl;
import co.yedam.puppy.vo.FilesVO;
import co.yedam.puppy.vo.PetListVO;

public class PetListUpdate implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		String savePath = "C:\\Temp\\"; // 파일 저장위치(개발할때만 여기서쓴다.) /실제서버에서는 fileSave로 해줘야한다.
		int upLoadSize = 1024*1024*1024; // 최대 파일 사이즈 100MB까지 (http로 최대는 200MB)
		MultipartRequest multi;
			try {
				multi = new MultipartRequest(request, savePath, upLoadSize, "UTF-8", new DefaultFileRenamePolicy());
				//입양동물 소개게시판 수정처리
				PetListService dao = new PetListServiceImpl();
				PetListVO lvo = new PetListVO();
				lvo.setPetListNo(Integer.parseInt(multi.getParameter("petListNo")));
				lvo.setPetListType(multi.getParameter("petListType"));
				lvo.setPetListState(multi.getParameter("petListState"));
				lvo.setBoardId(Integer.parseInt(multi.getParameter("boardId")));
				
				//작성자 이름이 관리자일 때 ADMIN으로 바꿔서 입력함.
				String writer = multi.getParameter("petListWriter");
				if(writer.equals("관리자")) {writer = "ADMIN";}
				lvo.setPetListWriter(writer);
				
				lvo.setPetListTitle(multi.getParameter("petListTitle"));
				lvo.setPetListContent(multi.getParameter("petListContent"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		return "petList.do";
	}
}
