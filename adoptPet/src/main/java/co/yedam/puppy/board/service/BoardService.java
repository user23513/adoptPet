package co.yedam.puppy.board.service;

import java.util.List;

import co.yedam.puppy.board.vo.BoardVO;

public interface BoardService {
	// 봉사활동 후기 게시판
	List<BoardVO> volReviewSelectList(); // 목록
	BoardVO volReviewSelectOne(BoardVO vo); // 조회
	int volReviewUpdate (BoardVO vo); // 수정
	int volReviewDelete (BoardVO vo); // 삭제
	int volReviewInsert (BoardVO vo); // 추가
	
	List<BoardVO> volReviewAddSelectList(int startRow, int pageSize); // 게시판 전체 조회
}
