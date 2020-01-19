package goodee.three.board.service;

import java.util.List;

import goodee.three.board.vo.Comment;


public interface CommentService {
	public boolean insertComment(Comment comment);
	
	public List<Comment> selectCommentList(int boardNo);
	
	public int deleteCommentList(Comment comment);
	
}
