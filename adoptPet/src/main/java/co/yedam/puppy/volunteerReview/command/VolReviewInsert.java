package co.yedam.puppy.volunteerReview.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.board.service.BoardService;
import co.yedam.puppy.board.service.BoardServiceImpl;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.BoardVO;

public class VolReviewInsert implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 봉사활동후기 등록 후 뿌려주는 페이지로 이동
		BoardService dao = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		
		vo.setBoardTitle(request.getParameter("boardTitle"));
		vo.setBoardWriter(request.getParameter("boardWriter"));
		vo.setBoardContent(request.getParameter("boardContect"));
		
		int r = dao.volReviewInsert(vo); // int 타입 0건입력 1건입력
		
		if(r > 0) {
			request.setAttribute("message", "봉사활동후기 등록 처리가 완료되었습니다.");
		} else {
			request.setAttribute("message", "봉사활동후기 등록 처리를 실패했습니다.");
		}
		
		return "volReviewList.do";
	}

}
