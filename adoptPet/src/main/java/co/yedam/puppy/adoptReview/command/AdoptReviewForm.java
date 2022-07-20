package co.yedam.puppy.adoptReview.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.comm.Command;

public class AdoptReviewForm implements Command{

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 입양후기 등록
		return "adopt/adoptReviewForm";
	}

}
