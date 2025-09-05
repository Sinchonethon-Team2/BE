package sinchonthon.team2.challenge.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import sinchonthon.team2.common.dto.command.PeriodDto;

@Data
@AllArgsConstructor
public class ChallengeFixRequest {
    private PeriodDto period;
    private String task;
    private String notice;
}
