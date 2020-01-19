package goodee.three.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.three.board.mapper.MemberMapper;
import goodee.three.board.vo.LoginForm;
import goodee.three.board.vo.Member;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{
	@Autowired private MemberMapper memberMapper;
	
	@Override
	public int deleteMemberList(int memberNo) {
		System.out.println("탈퇴 서비스"+memberNo);
		return memberMapper.deleteMember(memberNo);
	}
	
	@Override
	public int joinMemberList(Member member) {
		System.out.println("회원가입 service:"+member);
		return memberMapper.insertMember(member);
	}
	
	@Override
	public Member getMemberOne(LoginForm loginForm) {
		return memberMapper.selectMemberOne(loginForm);
	}
}
