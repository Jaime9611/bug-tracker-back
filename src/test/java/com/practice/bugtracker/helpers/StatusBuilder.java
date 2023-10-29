package com.practice.bugtracker.helpers;

import com.practice.bugtracker.dtos.StatusDTO;
import com.practice.bugtracker.models.Status;
import java.util.UUID;

public class StatusBuilder {

  private static final String TITLE = "Status test title";

  public static Status buildStatus() {
    return Status.builder()
        .id(UUID.randomUUID())
        .title(TITLE)
        .build();
  }

  public static StatusDTO buildStatusDto() {
    return StatusDTO.builder()
        .id(UUID.randomUUID())
        .title(TITLE)
        .build();
  }

}
