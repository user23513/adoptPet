package co.yedam.puppy.heart.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeartVO {

	private String memberId; //not null - 회원 아이디
	private int petListNo; //not null - 입양동물소개게시판 번호
}
