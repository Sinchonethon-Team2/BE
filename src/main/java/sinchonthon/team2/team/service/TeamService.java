package sinchonthon.team2.team.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sinchonthon.team2.challenge.dto.command.ChallengeResponseDto;
import sinchonthon.team2.common.domain.Period;
import sinchonthon.team2.member.domain.Member;
import sinchonthon.team2.member.dto.MemberSummaryDto;
import sinchonthon.team2.member.repository.MemberRepository;
import sinchonthon.team2.subject.domain.Subject;
import sinchonthon.team2.subject.repository.SubjectRepository;
import sinchonthon.team2.team.domain.Team;
import sinchonthon.team2.team.dto.TeamCreateRequestDto;
import sinchonthon.team2.team.dto.TeamDetailResponseDto;
import sinchonthon.team2.team.dto.TeamResponseDto;
import sinchonthon.team2.team.repository.TeamRepository;

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

        Period period = Period.create(requestDto.startDate(),requestDto.endDate());

        Team team = Team.create(
                holder,
                subject,
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
        return toResponse(team);
    }

    @Transactional(readOnly = true)
    public TeamDetailResponseDto getTeamDetail(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));

        // 챌린지 변환
        List<ChallengeResponseDto> challengeDtos = team.getChallenges().stream()
                .map(ch -> new ChallengeResponseDto(
                        ch.getId(),
                        ch.getTask(),
                        ch.getNotice(),
                        ch.getIngredient().name(),
                        ch.getResultStatus().name()
                ))
                .toList();

        // 팀원 변환
        List<MemberSummaryDto> memberDtos = team.getMemberships().stream()
                .map(ms -> new MemberSummaryDto(
                        ms.getMember().getId(),
                        ms.getMember().getNickname()
                ))
                .toList();

        return new TeamDetailResponseDto(
                team.getId(),
                team.getName(),
                team.getNotice(),
                team.getPeriod().getBegin(),
                team.getPeriod().getEnd(),
                team.getCurrent(),
                team.getTotal(),
                team.getGoal(),
                team.getRecruitStatus().name(),
                team.getResultStatus().name(),
                challengeDtos,
                memberDtos
        );
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
                team.getHolder().getNickname(),
                team.getSubject().getId(),
                team.getSubject().getName(),
                team.getChallenges().size()
        );
    }




}
