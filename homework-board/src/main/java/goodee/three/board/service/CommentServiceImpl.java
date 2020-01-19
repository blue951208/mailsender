package goodee.three.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import goodee.three.board.mapper.CommentMapper;
import goodee.three.board.vo.Comment;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired private CommentMapper commentMapper;
	
	@Override
	public int deleteCommentList(Comment comment) {
		System.out.println("delete list");
		return commentMapper.deleteComment(comment);
	}
	
	@Override
	public List<Comment> selectCommentList(int boardNo) {
		System.out.println("comment List");
		return commentMapper.getCommentList(boardNo);
	}
	
	@Override
	public boolean insertComment(Comment comment) {
		System.out.println("comment service ck"+comment);
		return commentMapper.insertComment(comment);
	}

}
