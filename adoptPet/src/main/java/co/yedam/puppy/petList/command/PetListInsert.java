package co.yedam.puppy.petList.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.petList.service.PetListService;
import co.yedam.puppy.petList.service.PetListServiceImpl;
import co.yedam.puppy.vo.FilesVO;
import co.yedam.puppy.vo.PetListVO;

public class PetListInsert implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//입양동물 소개게시판 게시글 등록처리
		PetListService dao = new PetListServiceImpl();
		PetListVO lvo = new PetListVO();
		FilesVO fvo = new FilesVO();
		
		String savePath = "C:\\Temp\\"; // 파일 저장위치(개발할때만 여기서쓴다.) /실제서버에서는 fileSave로 해줘야한다.
		int upLoadSize = 1024*1024*1024; // 최대 파일 사이즈 100MB까지 (http로 최대는 200MB)
		
		try {
			//이객체가 생성되면서 파일업로드는 끝남
			MultipartRequest multi = new MultipartRequest(request, savePath, upLoadSize, "UTF-8", new DefaultFileRenamePolicy());
			String originalFileName = multi.getOriginalFileName("file");
			String saveFileName = multi.getFilesystemName("file");
			System.out.println("saveFileName: "+saveFileName);
			
			String writer = request.getParameter("petListWriter");
			if(writer.equals("관리자")) {writer = "ADMIN";}
			
			lvo.setPetListType(request.getParameter("petListType"));
			lvo.setPetListState(request.getParameter("petListState"));
			lvo.setPetListWriter(writer);
			lvo.setPetListContent(request.getParameter("petListContent"));
			
			if(originalFileName != null) {
				fvo.setFilesName(originalFileName);
				saveFileName = savePath + saveFileName; //파일경로를 추가한다.
				fvo.setFilesPath(saveFileName);
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dao.petListInsert(lvo,fvo);
		
		return null;
	}

}
