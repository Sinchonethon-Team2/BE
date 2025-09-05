package sinchonthon.team2.team.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sinchonthon.team2.member.domain.Member;
import sinchonthon.team2.team.dto.TeamCreateRequestDto;
import sinchonthon.team2.team.dto.TeamDetailResponseDto;
import sinchonthon.team2.team.dto.TeamResponseDto;
import sinchonthon.team2.team.service.TeamService;

import java.util.List;

import static sinchonthon.team2.login.auth.AuthService.LOGIN_SESSION_KEY;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teams")
public class TeamController {
    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamResponseDto> createTeam(@RequestBody TeamCreateRequestDto requestDto,
                                                      @SessionAttribute(name = LOGIN_SESSION_KEY,required = false) Member loginmember){

        if(loginmember == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        TeamResponseDto responseDto = teamService.createTeam(requestDto, loginmember.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

    @GetMapping
    public ResponseEntity<List<TeamResponseDto>> getTeams(){
        return ResponseEntity.ok(teamService.getTeams());

    }
    @GetMapping("/{teamId}")
    public ResponseEntity<TeamDetailResponseDto> getTeamDetail(@PathVariable Long teamId){
        return ResponseEntity.ok(teamService.getTeamDetail(teamId));
    }

}
