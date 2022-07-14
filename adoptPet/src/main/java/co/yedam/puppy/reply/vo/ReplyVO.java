package co.yedam.puppy.reply.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyVO {
	private int replyNo;
	private int boardNo;
	private String replyContent;
	private Date replyDate;

}
