package com.practice.bugtracker.dtos.mappers;

import com.practice.bugtracker.dtos.TeamDTO;
import com.practice.bugtracker.models.Team;

public interface TeamMapper {

  Team teamDtoToTeam(TeamDTO dto);

  TeamDTO teamToTeamDto(Team team);

}

