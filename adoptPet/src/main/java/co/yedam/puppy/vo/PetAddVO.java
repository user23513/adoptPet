package co.yedam.puppy.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetAddVO {
	
	private int petAddNo; //not null - 동물등록 번호
	private String petAddName; //not null - 동물 이름
	private String petAddAge; // 동물 나이
	private String petAddGender; //not null - 동물 성별
	private String petAddWeight; //동물 체중
	private String petAddHealth; //동물 건강상태
	private String petAddAdoptState; //not null - 동물 입양여부
	private String petAddType; //not null-동물 유형
	
	private boolean petAddCheck;//동물 글쓰기 했는지 확인

}
