package goodee.three.board.service;

import goodee.three.board.vo.LoginForm;
import goodee.three.board.vo.Member;

public interface MemberService {
	Member getMemberOne(LoginForm loginForm);
	
	int joinMemberList(Member member);
	
	int deleteMemberList(int memberNo);
}
