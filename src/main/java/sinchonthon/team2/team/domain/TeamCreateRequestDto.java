package sinchonthon.team2.team.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TeamCreateRequestDto(
        @NotBlank String name,
        String notice,
        @NotNull LocalDateTime startDate,
        @NotNull LocalDateTime endDate,
        @Min(2) int total,
        @Min(0) int amount,
        int goal,
        @NotNull Long subjectId,
        @Min(4) int challengeCount

) {}
