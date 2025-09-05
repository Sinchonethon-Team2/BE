package sinchonthon.team2.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Period {

    @Column(name = "begin")
    LocalDateTime begin;

    @Column(name = "end")
    LocalDateTime end;
}
