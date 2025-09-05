package sinchonthon.team2.membership.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import sinchonthon.team2.member.domain.Member;
import sinchonthon.team2.team.domain.Team;

/**
 * Member - Team 다대다 관계 분리를 위해 사용하는 연관관계.
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "memberships")
public class Membership {

    @Id @GeneratedValue
    @Column(name = "membership_id")
    private Long id;

    /**
     * 개발 편의성을 위해 양방향으로 설계하였습니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team")
    private Team team;

    /**
     * 개발 편의성을 위해 양방향으로 설계하였습니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member")
    private Member member;

    /**
     * 정적 팩토리 메서드에서만 사용할 생성자.
     */
    private Membership(Team team, Member member) {
        this.team = team;
        this.member = member;
    }

    /**
     * 단일 공통 진입점으로 사용할 정적 팩토리 메서드.
     */
    public static Membership create(Team team, Member member) {
        return new Membership(team, member);
    }



}
