package co.yedam.java.puppy.NoticeService;

import java.util.List;

import co.yedam.puppy.board.vo.BoardVO;

public interface NoticeService {
	//공지게시판
	List<BoardVO> boardSelectList(int startRow, int pageSize);// 게시판 목록
	
	BoardVO boardSelect(BoardVO vo);
	int boardInsert(BoardVO vo);//글쓰기
	int boardUpdate(BoardVO vo);//수정
	int boardDelete(BoardVO vo);//삭제
	
	List<BoardVO> boardSerarchList(String key, String val);//검색

}
