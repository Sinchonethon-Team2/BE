package sinchonthon.team2.challenge.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sinchonthon.team2.challenge.domain.Challenge;
import sinchonthon.team2.challenge.dto.command.ChallengeFixDto;
import sinchonthon.team2.challenge.repository.ChallengeRepository;
import sinchonthon.team2.common.mapper.PeriodMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChallengeCommandService {

    private final ChallengeRepository challengeRepository;

    public void fixChallenge(Long id, ChallengeFixDto dto) {

        // 챌린지를 조회합니다.
        Challenge challenge = challengeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("챌린지를 찾을 수 없습니다."));

        // 챌린지를 수정합니다.
        challenge.changeChallenge(PeriodMapper.from(dto.getPeriod()), dto.getTask(), dto.getNotice());
    }
}
