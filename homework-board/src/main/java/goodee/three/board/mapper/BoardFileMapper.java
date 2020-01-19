package goodee.three.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import goodee.three.board.vo.BoardFile;

@Mapper
public interface BoardFileMapper {
	public int insertBoardFile(BoardFile boardFile);
	
	public BoardFile selectBoardFileOne(int boardNo);
	
	public int deleteBoardFile(int boardNo);
	
	public int updateBoardFileOne(BoardFile boardFile);
}
