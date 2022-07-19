package co.yedam.puppy.qnaBoard.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.board.service.BoardService;
import co.yedam.puppy.board.service.BoardServiceImpl;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.BoardVO;

public class QnaBoardList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 문의게시판 리스트
		BoardService boardDao = new BoardServiceImpl();
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		list = boardDao.qnaBoardSelectList(1, 10, 0);  // 다시 확인하기
		
		request.setAttribute("list", list);
		return "qna/qnaBoardList";
	}

}
