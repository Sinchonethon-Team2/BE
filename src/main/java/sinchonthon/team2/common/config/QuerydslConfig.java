package sinchonthon.team2.common.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuerydslConfig {
    // 엔티티 매니저를 주입 받습니다.
    @PersistenceContext
    private EntityManager em;

    // 주입 받은 엔티티 매니저를 사용해 쿼리 팩토리를 빈으로 등록합니다.
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }
}
