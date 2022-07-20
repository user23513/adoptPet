package co.yedam.puppy.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.board.service.BoardService;
import co.yedam.puppy.board.service.BoardServiceImpl;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.BoardVO;
import co.yedam.puppy.vo.FilesVO;

public class NoticeUpdate implements Command{
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//공지 수정
		BoardService dao = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		vo.setBoardNo(Integer.parseInt(request.getParameter("boardNo")));
		
		request.setAttribute("boardVO", vo);
		
		return "notice/noticeList";
	}

}
