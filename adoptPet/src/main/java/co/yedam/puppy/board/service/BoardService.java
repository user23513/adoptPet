package co.yedam.puppy.board.service;

import java.util.List;

import co.yedam.puppy.vo.BoardVO;
import co.yedam.puppy.vo.FilesVO;


public interface BoardService {
	// 봉사활동 후기 게시판
	List<BoardVO> volReviewSelectList(int currentPage, int startRow, int pageSize); // 봉사후기 게시판 전체조회 목록
	int volReviewCount(); // DB에 있는 봉사후기 list 글 갯수 확인하여 페이징 처리할때 사용
	BoardVO volReviewSelectOne(BoardVO vo); // 단건조회
	int volReviewInsert (BoardVO vo); // 글쓰기
	int volReviewUpdate (BoardVO vo); // 수정
	int volReviewDelete (BoardVO vo); // 삭제
	List<BoardVO> volReviewSerarchList(String key, String val); // 검색

	//공지게시판
	List<BoardVO> boardSelectList(int currentPage, int startRow, int pageSize);// 공지 목록
	int boardCount();//DB공지 갯수 확인
	BoardVO boardSelect(BoardVO bvo,FilesVO fvo);//공지상세보기
	BoardVO boardSelectOne(BoardVO bvo, FilesVO fvo);//공지 단건 조회
	int noticeInsert(BoardVO bvo,FilesVO fvo);//글쓰기
	int noticeUpdate(BoardVO bvo,FilesVO fvo);//수정
	int noticeDelete(BoardVO bvo,FilesVO fvo);//삭제
	List<BoardVO> noticeSerarchList(String key, String val);//검색
	
	//입양후기 게시판
	List<BoardVO> adoptReviewSelectList(int currentPage, int startRow, int pageSize);//후기게시판 목록
	int apodtReviewCount();//DB공지 갯수 확인
	BoardVO adoptReviewSelect(BoardVO bvo,FilesVO fvo);//후기 상세보기
	BoardVO adoptFeviewSelectOne(BoardVO vo);//후기 단건 조회
	int adoptReviewInsert(BoardVO bvo,FilesVO fvo);//후기 글쓰기
	int adoptReviewUpdate(BoardVO bvo,FilesVO fvo);//수정
	int adoptReviewDelete(BoardVO bvo,FilesVO fvo);//삭제
	List<BoardVO> adoptReviewSearchList(String key, String val);//검색

}
