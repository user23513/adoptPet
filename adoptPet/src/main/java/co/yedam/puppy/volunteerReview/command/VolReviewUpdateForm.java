package co.yedam.puppy.volunteerReview.command;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.board.service.BoardService;
import co.yedam.puppy.board.service.BoardServiceImpl;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.BoardVO;

public class VolReviewUpdateForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 봉사후기 수정 처리
		BoardService dao = new BoardServiceImpl();
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
		
		int r = dao.volReviewUpdate(vo);
		if(r>0) {
			System.out.println("수정완료");
		}else {System.out.println("수정안됨");}
		
		
		return "volReviewList.do";
	}

}
