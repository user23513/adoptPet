package co.yedam.puppy.board.service;

import java.util.List;

import co.yedam.puppy.vo.BoardVO;
import co.yedam.puppy.vo.FilesVO;


public interface BoardService {
	// 봉사활동 후기 게시판
	List<BoardVO> volReviewSelectList(); // 게시판 목록
	BoardVO volReviewSelectOne(BoardVO vo); // 단건 조회
	int volReviewUpdate (BoardVO vo); // 수정
	int volReviewDelete (BoardVO vo); // 삭제
	int volReviewInsert (BoardVO vo); // 추가
	List<BoardVO> volReviewAddSelectList(int startRow, int pageSize); // 검색

	//공지게시판
	List<BoardVO> boardSelectList(int startRow, int pageSize);// 공지 목록
	BoardVO boardSelect(BoardVO bvo,FilesVO fvo);//공지상세보기
	int noticeInsert(BoardVO bvo,FilesVO fvo);//글쓰기
	int noticeUpdate(BoardVO bvo,FilesVO fvo);//수정
	int noticeDelete(BoardVO bvo,FilesVO fvo);//삭제
	List<BoardVO> noticeSerarchList(String key, String val);//검색
	
	//입양후기 게시판
	List<BoardVO> adoptReviewSelectList(int startRow, int pageSize);//후기게시판 목록
	BoardVO adoptReviewSelect(BoardVO bvo,FilesVO fvo);//후기 상세보기
	int adoptReviewInsert(BoardVO bvo,FilesVO fvo);//후기 글쓰기
	int adoptReviewUpdate(BoardVO bvo,FilesVO fvo);//수정
	int adoptReviewDelete(BoardVO bvo,FilesVO fvo);//삭제
	List<BoardVO> adoptReviewSearchList(String key, String val);//검색

}
