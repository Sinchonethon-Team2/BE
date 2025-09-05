package sinchonthon.team2.team.dto;

import java.time.LocalDateTime;

public record TeamResponseDto(
        Long id,
        String name,
        String notice,
        LocalDateTime startDate,
        LocalDateTime endDate,
        int current,
        int total,
        int amount,
        int goal,
        String recruitStatus,
        String resultStatus,
        String holderName,
        Long subjectId,
        String subjectName,
        int challengeCount

) {
}
