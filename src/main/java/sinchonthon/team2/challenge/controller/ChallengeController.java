package sinchonthon.team2.challenge.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sinchonthon.team2.challenge.dto.command.ChallengeFixDto;
import sinchonthon.team2.challenge.dto.request.ChallengeFixRequest;
import sinchonthon.team2.challenge.service.ChallengeCommandService;
import sinchonthon.team2.challenge.service.ChallengeQueryService;
import sinchonthon.team2.common.dto.response.ApiResponse;

/**
 * 챌린지 수정 [o]
 * 챌린지 완료 [o]
 * 챌린지 실패 [o]
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/teams/{teamId}/challenges")
public class ChallengeController {

    private final ChallengeCommandService challengeCommandService;
    private final ChallengeQueryService challengeQueryService;

    // 챌린지 수정 엔드포인트
    @PutMapping("/{challengeId}")
    public ResponseEntity<ApiResponse<Void>> fixChallenge(@PathVariable Long challengeId, @RequestBody ChallengeFixRequest request) {

        // 챌린지를 수정합니다.
        challengeCommandService.fixChallenge(challengeId, ChallengeFixDto.from(request));

        return ResponseEntity.ok(ApiResponse.success("챌린지 수정에 성공하였습니다."));
    }

    // 챌린지 실패 엔드 포인트
}
