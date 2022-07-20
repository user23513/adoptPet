package co.yedam.puppy.qnaBoard.command;

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

public class QnaBoardInsert implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 문의게시판 글 쓰기
		BoardService Dao = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		
		String no = request.getParameter("boardNo");
		vo.setBoardNo(Integer.parseInt(no));
		String id = request.getParameter("boardId");
		vo.setBoardId(Integer.parseInt(id));
		vo.setBoardTitle(request.getParameter("boardTitle"));
		vo.setBoardWriter(request.getParameter("boardWriter"));
		vo.setBoardContent(request.getParameter("boardContent"));
		String date = request.getParameter("boardDate");
		vo.setBoardDate(Date.valueOf(date));
		String hit = request.getParameter("boardHit");
		vo.setBoardHit(Integer.parseInt(hit));
		
		int r = Dao.volReviewInsert(vo); // int 타입 0건입력 1건입력
		
		if(r > 0) {
			System.out.println("DB에 1건 입력되었습니다.");
		} else {
			System.out.println("입력 실패!");
		}
		return "qna/qnaBoardList"; 
	}
		

}