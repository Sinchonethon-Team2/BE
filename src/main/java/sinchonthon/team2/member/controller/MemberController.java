package sinchonthon.team2.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sinchonthon.team2.member.dto.MemberResponse;
import sinchonthon.team2.member.dto.SignUpRequest;
import sinchonthon.team2.member.repository.MemberRepository;
import sinchonthon.team2.member.service.MemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;

    //회원가입
    @PostMapping
    public ResponseEntity<String> SignUp(@RequestBody SignUpRequest request)
    {
        String result = memberService.signUp(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getUsers()
    {
        List<MemberResponse> members = memberService.getUsers();
        return ResponseEntity.ok(members);
    }
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> getUser(@PathVariable Long memberId)
    {
        return ResponseEntity.ok(memberService.getUser(memberId));
    }

}
