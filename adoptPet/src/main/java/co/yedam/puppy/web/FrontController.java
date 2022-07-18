package co.yedam.puppy.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import co.yedam.puppy.MainCommand;
import co.yedam.puppy.admin.command.AdoptList;
import co.yedam.puppy.admin.command.AdoptStateSearch;
import co.yedam.puppy.admin.command.MemberList;
import co.yedam.puppy.admin.command.UpdateMemberList;
import co.yedam.puppy.board.command.AjaxNoticeSearch;
import co.yedam.puppy.board.command.NoticeForm;
import co.yedam.puppy.board.command.NoticeInsert;
import co.yedam.puppy.board.command.NoticeList;
import co.yedam.puppy.board.command.NoticeSelect;
import co.yedam.puppy.board.command.NoticeUpdate;
import co.yedam.puppy.calendar.command.Calendar;
import co.yedam.puppy.calendar.service.CalendarDelete;
import co.yedam.puppy.calendar.service.CalendarInsert;
import co.yedam.puppy.calendar.service.CalendarList;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.heart.command.HeartCheck;
import co.yedam.puppy.petAdd.command.PetAddForm;
import co.yedam.puppy.petAdd.command.PetAddInsert;
import co.yedam.puppy.petAdd.command.PetAddList;
import co.yedam.puppy.petAdd.command.PetAddUpdate;
import co.yedam.puppy.petAdd.command.PetAddUpdateForm;
import co.yedam.puppy.petList.command.PetList;

import co.yedam.puppy.member.command.AjaxMemberIdCheck;

import co.yedam.puppy.petList.command.PetListForm;
import co.yedam.puppy.petList.command.PetListInsert;
import co.yedam.puppy.petList.command.PetListUpdate;
import co.yedam.puppy.petList.command.PetListUpdateForm;
import co.yedam.puppy.petList.command.PetListView;
import co.yedam.puppy.member.command.MemberDelete;
import co.yedam.puppy.member.command.MemberJoin;
import co.yedam.puppy.member.command.MemberJoinForm;
import co.yedam.puppy.member.command.MemberLogin;
import co.yedam.puppy.member.command.MemberLoginForm;
import co.yedam.puppy.member.command.MemberLogout;
import co.yedam.puppy.member.command.MemberUpdateForm;

import co.yedam.puppy.member.command.MemberMyPage;
import co.yedam.puppy.member.command.MemberUpdate;

@Controller

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Command> map = new HashMap<>();

	public FrontController() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		// 요청과 수행할 command연결
		map.put("/main.do", new MainCommand());//처음접근하는곳
		
		map.put("/memberLoginForm.do", new MemberLoginForm()); // 로그인 폼 호출
		map.put("/memberLogin.do", new MemberLogin()); // 로그인
		map.put("/memberLogout.do", new MemberLogout()); // 로그아웃
		map.put("/memberJoinForm.do", new MemberJoinForm()); // 회원가입 화면
		map.put("/memberJoin.do", new MemberJoin()); // 회원가입 처리
		map.put("/ajaxMemberIdCheck.do", new AjaxMemberIdCheck()); // 아이디 중복체크
		

		map.put("/memberMyPage.do", new MemberMyPage()); //로그인후 마이페이지
		map.put("/memberDelete.do", new MemberDelete()); //회원자진탈퇴
		map.put("/memberUpdateForm.do", new MemberUpdateForm());  //회원정보수정화면
		map.put("/memberUpdate.do", new MemberUpdate()); //회원정보수정
				 

		map.put("/calendar.do", new Calendar()); // 캘린더 페이지
		map.put("/calendarList.do", new CalendarList()); // 캘린더 리스트 페이지
		map.put("/calendarInsert.do", new CalendarInsert()); // 캘린더 등록 페이지
		map.put("/calendarDelete.do", new CalendarDelete()); // 캘린더 삭제 페이지
		
		
		map.put("/petAddForm.do", new PetAddForm()); //입양동물 등록 폼 페이지로 이동
		map.put("/petAddInsert.do", new PetAddInsert()); //입양동물 등록 처리
		map.put("/petAddList.do", new PetAddList()); //입양동물등록 리스트 뿌려주는 페이지
		map.put("/petAddUpdate.do", new PetAddUpdate()); //입양동물 수정 form으로 이동
		map.put("/petAddUpdateForm.do", new PetAddUpdateForm()); //입양동물등록 수정 처리
		
		map.put("/petList.do", new PetList()); //입양동물 소개 게시판 페이지로 이동
		map.put("/petListView.do", new PetListView()); //리스트에 제목클릭했을때 게시물 보여주는 페이지
		map.put("/petListForm.do", new PetListForm()); //입양동물 소개게시판 게시글 등록페이지로 이동
		map.put("/petListInsert.do", new PetListInsert()); //입양동물 소개게시판 게시글 등록처리
		map.put("/petListUpdateForm.do", new PetListUpdateForm()); //입양동물 소개게시판 수정폼으로 이동
		map.put("/petListUpdate.do", new PetListUpdate()); //입양동물 소개게시판 수정처리
		map.put("/heartCheck.do", new HeartCheck()); //게시글 좋아요버튼 눌렀을때 처리
		
		map.put("/noticeList.do", new NoticeList());//공지 리스트
		map.put("/noticeSelect.do", new NoticeSelect());//공지 상세보기
		map.put("/noticeForm.do", new NoticeForm());//공지 입력폼 호출
		map.put("/noticeInsert.do", new NoticeInsert()); //공지 등록
		map.put("/noticeUpdateForm.do", new NoticeUpdate());//공지 수정 폼
		map.put("/ajaxNoticeInsert.do", new AjaxNoticeSearch());//공지 검색
		
		map.put("/memberList.do", new MemberList()); //모든회원리스트
		map.put("/adoptList.do", new AdoptList());//모든입양신청리스트
		map.put("/updateMemberList.do", new UpdateMemberList()); //멤버권한수정
		map.put("/adoptStateSearch.do", new AdoptStateSearch());
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청분석하고 실행하고 결과돌려주는 곳
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());
		
		// 1.요청수행
		Command command = map.get(page);
		String viewPage  = command.exec(request, response);
		
		// 2.결과페이지
		if (!viewPage.endsWith(".do")) {
			response.setContentType("text/html; charset=UTF-8");
			if (viewPage.startsWith("ajax:")) {
				response.getWriter().append(viewPage.substring(5));
				return;
			}

			viewPage = viewPage + ".tiles";
			//viewPage = "/WEB-INF/views/"+viewPage + ".jsp";

			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(viewPage);
		}
		
	}

}
