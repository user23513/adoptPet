package co.yedam.puppy.admin.service;

import java.util.List;

import co.yedam.puppy.vo.AdoptSubscriptionVO;
import co.yedam.puppy.vo.MemberVO;
import co.yedam.puppy.vo.VolunteerSubscriptionVO;


public interface AdminService {
	
	//관리자마이페이지
	List<MemberVO> allMemberList(int currentPage, int startRow, int pageSize);
	int updateMemberList(MemberVO vo);//회원수정(권한)
	int deleteMemberList(MemberVO vo);//회원탈퇴 
	int memberListCount();
	
	List<AdoptSubscriptionVO> allAdoptList(int currentPage, int startRow, int pageSize);
	int updateAdoptList(AdoptSubscriptionVO vo);//입양상태 수정
	
	List<VolunteerSubscriptionVO> allVolunteerList(int currentPage, int startRow, int pageSize);
	int updateVolunteerList(VolunteerSubscriptionVO vo); //봉사신청상태 수정
	
	//모든회원후원정보리스트 메소드있어ㅇㅑ됨
	
	
}
