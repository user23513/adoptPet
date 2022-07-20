package co.yedam.puppy.adoptReview.command;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.yedam.puppy.board.service.BoardService;
import co.yedam.puppy.board.service.BoardServiceImpl;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.BoardVO;
import co.yedam.puppy.vo.FilesVO;

public class AdoptReviewInsert implements Command{

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 입양 후기 글쓰기
		BoardService noticeDao = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		FilesVO fvo = new FilesVO();
	
		String savePath = "C:\\Temp\\"; // 파일이저장될수있는 path경로찾기 (실제서버에서는 fileSave)
		int uploadSize = 1024 * 1024 * 1024; // 파일 최대사이즈 100MB
		
		int n = 0;
		
		try {
			MultipartRequest multi = 
					new MultipartRequest(request, savePath, uploadSize, "utf-8", new DefaultFileRenamePolicy());
			
			String writer = multi.getParameter("boardWriter");
			/*
			 * if(writer.equals("관리자")) { writer = "ADMIN"; }
			 */
			
			String originalFileName = multi.getOriginalFileName("file");
			String saveFileName = multi.getFilesystemName("file");
			vo.setBoardWriter(multi.getParameter("boardWriter"));
			vo.setBoardTitle(multi.getParameter("boardTitle"));
			vo.setBoardDate(Date.valueOf(multi.getParameter("boardDate")));
			vo.setBoardContent(multi.getParameter("boardContent"));
			if(originalFileName != null) {
				fvo.setFilesName(saveFileName);
				saveFileName = savePath + saveFileName;//파일경로를 추가한다.
				fvo.setFilesPath(saveFileName);
			}
			n = noticeDao.noticeInsert(vo);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String returnPage = null;
		if(n != 0) {
			returnPage = "apoptReviewList.do";
		}else {
			request.setAttribute("message", "게시글 등록이 실패했습니다.");
			returnPage = "adoptReview/adoptReviewError";
		}
		return returnPage;
	}
		
		
		

}
