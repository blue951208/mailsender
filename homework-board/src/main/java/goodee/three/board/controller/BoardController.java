package goodee.three.board.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.three.board.mapper.BoardFileMapper;
import goodee.three.board.service.BoardService;
import goodee.three.board.vo.Board;
import goodee.three.board.vo.BoardFile;
import goodee.three.board.vo.BoardForm;
import goodee.three.board.vo.Member;

@Controller
public class BoardController {
	@Autowired private BoardService boardService;
	@Autowired private BoardFileMapper boardFileService;

	//삭제 페이지로 이동
	@GetMapping("/deleteBoardForm")
	public String delete(HttpSession session,Model model,@RequestParam("boardNo")int boardNo) {
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/login";
		}
		System.out.println("삭제 페이지 이동 No:"+boardNo);
		model.addAttribute("boardNo", boardNo);
		return "deleteBoardForm";
	}
	
	//삭제
	@PostMapping("/deleteBoard") 
	public String deleteBoardList(HttpSession session,Board board) {
		System.out.println("delete control board ck:"+board);
		boardService.deleteBoard(board);
		return "redirect:/selectBoardList";
	}
	
	
	@GetMapping("/index") //index 페이지
	public String index(HttpSession session) {
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/login";
		}
		System.out.println("index page");//O
		return "index";
	}
	
	//수정 페이지 이동
	@GetMapping("/updateBoardForm")
	public String update(HttpSession session,Model model,@RequestParam("boardNo")int boardNo) {
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/login";
		}
		System.out.println("수정 페이지 이동 board ck:"+boardNo);
		
		Board board = boardService.selectBoradOne(boardNo);
		System.out.println("수정 사항 "+board);
		
		model.addAttribute("board", board);
		return "updateBoardForm";
	}
	
	
	
	  @PostMapping("/updateBoard")//수정 
	  public String updateBoardList(HttpSession session,BoardForm boardForm) {
	  System.out.println("수정사항 controller ck:"+boardForm);
	  boardService.updateBoard(boardForm); 
	  return "redirect:/selectBoardOne?boardNo="+boardForm.getBoardNo(); 
	  }
	 
	
	@PostMapping("/insertBoard") // insert form 입력후 다시 list페이지로
	public String insertBoardList(HttpSession session,BoardForm board) {
		System.out.println("isnert para:"+board);
		boardService.insertBoard(board);
		return "redirect:/selectBoardList";
	}
	
	
	@GetMapping("/selectBoardList") //list 페이지
	public String selectBoardList(HttpSession session,Model model,
					@RequestParam(value="currentPage",defaultValue="1") int currentPage,
					@RequestParam(value="searchWord",required=false)String searchWord) {
		/*
		 * if(session.getAttribute("loginMember") == null) { return "redirect:/login"; }
		 */
		System.out.println("selectBoardList page ck"+currentPage);
		System.out.println("검색할 단어:"+searchWord);
		int rowPerPage =10;
		Map<String,Object> map = boardService.selectBoardList(currentPage, rowPerPage,searchWord); 
		System.out.println("control map :"+map);
		
		model.addAttribute("member", session.getAttribute("loginMember"));
		model.addAttribute("map", map);
		model.addAttribute("searchWord", searchWord);
		
		return "selectBoardList";
	}
	
	//상세보기
	@GetMapping("/selectBoardOne")
	public String selectBoardOne(HttpSession session,Model model,@RequestParam("boardNo")int boardNo) {
		if(session.getAttribute("loginMember") == null) {//login 여부 확인
			return "redirect:/login";
		}
		
		System.out.println("One control No:"+boardNo);
		Board board = boardService.selectBoradOne(boardNo);
		System.out.println("control boardOne :"+board);
		model.addAttribute("member", session.getAttribute("loginMember"));
		model.addAttribute("board", board);
		
		return "selectBoardOne";
	}
	
}
