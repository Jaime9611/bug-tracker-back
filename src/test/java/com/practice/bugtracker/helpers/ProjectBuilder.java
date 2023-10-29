package com.practice.bugtracker.helpers;

import com.practice.bugtracker.dtos.ProjectDTO;
import com.practice.bugtracker.models.Project;
import java.time.LocalDateTime;
import java.util.UUID;

public class ProjectBuilder {

  private static final String TITLE = "Project test title";
  private static final LocalDateTime START_DATE = LocalDateTime.now();
  private static final LocalDateTime END_DATE = LocalDateTime.now().plusMonths(1);


  public static Project buildProject() {
    return Project.builder()
        .projectId(UUID.randomUUID())
        .title(TITLE)
        .team(TeamBuilder.buildTeam())
        .startsAt(START_DATE)
        .endsAt(END_DATE)
        .build();
  }

  public static ProjectDTO buildProjectDto() {
    return ProjectDTO.builder()
        .id(UUID.randomUUID())
        .title(TITLE)
        .team(TeamBuilder.buildTeamDto())
        .startsAt(START_DATE)
        .endsAt(END_DATE)
        .build();
  }

}
