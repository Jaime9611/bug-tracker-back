package com.practice.bugtracker.dtos.mappers.impls;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.practice.bugtracker.dtos.TeamDTO;
import com.practice.bugtracker.dtos.mappers.TeamMapper;
import com.practice.bugtracker.models.Team;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TeamMapperImplTest {

  private TeamMapper teamMapper;

  @BeforeEach
  void setUp() {
    teamMapper = new TeamMapperImpl();
  }

  @Test
  void shouldMapTeamToTeamDto() {
    Team team = Team.builder()
        .id(UUID.randomUUID())
        .title("Team test dto")
        .build();

    TeamDTO resultDto = teamMapper.teamToTeamDto(team);

    assertThat(team.getId()).isEqualTo(resultDto.getId());
    assertThat(team.getTitle()).isEqualTo(resultDto.getTitle());
  }

  @Test
  void shouldMapTeamDtoToTeam() {
    TeamDTO dto = TeamDTO.builder()
        .id(UUID.randomUUID())
        .title("Team test dto")
        .build();

    Team resultModel = teamMapper.teamDtoToTeam(dto);

    assertThat(dto.getId()).isEqualTo(resultModel.getId());
    assertThat(dto.getTitle()).isEqualTo(resultModel.getTitle());
  }
}