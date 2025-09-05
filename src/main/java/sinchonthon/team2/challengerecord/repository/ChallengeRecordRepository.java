package sinchonthon.team2.challengerecord.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sinchonthon.team2.challengerecord.domain.ChallengeRecord;

import java.util.Optional;

public interface ChallengeRecordRepository extends JpaRepository<ChallengeRecord, Long> {
    Optional<ChallengeRecord> findByChallengeIdAndMemberId(long challengeId, long memberId);
}
