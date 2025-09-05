package sinchonthon.team2.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sinchonthon.team2.team.domain.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
