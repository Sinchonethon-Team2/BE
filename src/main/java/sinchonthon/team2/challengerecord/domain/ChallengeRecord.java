package sinchonthon.team2.challengerecord.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import sinchonthon.team2.challenge.domain.Challenge;
import sinchonthon.team2.common.domain.ResultStatus;
import sinchonthon.team2.member.domain.Member;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "challenge_records")
public class ChallengeRecord {

    @Id @GeneratedValue
    @Column(name = "challenge_record_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @Enumerated(EnumType.STRING)
    @Column(name = "result_status")
    private ResultStatus resultStatus = ResultStatus.NEUTRAL;

    /**
     * 정적 팩토리 메서드에서만 사용하는 생성자입니다.
     */
    private ChallengeRecord(Member member, Challenge challenge) {
        this.member = member;
        this.challenge = challenge;
    }

    /**
     * 단일 공통 진입점 정적 팩토리 메서드입니다.
     */
    public static ChallengeRecord create(Challenge challenge, Member member) {
        return new ChallengeRecord(member, challenge);
    }

    /**
     * 사용자의 챌린지 성공시 사용하는 메서드입니다.
     */
    public void successChallenge() {
        resultStatus = ResultStatus.SUCCESS;
    }

    /**
     * 사용자의 챌린지 실패시 사용하는 메서드입니다.
     */
    public void failChallenge() {
        resultStatus = ResultStatus.FAILURE;
    }
}
