package co.yedam.puppy.member.service;

import co.yedam.puppy.vo.MemberVO;

public interface MemberService {

	// 지혜
	int memberInsert(MemberVO vo); //멤버 삽입
	//경아
	MemberVO memberSelectOne(MemberVO vo);// 내정보단건조회 
	int memberUpdate(MemberVO vo); // 내정보수정
	int memberDelete(MemberVO vo); // 내정보삭제(탈퇴)
}
