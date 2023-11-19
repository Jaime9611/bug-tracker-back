package com.practice.bugtracker.helpers;

import com.practice.bugtracker.dtos.TeamDTO;
import com.practice.bugtracker.models.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TeamBuilder {

  private static final String TITLE = "Team test title";

  public static Team buildTeam() {
    return Team.builder()
        .id(UUID.randomUUID())
        .title(TITLE)
        .build();
  }

  public static TeamDTO buildTeamDto() {
    return TeamDTO.builder()
        .id(UUID.randomUUID())
        .title(TITLE)
        .build();
  }

  public static List<Team> buildTeamList(int size) {
    List<Team> teamList = new ArrayList<>();

    for(int x = 0; x < size; x++) {
      teamList.add(buildTeam());
    }

    return teamList;
  }
}
