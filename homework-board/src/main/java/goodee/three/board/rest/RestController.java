package goodee.three.board.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.three.board.service.CommentService;
import goodee.three.board.vo.Comment;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	@Autowired CommentService commentService;
	
	@PostMapping("/rest/deleteComment")
	public String deleteCommentList(Comment comment) {
		System.out.println("delete Controller :"+comment);
		commentService.deleteCommentList(comment);
		return "success";
	}
	
	@PostMapping("/rest/getCommentList")
	public List<Comment> getCommentList(@RequestParam(value="boardNo")int boardNo){
		System.out.println("comment boardNo:"+boardNo);
		return commentService.selectCommentList(boardNo);
	}
	
	
	@PostMapping("/rest/addComment")
	public String addComment(Comment comment) {
		System.out.println("restControleer comment ck>: "+comment);
		//service 호출
		commentService.insertComment(comment);
		return "success";
	}
}