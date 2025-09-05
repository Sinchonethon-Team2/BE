package sinchonthon.team2.generic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화 (POST 테스트 시 필요)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/members", "/api/v1/members/**").permitAll() // 회원가입, 조회 허용
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login.disable()) // 기본 로그인폼 비활성화 (원하면 유지 가능)
                .httpBasic(basic -> basic.disable()); // Basic Auth 비활성화 (선택)

        return http.build();
    }
}