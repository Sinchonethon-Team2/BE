package sinchonthon.team2.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import sinchonthon.team2.member.domain.Member;
import sinchonthon.team2.member.domain.School;

@Data
@AllArgsConstructor
public class MemberResponse {

    private Long memberId;
    private String nickname;
    private String email;
    private School school;

    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getId(), member.getNickname(), member.getEmail(), member.getSchool());
    }
}
