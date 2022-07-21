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


		int cnt = adminDao.adoptListCount();
		int pageSize = 10;

		// 현재 페이지 정보 설정
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		// 첫행번호를 계산
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		
		
		String key = request.getParameter("key");
		String val = request.getParameter("val");

		list = adminDao.AdoptSubscriptionSearchList(key, val);
		
		// =================페이징처리=============================
		int pageCount = 0;
		int pageBlock = 0;
		int startPage = 0;
		int endPage = 0;
		if (cnt != 0) {
			// 전체 페이지수 계산
			pageCount = cnt / pageSize + (cnt % pageSize == 0 ? 0 : 1);

			// 한 페이지에 보여줄 페이지 블럭
			pageBlock = 10;

			// 한 페이지에 보여줄 페이지 블럭 시작번호 계산
			startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;

			// 한 페이지에 보여줄 페이지 블럭 끝 번호 계산
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount) {
				endPage = pageCount;
			}

		}
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		request.setAttribute("adoptList", list);
		return "admin/adoptList";
	}

}
