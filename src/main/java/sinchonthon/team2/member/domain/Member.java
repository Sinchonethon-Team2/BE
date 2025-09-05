package sinchonthon.team2.member.domain;

import jakarta.persistence.*;

import lombok.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import sinchonthon.team2.image.domain.Image;
import sinchonthon.team2.membership.domain.Membership;
import sinchonthon.team2.team.domain.Team;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(name = "member_email")
    private String email;

    @Column(name = "member_password")
    private String password;

    /**
     * Member - Team 다대다 관계 분리를 위해 사용하는 연관관계.
     * 개발 편의성을 위해 양방향 연관관계로 설정하였습니다.
     */
    @OneToMany(mappedBy = "member")
    private List<Membership> memberships = new ArrayList<>();

    /**
     * 회원 프로필 이미지.
     * 단방향 일대일 연관관계입니다.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_image_id")
    private Image image;

    /**
     * 정적 팩토리 메서드에서만 사용하는 생성자.
     */
    private Member(String nickname, School school, String email, String password, Image image) {
        this.nickname = nickname;
      
        this.email = email;
        this.password = password;
        this.school = school;
        this.image = image;

    }

    /**
     * 단일 공통 진입점.
     * 정적 팩토리 메서드.
     * 회원 가입에서 호출합니다.
     */
    public static Member create(String name, School school, String email, String password, Image image) {
        return new Member(name, school, email, password, image);
    }
}
