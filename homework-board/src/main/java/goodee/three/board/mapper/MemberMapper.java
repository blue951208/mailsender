package goodee.three.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import goodee.three.board.vo.LoginForm;
import goodee.three.board.vo.Member;

@Mapper
public interface MemberMapper {
	Member selectMemberOne(LoginForm loginForm);

	int insertMember(Member member);
	
	int deleteMember(int memberNo);
}
