package co.yedam.puppy.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.board.service.BoardService;
import co.yedam.puppy.board.service.BoardServiceImpl;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.BoardVO;

public class NoticeSelect implements Command{

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//공지 상세보기
		BoardService noticeDao = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		vo.setBoardNo(Integer.valueOf(request.getParameter("no")));
		vo = noticeDao.boardSelectOne(vo);
		System.out.println(request.getParameter("no"));
		request.setAttribute("board", vo);
		return "notice/noticeSelect";
	}

}
