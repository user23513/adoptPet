package co.yedam.puppy.files.service;

import java.util.List;

import co.yedam.puppy.vo.FilesVO;
import co.yedam.puppy.vo.PetListVO;

public interface FilesService {
	
	int filesInsert(FilesVO vo); //첨부파일 등록
	List<FilesVO> fileNoSelect(int petListNo); //선택한 게시글에 파일넘버 가지고오기
	int filesDelete(PetListVO vo); //파일삭제
	List<String> filesSelect(PetListVO vo); //파일조회
}
