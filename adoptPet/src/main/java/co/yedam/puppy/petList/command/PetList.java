package co.yedam.puppy.petList.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.heart.service.HeartService;
import co.yedam.puppy.heart.service.HeartServiceImpl;
import co.yedam.puppy.petAdd.service.PetAddService;
import co.yedam.puppy.petAdd.service.PetAddServiceImpl;
import co.yedam.puppy.petList.service.PetListService;
import co.yedam.puppy.petList.service.PetListServiceImpl;
import co.yedam.puppy.vo.HeartVO;
import co.yedam.puppy.vo.PetListVO;

public class PetList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//입양동물 소개 게시판 페이지로 이동
		
		PetListService petListDao = new PetListServiceImpl();
		
		//============가져오는 게시글 수==============
		int cnt = petListDao.petListCount(); //게시판 DB에 있는 글 개수를 확인
		
		//한 페이지에 출력될 글 수
		int pageSize = 10;
		
		//현재 페이지 정보 설정
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		
		request.setAttribute("cnt", cnt);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNum", pageNum);
		
		//첫행번호를 계산
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize+1;
		
		//pageSize만큼 list에 게시글 저장
		
		//다시 파일을 담은 list
		List<PetListVO> nList = petListDao.petListFiles(petListDao.petListSelectList(currentPage, startRow, pageSize));
		
		//=================페이징처리=============================
		int pageCount=0;
		int pageBlock=0;
		int startPage=0;
		int endPage=0;
		if(cnt != 0) {
			//전체 페이지수 계산
			pageCount = cnt / pageSize + (cnt % pageSize == 0 ? 0 : 1);
			
			//한 페이지에 보여줄 페이지 블럭
			pageBlock = 10;
			
			//한 페이지에 보여줄 페이지 블럭 시작번호 계산
			startPage = ((currentPage-1) / pageBlock) * pageBlock+1;
			
			//한 페이지에 보여줄 페이지 블럭 끝 번호 계산
			endPage = startPage + pageBlock-1;
			if(endPage > pageCount) {
				endPage = pageCount;
			}
			
		}
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		HeartService heartDao = new HeartServiceImpl();
		HeartVO heartVo = new HeartVO();
		//===============하트수================
		for(PetListVO vo : nList) {
			int petListNo = vo.getPetListNo();
			int heartNum = heartDao.heartCount(petListNo);
			vo.setHeartNum(heartNum);
			
			heartVo.setMemberId("lee");
			heartVo.setPetListNo(petListNo);
			int check = heartDao.heartCheck(heartVo); //하트클릭여부 체크(1이면 체크되어있는거 0이면 체크안되어있는거)
			vo.setHeartCheck(check);
		}
		request.setAttribute("petList", nList);
		
		return "petList/petList";
	}

}
