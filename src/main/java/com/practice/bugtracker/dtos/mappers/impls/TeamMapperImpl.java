package com.practice.bugtracker.dtos.mappers.impls;

import com.practice.bugtracker.dtos.StatusDTO;
import com.practice.bugtracker.dtos.TeamDTO;
import com.practice.bugtracker.dtos.mappers.TeamMapper;
import com.practice.bugtracker.models.Status;
import com.practice.bugtracker.models.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamMapperImpl implements TeamMapper {

  @Override
  public Team teamDtoToTeam(TeamDTO dto) {
    if (dto == null) {
      return null;
    }

    Team.TeamBuilder team = Team.builder();

    team.id(dto.getId());
    team.title(dto.getTitle());

    return team.build();
  }

  @Override
  public TeamDTO teamToTeamDto(Team team) {
    if (team == null) {
      return null;
    }

    TeamDTO.TeamDTOBuilder dto = TeamDTO.builder();

    dto.id(team.getId());
    dto.title(team.getTitle());

    return dto.build();
  }
}
