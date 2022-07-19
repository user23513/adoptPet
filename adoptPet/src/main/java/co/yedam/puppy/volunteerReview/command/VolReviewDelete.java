package co.yedam.puppy.volunteerReview.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.board.service.BoardService;
import co.yedam.puppy.board.service.BoardServiceImpl;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.BoardVO;

public class VolReviewDelete implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 봉사활동후기 삭제 페이지
		BoardService dao = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		int r = dao.volReviewDelete(vo);
		
		vo.setBoardNo(Integer.parseInt(request.getParameter("boardNo")));
		
		
		if (r > 0) {
			System.out.println("DB에 1건이 삭제되었습니다.");
		} else {
			System.out.println("삭제 실패~!");
		}
		
		
		return "volReviewList.do";
	}

}
