package co.yedam.puppy.petList.service;

import java.util.List;

import co.yedam.puppy.vo.FilesVO;
import co.yedam.puppy.vo.PetListVO;


public interface PetListService {

	List<PetListVO> petListSelectList(int currentPage, int startRow, int pageSize); //입양동물소개게시판 전체리스트조회
	PetListVO petListSelectOne(PetListVO vo); //입양동물소개게시판 단건조회
	int petListInsert(PetListVO listVO, FilesVO fileVO); //입양동물소개게시판 등록(파일까지)
	int petListUpdate(PetListVO listVO, FilesVO fileVO); //입양동물소개게시판 수정
	int petListDelete(PetListVO listVO, FilesVO fileVO); //입양동물소개게시판 삭제
	List<PetListVO> petListSearchList(String key, String val); //입양동물소개게시판 검색조회
	List<PetListVO> petListSort(String petType); //입양동물소개게시판 동물유형으로 정렬해서 보여주기
	int petListCount(); //입양동물소개게시판에 있는 글 개수 확인
	List<PetListVO> petListFiles(List<PetListVO> list); //담아온 list_no를  뽑아서 파일을 찾아서 다시 vo객체에 담기
	
}
