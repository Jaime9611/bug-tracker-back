package com.practice.bugtracker.dtos.mappers.impls;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.practice.bugtracker.dtos.ProjectDTO;
import com.practice.bugtracker.dtos.TeamDTO;
import com.practice.bugtracker.dtos.mappers.ProjectMapper;
import com.practice.bugtracker.models.Project;
import com.practice.bugtracker.models.Team;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProjectMapperImplTest {

  private ProjectMapper projectMapper;

  @BeforeEach
  void setUp() {
    projectMapper = new ProjectMapperImpl(new TeamMapperImpl(), new StatusMapperImpl());
  }

  @Test
  void shouldMapProjectToProjectDto() {
    Team mockTeam = Team.builder()
        .id(UUID.randomUUID())
        .title("Test team")
        .build();

    Project project = Project.builder()
        .projectId(UUID.randomUUID())
        .title("Project test dto")
        .team(mockTeam)
        .startsAt(LocalDateTime.now())
        .endsAt(LocalDateTime.now().plusMonths(1))
        .build();

    ProjectDTO resultDto = projectMapper.projectToProjectDto(project);

    assertThat(project.getProjectId()).isEqualTo(resultDto.getId());
    assertThat(project.getTitle()).isEqualTo(resultDto.getTitle());
    assertThat(project.getStartsAt()).isEqualTo(resultDto.getStartsAt());
    assertThat(project.getEndsAt()).isEqualTo(resultDto.getEndsAt());
    assertThat(project.getTeam().getId()).isEqualTo(resultDto.getTeam().getId());
  }

  @Test
  void shouldMapProjectDtoToProject() {
    TeamDTO mockTeamDto = TeamDTO.builder()
        .id(UUID.randomUUID())
        .title("Test team")
        .build();

    ProjectDTO dto = ProjectDTO.builder()
        .id(UUID.randomUUID())
        .title("Project test model")
        .team(mockTeamDto)
        .startsAt(LocalDateTime.now())
        .endsAt(LocalDateTime.now().plusMonths(1))
        .build();

    Project resultModel = projectMapper.projectDtoToProject(dto);

    assertThat(dto.getId()).isEqualTo(resultModel.getProjectId());
    assertThat(dto.getTitle()).isEqualTo(resultModel.getTitle());
    assertThat(dto.getStartsAt()).isEqualTo(resultModel.getStartsAt());
    assertThat(dto.getEndsAt()).isEqualTo(resultModel.getEndsAt());
    assertThat(dto.getTeam().getId()).isEqualTo(resultModel.getTeam().getId());
  }
}