package co.yedam.puppy.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.comm.Command;

public class NoticeForm implements Command{

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 공지 등록 
		return "notice/noticeInsertForm";
	}

}
