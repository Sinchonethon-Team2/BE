package sinchonthon.team2.challengerecord.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sinchonthon.team2.challengerecord.repository.ChallengeRecordRepository;
import sinchonthon.team2.challengerecord.service.ChallengeRecordCommandService;
import sinchonthon.team2.common.dto.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teams/{teamId}/challenges")
public class ChallengeRecordController {

    private final ChallengeRecordCommandService challengeRecordCommandService;

    // 수정 필요
    // 챌린지 성공 엔드 포인트
    @PatchMapping("/{challengeId}/success")
    public ResponseEntity<ApiResponse<Void>> successChallenge(@PathVariable Long challengeId, Long userId) {

        // 참여자의 챌린지 참여 기록을 성공 처리합니다.
        challengeRecordCommandService.successChallenge(challengeId, userId);

        return ResponseEntity.ok(ApiResponse.success("챌린지 참여 성공을 기록하였습니다."));
    }

    // 수정 필요
    // 챌린지 실패 엔드 포인트
    @PatchMapping("/{challengeId}/fail")
    public ResponseEntity<ApiResponse<Void>> failChallenge(@PathVariable Long challengeId, Long userId) {

        // 참여자의 챌린지 참여 기록을 실패 처리합니다.
        challengeRecordCommandService.failChallenge(challengeId, userId);

        return ResponseEntity.ok(ApiResponse.success("챌린지 참여 실패를 기록하였습니다."));
    }
}
