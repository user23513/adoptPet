package co.yedam.puppy.volunteerReview.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.board.service.BoardService;
import co.yedam.puppy.board.service.BoardServiceImpl;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.BoardVO;

public class AjaxVolReviewDelete implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 봉사활동후기 삭제 페이지
		BoardService dao = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		String boardNo = request.getParameter("boardNo");
		vo.setBoardNo(Integer.parseInt("boardNo"));
		int r = dao.volReviewDelete(vo);
		String json = "0";
		if(r>0) {
			json = "1";
		}
		
		return "ajax:"+ json;
	}

}
