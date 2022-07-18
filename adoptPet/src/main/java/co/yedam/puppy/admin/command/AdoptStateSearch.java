package co.yedam.puppy.admin.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.yedam.puppy.admin.service.AdminService;
import co.yedam.puppy.admin.service.AdminServiceImple;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.AdoptSubscriptionVO;

public class AdoptStateSearch implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 상태 검색
		AdminService adminDao = new AdminServiceImple();
		List<AdoptSubscriptionVO> list = new ArrayList<AdoptSubscriptionVO>();
		ObjectMapper mapper = new ObjectMapper();
		String key = request.getParameter("key");
		String val = request.getParameter("val");
		System.out.println(key);
		System.out.println(val);
		list = adminDao.AdoptSubscriptionSearchList(key, val);
		String jsonList = null;
		try {
			jsonList = mapper.writeValueAsString(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "ajax:"+jsonList;
	}

}
