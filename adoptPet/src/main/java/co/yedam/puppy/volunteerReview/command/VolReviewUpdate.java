package co.yedam.puppy.volunteerReview.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.board.service.BoardService;
import co.yedam.puppy.board.service.BoardServiceImpl;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.BoardVO;

public class VolReviewUpdate implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 봉사활동후기 수정
		// 선택한 봉사활동후기 정보 가져오기
		BoardService dao = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		
		String no = request.getParameter("boardNo");
		vo.setBoardNo(Integer.parseInt(no));
		vo = dao.volReviewSelectOne(vo);
		
		request.setAttribute("BoardVO", vo);
		
		
		return "volReview/volReviewUpdateForm";
	}

}
