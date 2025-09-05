package sinchonthon.team2.common.dto.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PeriodDto {

    private LocalDateTime begin;
    private LocalDateTime end;
}
