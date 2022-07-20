package co.yedam.puppy.heart.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.heart.service.HeartService;
import co.yedam.puppy.heart.service.HeartServiceImpl;
import co.yedam.puppy.vo.HeartVO;

public class HeartCheck implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//게시글 좋아요버튼 눌렀을때 처리
		HeartService dao = new HeartServiceImpl();
		HeartVO vo = new HeartVO();
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		vo.setMemberId(id);
		vo.setPetListNo(Integer.parseInt(request.getParameter("petListNo")));
		
		int r = dao.heartCheck(vo); //좋아요 누른적이 있는지 확인 
		String heartColor = null;
		// 0이면 누른적이 없음
		ObjectMapper mapper = new ObjectMapper();
		if(r == 0) {
			int cnt = dao.heartInsert(vo); //인서트되면 하트색이 빨강
			
			if(cnt>0) {
				try {
					vo.setHeartColor("Red");
					int count = dao.heartCount(Integer.parseInt(request.getParameter("petListNo")));
					vo.setHeartCount(count);
					heartColor = mapper.writeValueAsString(vo);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				}
		}else {
			int cnt = dao.heartDelete(vo); //누른적이 있으면 다시 삭제되고 색상은 흰색
			if(cnt>0) {
				vo.setHeartColor("White");
				int count = dao.heartCount(Integer.parseInt(request.getParameter("petListNo")));
				vo.setHeartCount(count);
				try {
					heartColor = mapper.writeValueAsString(vo);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return "ajax:" + heartColor ;
	}

}
