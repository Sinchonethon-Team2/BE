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

    /**
     * 정적 팩토리 메서드에서만 사용하는 기본 생성자.
     */
    private Period(LocalDateTime begin, LocalDateTime end) {
        this.begin = begin;
        this.end = end;
    }

    /**
     *  단일 공통 진입점으로 사용하는 정적 팩토리 메서드.
     *  기간을 생성할 때 사용합니다.
     */
    public static Period create(LocalDateTime begin, LocalDateTime end) {
        return new Period(begin, end);
    }
}
