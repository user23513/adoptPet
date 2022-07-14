package co.yedam.puppy.volunteerSubscription.service;

import java.util.List;

import co.yedam.puppy.vo.AdoptSubscriptionVO;
import co.yedam.puppy.vo.PetListVO;


public interface VolunteerSubscriptionService {
	//경아(은지)
	//회원마이페이지
	List<PetListVO> myVolunteerList(int startRow, int pageSize); // 나의 봉사신청 리스트
	int myVolunteerInsert(AdoptSubscriptionVO vo); // 나의 봉사신청 추가
	int myVolunteerUpdate(AdoptSubscriptionVO vo);// 나의 봉사신청 수정
	int myVolunteerDelete(AdoptSubscriptionVO vo); // 나의 봉사신청 삭제

}
