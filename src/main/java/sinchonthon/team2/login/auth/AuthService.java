package sinchonthon.team2.login.auth;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sinchonthon.team2.login.dto.LoginResponse;
import sinchonthon.team2.member.domain.Member;
import sinchonthon.team2.member.repository.MemberRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    public static final String LOGIN_SESSION_KEY = "login";
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;




    @Transactional
    //로그인처리
    public LoginResponse login(String loginId, String password, HttpSession session) {
        Member member =memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if(!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        //로그인 성공한 사용자 정보를 서버세션에 저장
        session.setAttribute(LOGIN_SESSION_KEY, member);
        //세션 유효시간 ( 30분 )
        session.setMaxInactiveInterval(1800);
        return new LoginResponse(member.getId(), member.getSchool());
    }


    //세션에서 로그인 사용자 조회
    public Member getLoginUser(HttpSession session) {
        Member member = (Member) session.getAttribute(LOGIN_SESSION_KEY);

        if (member == null) {
            throw new IllegalArgumentException("로그인된 사용자가 없습니다.");

        }
        return member;
    }

    //로그아웃 처리
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
