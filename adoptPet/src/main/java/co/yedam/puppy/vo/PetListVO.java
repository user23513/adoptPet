package co.yedam.puppy.vo;

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
	
	//join해서 게시판에 뿌려줄때 쓰는 변수
	private String filesPath1; //파일1번 경로
	private String filesPath2; //파일2번 경로
	private String filesPath3; //파일3번 경로
	
	
	private int heartNum;//하트수
	private int heartCheck;//하트체크여부
}
