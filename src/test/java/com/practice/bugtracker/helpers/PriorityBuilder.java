package com.practice.bugtracker.helpers;

import com.practice.bugtracker.dtos.PriorityDTO;
import com.practice.bugtracker.models.Priority;
import java.util.UUID;

public class PriorityBuilder {

  private static final String TITTLE = "Priority test title";

  public static Priority buildPriority() {
    return Priority.builder()
        .id(UUID.randomUUID())
        .title(TITTLE)
        .build();
  }

  public static PriorityDTO buildPriorityDto() {
    return PriorityDTO.builder()
        .id(UUID.randomUUID())
        .title(TITTLE)
        .build();
  }

}
