package co.yedam.puppy.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeartVO {

	private String memberId; //not null - 회원 아이디
	private int petListNo; //not null - 입양동물소개게시판 번호
	
	private String heartColor; //하트색상
	private int heartCount; //하트 수
}
