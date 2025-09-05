package sinchonthon.team2.subject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sinchonthon.team2.subject.domain.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {


}
