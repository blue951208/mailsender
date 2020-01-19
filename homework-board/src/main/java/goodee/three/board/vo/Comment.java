package goodee.three.board.vo;

import lombok.Data;

@Data
public class Comment {
	private int commentNo;
	private int boardNo;
	private String commentPw;
	private String commentContent;
	private String commentUser;
}
