package co.yedam.puppy.adoptSubscription.service;

import co.yedam.puppy.adoptSubscription.vo.AdoptSubscriptionVO;

public interface AdoptSubscriptionService {

	int AdoptSubscriptionInsert(AdoptSubscriptionVO vo); //입양동물 신청 form내용 db에 등록
}
