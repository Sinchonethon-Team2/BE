package sinchonthon.team2.team.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import sinchonthon.team2.challenge.domain.Challenge;
import sinchonthon.team2.common.domain.Period;
import sinchonthon.team2.common.domain.ResultStatus;
import sinchonthon.team2.image.domain.Image;
import sinchonthon.team2.member.domain.Member;
import sinchonthon.team2.membership.domain.Membership;
import sinchonthon.team2.subject.domain.Subject;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "teams")
public class Team {

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    /**
     * 팀의 개설자.
     * 반대 방향 연관 관계는 NULL 의 존재 가능성으로 인해 설계하지 않았습니다.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "holder")
    private Member holder;

    /**
     * Member - Team 다대다 관계 분리를 위해 사용하는 연관관계.
     * 참여자를 저장합니다.
     * 개발 편의성을 위해 양방향으로 설정하였습니다.
     */
    @OneToMany(mappedBy = "team")
    private List<Membership> memberships = new ArrayList<>();

    /**
     * 팀의 학습 과목
     * NULL 방지를 위해 반대 방향 연관관계는 설정하지 않았습니다.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject")
    private Subject subject;

    /**
     * 팀의 챌린지 컬렉션.
     * 일대다 이므로, 개발 편의성을 위해 양방향 연관관계로 설계하였습니다.
     */
    @OneToMany(mappedBy = "team")
    @JoinColumn(name = "challenges")
    private List<Challenge> challenges = new ArrayList<>();

    /**
     * 팀 대표 이미지.
     * 일대일 단방향 연관관계.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_image")
    private Image image;

    @Column(name = "team_notice")
    private String notice;

    @Column(name = "team_name")
    private String name;

    @Embedded
    @Column(name = "team_period")
    private Period period;

    @Column(name = "team_current")
    private int current;

    @Column(name = "team_total")
    private int total;

    @Column(name = "team_amount")
    private int amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "team_result_status")
    private ResultStatus resultStatus = ResultStatus.NEUTRAL;

    @Enumerated(EnumType.STRING)
    @Column(name = "team_recruit_status")
    private RecruitStatus recruitStatus = RecruitStatus.RECRUITING;

    @Column(name = "team_goal")
    private int goal;

    /**
     * 정적 팩토리 메서드에서만 사용할 생성자.
     */
    private Team(Member holder, String notice, String name, Period period, int total, int amount, int goal, Image image) {
        this.holder = holder;
        this.notice = notice;
        this.name = name;
        this.period = period;
        this.total = total;
        this.amount = amount;
        this.goal = goal;
        this.image = image;
    }

    /**
     * 단일 공통 진입점으로 사용하는 정적 팩토리 메서드.
     * 팀 최초 등록시 사용한다.
     */
    public static Team create(Member holder, String notice, String name, Period period, int total, int amount, int goal, Image image) {

        Team team = new Team(holder, notice, name, period, total, amount, goal, image);
        Membership membership = Membership.create(team, holder);
        team.memberships.add(membership);

        return team;
    }
}
