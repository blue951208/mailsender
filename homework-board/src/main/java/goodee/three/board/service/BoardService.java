package goodee.three.board.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import goodee.three.board.mapper.BoardMapper;
import goodee.three.board.vo.Board;
import goodee.three.board.vo.BoardForm;


public interface BoardService {
	
	public Map<String,Object> selectBoardList(int currentPage,int rowPerPage, String searchWord);
	
	public void insertBoard(BoardForm boardForm);
	
	public Board selectBoradOne(int boardNo);
	
	public int deleteBoard(Board board);
	
	public int updateBoard(BoardForm boardForm);
	
}
