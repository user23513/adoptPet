package co.yedam.puppy.admin.service;

import java.util.List;

import co.yedam.puppy.vo.AdoptSubscriptionVO;
import co.yedam.puppy.vo.MemberVO;
import co.yedam.puppy.vo.PetListVO;
import co.yedam.puppy.vo.VolunteerSubscriptionVO;


public interface AdminService {
	
	//관리자마이페이지
	List<MemberVO> allMemberList(int currentPage, int startRow, int pageSize);
	int updateMemberList(MemberVO vo);//회원수정(권한)
	int memberListCount();//회원페이지수
	
	List<AdoptSubscriptionVO> allAdoptList(int currentPage, int startRow, int pageSize);//봉사신청리스트
	int adoptListUpdate(AdoptSubscriptionVO vo);//입양상태 수정
	List<AdoptSubscriptionVO> AdoptSubscriptionSearchList(String key, String val);//입양상태 검색
	int adoptListCount();//입양페이지수
	
	AdoptSubscriptionVO adoptOneView(AdoptSubscriptionVO vo);
	
	List<VolunteerSubscriptionVO> allVolunteerList(int currentPage, int startRow, int pageSize);//봉사신청리스트
	int updateVolunteerList(VolunteerSubscriptionVO vo); //봉사신청상태 수정
	int volunteerListCount();//봉사신청페이지수
	List<VolunteerSubscriptionVO> volunteerSubscriptionSearchList(String key, String val);//봉사신청상태 검색
	
	
	
}
