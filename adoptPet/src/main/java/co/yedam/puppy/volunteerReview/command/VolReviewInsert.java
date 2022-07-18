package co.yedam.puppy.volunteerReview.command;

import java.sql.Date;

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
		
		String no = request.getParameter("board_no");
		vo.setBoardNo(Integer.parseInt(no));
		String id = request.getParameter("board_id");
		vo.setBoardId(Integer.parseInt(id));
		vo.setBoardTitle(request.getParameter("board_title"));
		vo.setBoardWriter(request.getParameter("board_writer"));
		vo.setBoardContent(request.getParameter("board_content"));
		String date = request.getParameter("board_date");
		vo.setBoardDate(Date.valueOf(date));
		String hit = request.getParameter("board_hit");
		vo.setBoardHit(Integer.parseInt(hit));
		
		int r = dao.volReviewInsert(vo); // int 타입 0건입력 1건입력
		
		if(r > 0) {
			System.out.println("DB에 1건 입력되었습니다.");
		} else {
			System.out.println("입력 실패!!ㅜㅜ ");
		}
		
		return "volReviewList.do";
	}

}
