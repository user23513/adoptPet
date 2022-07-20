package co.yedam.puppy.qnaBoard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.comm.Command;

public class QnaBoardForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 문의글 등록 폼 호출
		return "qna/qnaBoardInsertForm";
		
	}

}
