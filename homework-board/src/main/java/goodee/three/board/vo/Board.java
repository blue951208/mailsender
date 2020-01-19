package goodee.three.board.vo;

import lombok.Data;

@Data
public class Board {
	private int boardNo;
	private String boardPw;
	private String boardTitle;
	private String boardContent;
	private String boardUser;
	private String boardDate;
	private BoardFile boardFile;
}
