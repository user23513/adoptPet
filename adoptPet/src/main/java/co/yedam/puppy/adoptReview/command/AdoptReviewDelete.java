package co.yedam.puppy.adoptReview.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.yedam.puppy.board.service.BoardService;
import co.yedam.puppy.board.service.BoardServiceImpl;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.BoardVO;

public class AdoptReviewDelete implements Command{

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 입양후기 삭제
		BoardService boardDao = new BoardServiceImpl();
		List<BoardVO> list = new ArrayList<BoardVO>();
		ObjectMapper mapper = new ObjectMapper();  //jackson 라이브러리 사용(json)
		BoardVO vo = new BoardVO();
		vo.setBoardNo(Integer.valueOf(request.getParameter("no")));
		int n = boardDao.noticeDelete(vo, null);
		String jsonList = "0";
		if(n !=0) {
			jsonList ="1";
		}
		return "ajax:"+jsonList;
	}

}
