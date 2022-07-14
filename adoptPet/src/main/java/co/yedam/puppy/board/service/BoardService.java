package co.yedam.puppy.board.service;

import java.util.List;

import co.yedam.puppy.vo.BoardVO;
import co.yedam.puppy.vo.FilesVO;


public interface BoardService {
	// 봉사활동 후기 게시판
	List<BoardVO> volReviewSelectList(); // 목록
	BoardVO volReviewSelectOne(BoardVO vo); // 조회
	int volReviewUpdate (BoardVO vo); // 수정
	int volReviewDelete (BoardVO vo); // 삭제
	int volReviewInsert (BoardVO vo); // 추가
	List<BoardVO> volReviewAddSelectList(int startRow, int pageSize); // 게시판 전체 조회

	//공지게시판
	List<BoardVO> boardSelectList(int startRow, int pageSize);// 공지 목록
	BoardVO boardSelect(BoardVO vo);
	int noticeInsert(BoardVO bvo,FilesVO fvo);//글쓰기
	int noticeUpdate(BoardVO bvo,FilesVO fvo);//수정
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
