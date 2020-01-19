package goodee.three.board.vo;

import lombok.Data;

@Data
public class BoardFile {
	private int boardFileNo;
	private int boardNo;
	private String fileName;
	private String extension;
	private String contentType;
	private long size;
	private String originName;
}
