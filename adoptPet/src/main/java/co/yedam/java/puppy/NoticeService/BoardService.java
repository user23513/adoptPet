package co.yedam.java.puppy.NoticeService;

import java.util.List;

import co.yedam.puppy.board.vo.BoardVO;

public interface BoardService {
	//공지게시판
	List<BoardVO> boardSelectList(int startRow, int pageSize);// 게시판 목록
	
	BoardVO boardSelect(BoardVO vo);
	int noticeInsert(BoardVO vo);//글쓰기
	int noticeUpdate(BoardVO vo);//수정
	int noticeDelete(BoardVO vo);//삭제
	
	List<BoardVO> noticeSerarchList(String key, String val);//검색
	
	//입양후기 게시판
	List<BoardVO> adoptReviewSelectList(int startRow, int pageSize);
	BoardVO adoptReviewSelect(BoardVO vo);
	int adoptReviewInsert(BoardVO vo);
	int adoptReviewUpdate(BoardVO vo);
	int adoptReviewDelete(BoardVO vo);
	
	List<BoardVO> adoptReviewSearchList(String key, String val);//검색

}
