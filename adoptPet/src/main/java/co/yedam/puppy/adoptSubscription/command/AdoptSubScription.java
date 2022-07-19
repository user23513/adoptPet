package co.yedam.puppy.adoptSubscription.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.yedam.puppy.adoptSubscription.service.AdoptSubscriptionService;
import co.yedam.puppy.adoptSubscription.service.AdoptSubscriptionServiceImpl;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.AdoptSubscriptionVO;

public class AdoptSubScription implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//입양신청눌렀을때 처리해주는
		String petAddNo = request.getParameter("petAddNo");
		AdoptSubscriptionService dao = new AdoptSubscriptionServiceImpl();
		
		AdoptSubscriptionVO vo = new AdoptSubscriptionVO();
		vo.setMemberId("lee"); //${id}
		vo.setPetAddNo(Integer.parseInt(petAddNo));
		vo.setAdoptSubscriptionOk(request.getParameter("adoptSubscriptionOk"));
		vo.setAdoptSubscriptionReason(request.getParameter("adoptSubscriptionReason"));
		
		int r = dao.AdoptSubInsert(vo);
		String json = "0";
		
		if(r>0) {
			json="1";
		}
		return "ajax:"+json;
	}

}
