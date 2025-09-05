package sinchonthon.team2.subject.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "subjects")
public class Subject {

    @Id @GeneratedValue
    @Column(name = "subject_id")
    private Long id;

    @Column(name = "subject_name")
    private String name;

    /**
     * 정적 팩토리 메서드에서만 사용할 생성자.
     */
    private Subject (String name) {
        this.name = name;
    }

    /**
     * 단일 공통 진입점으로 사용할 정적 팩토리 메서드.
     * 과목 등록시 사용한다.
     */
    public static Subject create(String name) {
        return new Subject(name);
    }
}
