package sinchonthon.team2.common.mapper;

import sinchonthon.team2.common.domain.Period;
import sinchonthon.team2.common.dto.command.PeriodDto;

public class PeriodMapper {

    public static Period from(PeriodDto dto) {
        return Period.create(dto.getBegin(), dto.getEnd());
    }
}
