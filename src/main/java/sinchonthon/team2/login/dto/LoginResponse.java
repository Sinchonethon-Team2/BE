package sinchonthon.team2.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sinchonthon.team2.member.domain.School;

@Data
@AllArgsConstructor
public class LoginResponse {

    Long memberId;
    School school;

}
