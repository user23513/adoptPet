package co.yedam.puppy.adoptReview.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.board.service.BoardService;
import co.yedam.puppy.board.service.BoardServiceImpl;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.BoardVO;

public class AdoptReviewSelect implements Command{

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 입양후기 상세보기
		BoardService noticeDao = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		vo.setBoardNo(Integer.valueOf(request.getParameter("no")));
		vo = noticeDao.boardSelect(vo);
		return "adoptReview/adoptReviewSelect";
	}

}
