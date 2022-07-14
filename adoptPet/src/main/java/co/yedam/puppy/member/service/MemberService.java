package co.yedam.puppy.member.service;

import co.yedam.puppy.member.vo.MemberVO;

public interface MemberService {
	//경아
	MemberVO memberSelectOne(MemberVO vo);// 내정보조회 
	int memberUpdate(MemberVO vo); // 내정보수정
	int memberDelete(MemberVO vo); // 내정보삭제(탈퇴)

}
