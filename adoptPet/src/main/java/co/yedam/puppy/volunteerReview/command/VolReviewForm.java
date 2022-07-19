package co.yedam.puppy.volunteerReview.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.comm.Command;

public class VolReviewForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 봉사활동 후기 등록 폼 페이지로 이동
		return "volReview/volReviewInsertForm";
	}

}
