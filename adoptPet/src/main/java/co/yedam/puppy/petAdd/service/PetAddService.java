package co.yedam.puppy.petAdd.service;

import java.util.List;

import co.yedam.puppy.vo.PetAddVO;


public interface PetAddService {
	
	List<PetAddVO> petAddSelectList(int currentPage ,int startRow, int pageSize); //입양동물 전체조회(페이징처리)
	int petAddCount(); //DB에 있는 입양동물List 글 갯수 확인
	PetAddVO petAddSelectOne(PetAddVO vo); //입양동물 단건조회
	int petAddInsert(PetAddVO vo); //입양동물 등록
	int petAddUpdate(PetAddVO vo); //입양동물 수정
	int petAddDelete(PetAddVO vo); //입양동물 삭제

}
