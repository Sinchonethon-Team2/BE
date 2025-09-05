package sinchonthon.team2.team.domain;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface TeamRepository extends JpaRepository<Team, Long> {





}
