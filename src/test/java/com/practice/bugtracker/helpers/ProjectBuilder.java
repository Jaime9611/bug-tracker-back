package com.practice.bugtracker.helpers;

import com.practice.bugtracker.dtos.ProjectDTO;
import com.practice.bugtracker.models.Project;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

  public static List<Project> buildProjectList(int size) {
    List<Project> statusList = new ArrayList<>();

    for (int x = 0; x < size; x++) {
      statusList.add(buildProject());
    }

    return statusList;
  }

  public static List<ProjectDTO> buildProjectDTOList(int size) {
    List<ProjectDTO> statusList = new ArrayList<>();

    for (int x = 0; x < size; x++) {
      statusList.add(buildProjectDto());
    }

    return statusList;
  }

}
