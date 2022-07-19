package co.yedam.puppy.adoptSubscription.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.comm.Command;

public class AdoptSubscriptionForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//입양신청폼으로 이동(멤버아이디${id}랑 동물번호받아옴)
		String petAddNo = request.getParameter("petAddNo");
		request.setAttribute("petAddNo", petAddNo);
		
		return "ajax:";
	}

}
