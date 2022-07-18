package co.yedam.puppy.board.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.board.service.BoardService;
import co.yedam.puppy.board.service.BoardServiceImpl;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.BoardVO;

public class NoticeList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 공지 게시판 리스트
		BoardService noticeDao = new BoardServiceImpl();
		List<BoardVO> list = new ArrayList<BoardVO>();
		list = noticeDao.boardSelectList(1, 10,0);//페이징인가 ???
		request.setAttribute("list", list);
		return "notice/noticeList";
	}

}
