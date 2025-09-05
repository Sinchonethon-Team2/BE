package sinchonthon.team2.challenge.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import sinchonthon.team2.common.domain.Period;
import sinchonthon.team2.common.domain.ResultStatus;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "challenges")
public class Challenge {

    @Id @GeneratedValue
    @Column(name = "challenge_id")
    private Long id;

    @Embedded
    @Column(name = "challenge_period")
    private Period period;

    @Column(name = "challenge_task")
    private String task;

    @Enumerated(EnumType.STRING)
    @Column(name = "challenge_result_status")
    private ResultStatus resultStatus = ResultStatus.NEUTRAL;

    @Column(name = "challenge_notice")
    private String notice;

    /**
     * 정적 팩토리 메서드에서만 사용할 생성자.
     */
    private Challenge(Period period, String task, String notice) {
        this.period = period;
        this.task = task;
        this.notice = notice;
    }

    /**
     * 단일 공통 진입점으로 사용할 정적 팩토리 메서드.
     * 챌린지 생성시 사용한다.
     */
    public static Challenge create(Period period, String task, String notice) {
        return new Challenge(period, task, notice);
    }
}
