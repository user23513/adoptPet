
package co.yedam.puppy.heart.service;

import java.util.List;

import co.yedam.puppy.vo.HeartVO;


public interface HeartService {

	List<HeartVO> myHeartList(int startRow, int pageSize); //내가누른하트리스트
    int heartCheck(HeartVO vo); //하트클릭여부 체크(1이면 체크되어있는거 0이면 체크안되어있는거)
	int heartInsert(HeartVO vo); //하트클릭여부가 0이면 하트디비에 정보가 입력되게
	int heartDelete(HeartVO vo); //하트클릭여부가 1이면 하트디비에 정보삭제
	int heartCount(int petListNo); //입양동물소개게시판 번호를 가지고 하트수를 카운트해서 뿌려주기
}



