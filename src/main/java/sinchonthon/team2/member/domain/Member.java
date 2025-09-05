package sinchonthon.team2.member.domain;

import jakarta.persistence.*;
import lombok.*;
import sinchonthon.team2.membership.domain.Membership;
import sinchonthon.team2.team.domain.Team;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    //로그인 아이디
    private String loginId;
    @Column(name = "member_name")
    private String nickname;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_school")
    private School school;

    /**
     * 사용자가 참여한 팀.
     * 양방향 연관관계로 설정하였습니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team")
    private Team team;

    /**
     * Member - Team 다대다 관계 분리를 위해 사용하는 연관관계.
     * 개발 편의성을 위해 양방향 연관관계로 설정하였습니다.
     */
    @OneToMany(mappedBy = "member")
    private List<Membership> memberships = new ArrayList<>();

    /**
     * 정적 팩토리 메서드에서만 사용하는 생성자.
     */
    private Member(String nickname, School school) {
        this.nickname = nickname;
        this.school = school;
    }

    /**
     * 단일 공통 진입점.
     * 정적 팩토리 메서드.
     * 회원 가입에서 호출합니다.
     */
    public static Member create(String name, School school) {
        return new Member(name, school);
    }
}
