package co.yedam.puppy.adoptSubscription.service;

import java.util.List;

import co.yedam.puppy.adoptSubscription.vo.AdoptSubscriptionVO;
import co.yedam.puppy.petList.vo.PetListVO;

public interface AdoptSubscriptionService {
	//경아
	//회원마이페이지
	List<PetListVO> myAdoptList(int startRow, int pageSize); // 나의 입양신청 리스트
	int myAdoptUpdate(AdoptSubscriptionVO vo);// 나의 입양신청 수정
	int myAdoptDelete(AdoptSubscriptionVO vo); // 나의 입양신청 삭제

}
