package sinchonthon.team2.challengerecord.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sinchonthon.team2.challengerecord.domain.ChallengeRecord;
import sinchonthon.team2.challengerecord.repository.ChallengeRecordRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ChallengeRecordCommandService {

    private final ChallengeRecordRepository challengeRecordRepository;

    // 참여자의 챌린지 참여 성공을 기록합니다.
    public void successChallenge(Long challengeId, Long memberId) {

        // 챌린지 참여 기록을 조회합니다.
        ChallengeRecord challengeRecord = challengeRecordRepository.findByChallengeIdAndMemberId(challengeId, memberId).orElseThrow(() ->
                new EntityNotFoundException("챌린지 참여 기록을 찾지 못했습니다."));

        // 챌린지 참여 기록을 성공으로 수정합니다.
        challengeRecord.successChallenge();
    }

    // 참여자의 챌린지 참여 실패를 기록합니다.
    public void failChallenge(Long challengeId, Long memberId) {

        // 챌린지 참여 기록을 조회합니다.
        ChallengeRecord challengeRecord = challengeRecordRepository.findByChallengeIdAndMemberId(challengeId, memberId).orElseThrow(() ->
                new EntityNotFoundException("챌린지 참여 기록을 찾지 못했습니다."));

        // 챌린지 참여 기록을 실패로 수정합니다.
        challengeRecord.failChallenge();
    }
}
