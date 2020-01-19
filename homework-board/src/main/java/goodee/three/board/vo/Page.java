package goodee.three.board.vo;

import lombok.Data;

@Data
public class Page {
	private int beginRow;
	private int rowPerPage;
	private String searchWord;
}
