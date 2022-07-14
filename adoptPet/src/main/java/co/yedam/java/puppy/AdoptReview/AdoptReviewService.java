package co.yedam.java.puppy.AdoptReview;

import java.util.List;

import co.yedam.puppy.board.vo.BoardVO;

public interface AdoptReviewService {
	//입양후기 게시판
	List<BoardVO> adoptReviewSelectList(int startRow, int pageSize);
	BoardVO adoptReviewSelect(BoardVO vo);
	int adoptReviewInsert(BoardVO vo);
	int adoptReviewUpdate(BoardVO vo);
	int adoptReviewDelete(BoardVO vo);
	
	List<BoardVO> adoptReviewSearchList(String key, String val);//검색

}
