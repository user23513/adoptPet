
package co.yedam.puppy.adoptSubscription.service;

import java.util.List;

import co.yedam.puppy.vo.AdoptSubscriptionVO;
import co.yedam.puppy.vo.PetListVO;


public interface AdoptSubscriptionService {
	//경아
	//회원마이페이지
	List<AdoptSubscriptionVO> myAdoptSubList(AdoptSubscriptionVO vo); // 나의 입양신청 리스트
    int AdoptSubInsert(AdoptSubscriptionVO vo); //입양동물 신청 form내용 db에 등록
	int myAdoptSubUpdate(AdoptSubscriptionVO vo);// 나의 입양신청 수정
	int myAdoptSubDelete(AdoptSubscriptionVO vo); // 나의 입양신청 삭제
	
	boolean isAdoptSubCheck(String memberId, int petAddNo); //같은동물 입양신청했는지 확인하는(true면 신청한적이 있고 false면 신청한적이 없음=>false면 신청버튼보이게)
	
}
