package co.yedam.puppy.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FilesVO {
	private int filesNo; //파일번호 not null
	private int boardId; //게시판 유형 not null
	private String filesName; //파일 이름 not null
	private String filesPath; //파일 실제 저장경로 not null
	private String filesType; //파일 유형 not null
	private int boardNo; //게시판 번호
	private int petListNo; //동물소개 게시판번호

}
