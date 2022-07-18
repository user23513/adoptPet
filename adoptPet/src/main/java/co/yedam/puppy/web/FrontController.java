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
import co.yedam.puppy.calendar.service.Calendar;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.petAdd.command.PetAddForm;
import co.yedam.puppy.petAdd.command.PetAddList;
import co.yedam.puppy.petList.command.PetList;

import co.yedam.puppy.member.command.AjaxMemberIdCheck;

import co.yedam.puppy.petList.command.PetListForm;
import co.yedam.puppy.petList.command.PetListInsert;

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
		
		map.put("/petAddForm.do", new PetAddForm()); //입양동물 등록 폼 페이지로 이동
		map.put("/petAddList.do", new PetAddList()); //입양동물 등록 처리 후 뿌려주는 페이지(임의로 내가 만듬)로 이동
		map.put("/petList.do", new PetList()); //입양동물 소개 게시판 페이지로 이동

		map.put("/petListForm.do", new PetListForm()); //입양동물 소개게시판 게시글 등록페이지로 이동
		map.put("/petListInsert.do", new PetListInsert()); //입양동물 소개게시판 게시글 등록처리
	
		map.put("/noticeList.do", new NoticeList());//공지 리스트
		map.put("/noticeSelect.do", new NoticeSelect());//공지 리스트
		map.put("/noticeForm.do", new NoticeForm());//공지 입력폼 호출
		map.put("/noticeInsert.do", new NoticeInsert()); //공지 등록
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
			if (viewPage.startsWith("ajax:")) {
				response.setContentType("text/html; charset=UTF-8");
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
