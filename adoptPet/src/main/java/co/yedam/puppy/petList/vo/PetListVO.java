package co.yedam.puppy.petList.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetListVO {

	private int petListNo; //not null - 동물소개게시판 번호
	private int boardId; //not null - 게시판유형   
	private String petListTitle; //not null - 동물소개게시판 제목
	private String petListContent; //동물소개게시판 내용
	private String petListWriter; //not null - 동물소개게시판 작성자
	private String petListState; //not null - 동물소개게시판 입양여부
	private String petListType; //not null - 동물소개게시판 동물유형
}
