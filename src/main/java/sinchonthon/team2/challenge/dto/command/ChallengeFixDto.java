package sinchonthon.team2.challenge.dto.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import sinchonthon.team2.challenge.domain.Challenge;
import sinchonthon.team2.challenge.dto.request.ChallengeFixRequest;
import sinchonthon.team2.common.dto.command.PeriodDto;

@Data
@AllArgsConstructor
public class ChallengeFixDto {

    private PeriodDto period;
    private String task;
    private String notice;

    public static ChallengeFixDto from(ChallengeFixRequest request) {
        return new ChallengeFixDto(request.getPeriod(), request.getTask(), request.getNotice());
    }
}
