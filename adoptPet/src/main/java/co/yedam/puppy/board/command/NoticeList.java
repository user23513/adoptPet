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
		int cnt = noticeDao.boardCount();
		int pageSize = 10;
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		list = noticeDao.boardSelectList(currentPage, startRow, pageSize);

		request.setAttribute("list", list);
		
		//페이징 처리
		int pageCount = 0;
		int pageBlock = 0;
		int startPage = 0;
		int endPage = 0;
		if (cnt != 0) {
			// 전체 페이지수 계산
			pageCount = cnt / pageSize + (cnt % pageSize == 0 ? 0 : 1);

			// 한 페이지에 보여줄 페이지 블럭
			pageBlock = 10;

			// 한 페이지에 보여줄 페이지 블럭 시작번호 계산
			startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;

			// 한 페이지에 보여줄 페이지 블럭 끝 번호 계산
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount) {
				endPage = pageCount;
			}

		}
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		return "notice/noticeList";
	}

}
