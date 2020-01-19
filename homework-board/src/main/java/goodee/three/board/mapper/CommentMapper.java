package goodee.three.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goodee.three.board.vo.Comment;

@Mapper
public interface CommentMapper {
	public int deleteComment(Comment comment);
	
	public List<Comment> getCommentList(int boardNo);
	
	public boolean insertComment(Comment comment); 
	
	public int deleteAll(int boardNo);
}
