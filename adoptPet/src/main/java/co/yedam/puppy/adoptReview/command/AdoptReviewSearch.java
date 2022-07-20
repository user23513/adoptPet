package co.yedam.puppy.adoptReview.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.yedam.puppy.board.service.BoardService;
import co.yedam.puppy.board.service.BoardServiceImpl;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.BoardVO;

public class AdoptReviewSearch implements Command{

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 입양후기 검색
		
		BoardService noticeDao = new BoardServiceImpl();
		List<BoardVO> list = new ArrayList<BoardVO>();
		ObjectMapper mapper = new ObjectMapper();
		String key = request.getParameter("key");
		String val = request.getParameter("val");
		
		list = noticeDao.noticeSerarchList(key, val);
		String jsonList = null;
		
		try {
			jsonList = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return "ajax:" + jsonList;
	}

}
