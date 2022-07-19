package co.yedam.puppy.admin.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.yedam.puppy.admin.service.AdminService;
import co.yedam.puppy.admin.service.AdminServiceImple;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.VolunteerSubscriptionVO;

public class VolunteerStateSearch implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 봉사신청상태 검색 
		AdminService adminDao = new AdminServiceImple();
		List<VolunteerSubscriptionVO> list = new ArrayList<VolunteerSubscriptionVO>();
		ObjectMapper mapper = new ObjectMapper();
		String key = request.getParameter("key");
		String val = request.getParameter("val");
		System.out.println(key);
		System.out.println(val);
		list = adminDao.volunteerSubscriptionSearchList(key, val);
		String jsonList = null;
		try {
			jsonList = mapper.writeValueAsString(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "ajax:"+jsonList;
		
		}

}
