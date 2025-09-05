package sinchonthon.team2.member.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sinchonthon.team2.login.auth.AuthService;
import sinchonthon.team2.member.domain.Member;
import sinchonthon.team2.member.dto.MemberResponse;
import sinchonthon.team2.member.dto.SignUpRequest;
import sinchonthon.team2.member.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    //회원가입

    public String signUp(SignUpRequest request) {
        Member member = request.toEntity();
        //비번 암호화 적용
        member.setPassword(passwordEncoder.encode(request.getPassword()));
        //저장
        memberRepository.save(member);

        return "회원가입 성공!";
    }

    @Transactional(readOnly = true)
    //전체 회원 조회
    public List<MemberResponse> getUsers()
    {
        return memberRepository.findAll()
                .stream()
                .map(MemberResponse::from)
                .toList();
    }

    //회원정보 조회
    public MemberResponse getUser(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new IllegalArgumentException("해당 회원이 존재하지 않습니다. "));

        return MemberResponse.from(member);
    }
}
