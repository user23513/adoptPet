package co.yedam.puppy.petList.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ser.std.FileSerializer;
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
		int r=0;
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
				
				r = dao.petListUpdate(lvo); //게시판 DB에 수정처리
				
				//파일DB에 삭제하고 다시 인서트 해주기
				FilesService fdao = new FilesServiceImpl();
				fdao.filesDelete(lvo); //게시글에 원래있던 파일 삭제하고
				//다시 인서트해주기
				//동물소개게시판 첨부파일 DB에 등록
				for(int i=1; i<=3; i++) {
					String originalFileName = multi.getOriginalFileName("file"+i); //실제파일이름
					String saveFileName = multi.getFilesystemName("file"+i); //저장된 파일이름
					//첨부파일추가를 했을 때
					if(originalFileName != null) { 
							FilesVO fvo = new FilesVO();
							fvo.setBoardId(Integer.parseInt(multi.getParameter("boardId")));
							fvo.setFilesName(originalFileName);
							//파일경로를 추가한다.
							saveFileName = savePath + saveFileName; 
							fvo.setFilesPath(saveFileName);
							//파일 확장자명만 뽑아내기 위해
							int idx = originalFileName.lastIndexOf(".");
							String filesType = originalFileName.substring(idx+1);
							fvo.setFilesType(filesType); //파일 확장자명
							fvo.setPetListNo(Integer.parseInt(multi.getParameter("petListNo")));
							fdao.filesInsert(fvo);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		if(r>0) {request.setAttribute("r", r);}
		return "petList/petListUpdateForm";
	}
}
