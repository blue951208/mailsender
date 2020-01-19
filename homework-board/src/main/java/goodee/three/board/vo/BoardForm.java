package goodee.three.board.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardForm {
	private int boardNo;
	private String boardPw;
	private String boardTitle;
	private String boardContent;
	private String boardUser;
	private String boardDate;
	private MultipartFile boardFile; //파일이 여러개일때는 배열이나 Arraylist
}
