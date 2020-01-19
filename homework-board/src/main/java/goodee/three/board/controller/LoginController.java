package goodee.three.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import goodee.three.board.service.MemberService;
import goodee.three.board.vo.LoginForm;
import goodee.three.board.vo.Member;

@Controller
public class LoginController {
	@Autowired private MemberService memberService;
	
	@GetMapping("/joinMember")
	public String joinPage() {
		System.out.println("회원가입 페이지로 이동");
		return "joinMember";
	}
	
	@PostMapping("/joinMemberList")
	public String joinMember(Member member) {
		System.out.println("회원가입 controller"+member);
		int row = memberService.joinMemberList(member);
		if(row!=0) {
			System.out.println("회원가입 완료");
		}
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login(HttpSession session) {
		System.out.println("login Page");
		Member member = (Member)session.getAttribute("loginMember");
		if(member != null) {
			System.out.println("login"+member);
			return "redirect:/index";
		}
		return "login";
	} 
	
	@PostMapping("/login")
	public String login(HttpSession session,LoginForm loginForm) {
		System.out.println("login 한 사람:"+loginForm);
		Member member = memberService.getMemberOne(loginForm);
		member.setMemberPw(loginForm.getMemberPw());
		System.out.println("member 에 값:"+member);
		if(member==null) {
			return "redirect:/login";
		}
		session.setAttribute("loginMember", member);
		System.out.println("session 에 저장된 값?"+session.getAttribute("loginMember"));
		return "redirect:/index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	@GetMapping("/outMember")
	public String outPage(HttpSession session) {
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/login";
		}
		System.out.println("회원탈퇴 페이지");
		return "outMember";
	}
	
	@PostMapping("/outMember")
	public String deleteMemberList(HttpSession session,Member member) {
		System.out.println("탈퇴 진행"+member);
		Member loginMember = (Member) session.getAttribute("loginMember");
		System.out.println("로그인중 회원"+loginMember);
		//비밀 번호 확인
		if(member.getMemberPw().equals(loginMember.getMemberPw())) {
			System.out.println("탈퇴 멤버"+loginMember);
			int row = memberService.deleteMemberList(loginMember.getMemberNo());
			//실행 여부 확인
			System.out.println("row="+row);
			if(row != 0) {
				System.out.println("회원 탈퇴 완료");
				session.invalidate();
			}
		}
		
		return "redirect:/login";
	}
}
