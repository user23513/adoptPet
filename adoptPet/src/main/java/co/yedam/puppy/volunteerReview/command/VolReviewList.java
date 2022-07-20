package co.yedam.puppy.volunteerReview.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.yedam.puppy.board.service.BoardService;
import co.yedam.puppy.board.service.BoardServiceImpl;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.BoardVO;

public class VolReviewList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 봉사활동 후기 리스트 뿌려주는 페이지

		BoardService dao = new BoardServiceImpl();
		
		// ===========가져오는 게시글 수=============
		int cnt = dao.volReviewCount();

		// 한 페이지에 출력될 글 수
		int pageSize = 10;

		// 현재 페이지 정보 설정
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		
		request.setAttribute("cnt", cnt); // 전체게시글수
		request.setAttribute("pageSize", pageSize); // 페이지당보여지는 게시물수
		request.setAttribute("pageNum", pageNum); // 현재페이지
		
		HttpSession session = request.getSession();
	    String id = (String) session.getAttribute("id");
	    request.setAttribute("id", id);
	    
		// 첫행번호를 계산
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;

		// pageSize만큼 list에 게시글 저장
		List<BoardVO> list = dao.volReviewSelectList(currentPage, startRow, pageSize);
		request.setAttribute("volReviewList", list);
		System.out.println(list);
		// ==============페이징 처리====================
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
		
		
		return "volReview/volReviewList";
	}

}
