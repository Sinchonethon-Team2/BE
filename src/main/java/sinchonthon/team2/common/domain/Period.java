package sinchonthon.team2.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class Period {

    @Column(name = "begin")
    LocalDateTime begin;

    @Column(name = "end")
    LocalDateTime end;
}
