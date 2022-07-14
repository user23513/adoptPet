package co.yedam.puppy.petAdd.service;

import java.util.List;

import co.yedam.puppy.vo.PetAddVO;

public class PetAddServiceImpl implements PetAddService {

	@Override
	public List<PetAddVO> petAddSelectList(int startRow, int pageSize) {
		//입양동물 전체조회(페이징처리)
		return null;
	}

	@Override
	public int petAddInsert(PetAddVO vo) {
		//입양동물 등록
		
		return 0;
	}

	@Override
	public int petAddUpdate(PetAddVO vo) {
		//입양동물 수정
		return 0;
	}

	@Override
	public int petAddDelete(PetAddVO vo) {
		//입양동물 삭제
		return 0;
	}

}
