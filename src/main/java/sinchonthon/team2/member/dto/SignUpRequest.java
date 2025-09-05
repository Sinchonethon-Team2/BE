package sinchonthon.team2.member.dto;


import lombok.*;
import sinchonthon.team2.member.domain.Member;
import sinchonthon.team2.member.domain.School;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class SignUpRequest {
    private String nickname;
    private String email;
    private String loginId;
    private String password;
    private School school;

    public Member toEntity() {
        return Member.builder()
                .nickname(nickname)
                .loginId(loginId)
                .email(email)
                .password(password)
                .school(school)
                .build();
    }
}
