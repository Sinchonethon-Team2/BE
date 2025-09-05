package sinchonthon.team2.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sinchonthon.team2.challenge.domain.Challenge;

import java.util.Optional;

public interface ChallengeRepository extends JpaRepository<Challenge, Long>, ChallengeRepositoryCustom {
    public Optional<Challenge> findById(long id);
}
