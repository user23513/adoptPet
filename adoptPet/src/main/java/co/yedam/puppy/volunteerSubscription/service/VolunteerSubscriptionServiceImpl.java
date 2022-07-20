package co.yedam.puppy.volunteerSubscription.service;

import java.util.List;

import co.yedam.puppy.vo.AdoptSubscriptionVO;
import co.yedam.puppy.vo.PetListVO;

public class VolunteerSubscriptionServiceImpl implements VolunteerSubscriptionService {

	@Override
	public List<PetListVO> myVolunteerList(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int myVolunteerInsert(AdoptSubscriptionVO vo) {
		// 봉사활동 등록폼
		int n = 0;
		String sql = "INSERT INTO VOLUNTEER_SUBSCRIPTION VALUES (MEMBER_ID,CALENDAR_NO, UNTEER_SUBSCRIPTION_OK)";
		return n;
	}

	@Override
	public int myVolunteerUpdate(AdoptSubscriptionVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int myVolunteerDelete(AdoptSubscriptionVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
