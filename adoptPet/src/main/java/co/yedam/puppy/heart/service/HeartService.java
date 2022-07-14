package co.yedam.puppy.heart.service;

import co.yedam.puppy.heart.vo.HeartVO;

public interface HeartService {

	int heartCheck(HeartVO vo); //하트클릭여부 체크(1이면 체크되어있는거 0이면 체크안되어있는거)
	int heartInsert(HeartVO vo); //하트클릭여부가 0이면 하트디비에 정보가 입력되게
	int heartCount(int petListNo); //입양동물소개게시판 번호를 가지고 하트수를 카운트해서 뿌려주기
}
