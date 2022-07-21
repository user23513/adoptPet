package co.yedam.puppy.board.command;

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

public class AjaxNoticeSearch implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 공지 검색
		BoardService noticeDao = new BoardServiceImpl();
		List<BoardVO> noticeSerarchList = new ArrayList<BoardVO>();
		ObjectMapper mapper = new ObjectMapper();
		String key = request.getParameter("key");
		String val = request.getParameter("val");
		
		noticeSerarchList = noticeDao.noticeSerarchList(key, val);
		String jsonList = null;
		
		try {
			jsonList = mapper.writeValueAsString(noticeSerarchList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return "ajax:" + jsonList;
	}

}
