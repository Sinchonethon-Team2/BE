package sinchonthon.team2.challenge.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import sinchonthon.team2.common.domain.Period;
import sinchonthon.team2.common.domain.ResultStatus;
import sinchonthon.team2.image.domain.Image;
import sinchonthon.team2.team.domain.Team;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "challenges")
public class Challenge {

    @Id @GeneratedValue
    @Column(name = "challenge_id")
    private Long id;

    /**
     * 양방향 연관관계로 설정하였습니다.
     * 해당 챌린지를 등록한 팀입니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "challenge_ingredient")
    private Ingredient ingredient;

    /**
     * 정적 팩토리 메서드에서만 사용할 생성자.
     */
    private Challenge(Team team, Ingredient ingredient) {
        this.team = team;
        this.ingredient = ingredient;
    }

    /**
     * 단일 공통 진입점으로 사용할 정적 팩토리 메서드.
     * 챌린지 최초 생성시 사용한다.
     */
    public static Challenge create(Team team, Ingredient ingredient) {
        return new Challenge(team, ingredient);
    }

    /**
     * 챌린지 수정시 사용하는 메서드입니다.
     */
    public void changeChallenge(Period period, String task, String notice) {
        this.period = period;
        this.task = task;
        this.notice = notice;
    }
}
