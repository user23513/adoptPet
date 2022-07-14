package co.yedam.puppy.volunteerSubscription.service;

import java.util.List;

import co.yedam.puppy.adoptSubscription.vo.AdoptSubscriptionVO;
import co.yedam.puppy.petList.vo.PetListVO;

public interface VolunteerSubscriptionService {
	//경아
	//회원마이페이지
	List<PetListVO> myVolunteerList(int startRow, int pageSize); // 나의 봉사신청 리스트
	int myVolunteerUpdate(AdoptSubscriptionVO vo);// 나의 봉사신청 수정
	int myVolunteerDelete(AdoptSubscriptionVO vo); // 나의 봉사신청 삭제

}
