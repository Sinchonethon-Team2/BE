package sinchonthon.team2.team.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sinchonthon.team2.member.domain.Member;
import sinchonthon.team2.subject.domain.Subject;
import sinchonthon.team2.subject.repository.SubjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamService {
    private final TeamRepository teamRepository;
    private final SubjectRepository subjectRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public TeamResponseDto createTeam(TeamCreateRequestDto requestDto, Long holderId){
        Member holder = memberRepository.findById(holderId)
                .orElseThrow(()-> new IllegalArgumentException("멤버를 찾을 수 없습니다."));

        Subject subject = subjectRepository.findById(requestDto.subjectId())
                .orElseThrow(()-> new IllegalArgumentException("과목을 찾을 수 없습니다."));

        var period = new sinchonthon.team2.common.domain.Period();
        period.begin = requestDto.startDate();
        period.end = requestDto.endDate();

        Team team = Team.create(
                holder,
                requestDto.notice(),
                requestDto.name(),
                period,
                requestDto.total(),
                requestDto.amount(),
                requestDto.goal(),
                null,/// 이미지 추후 처리
                requestDto.challengeCount()
        );
        teamRepository.save(team);

        return toResponse(team);
    }

    public List<TeamResponseDto> getTeams(){
        return teamRepository.findAll().stream()
                .map(this::toResponse)
                .toList();

    }

    public TeamResponseDto getTeam(Long teamId){
        Team team = teamRepository.findById(teamId)
                .orElseThrow(()-> new IllegalArgumentException("팀을 찾을 수 없습니다."));
    }

    public TeamResponseDto toResponse(Team team){
        return new TeamResponseDto(
                team.getId(),
                team.getName(),
                team.getNotice(),
                team.getPeriod().getBegin(),
                team.getPeriod().getEnd(),
                team.getCurrent(),
                team.getTotal(),
                team.getAmount(),
                team.getGoal(),
                team.getRecruitStatus().name(),
                team.getResultStatus().name(),
                team.getHolder().getName(),
                team.getSubject().getId(),
                team.getSubject().getName(),
                team.getChallenges().size()
        );
    }




}
