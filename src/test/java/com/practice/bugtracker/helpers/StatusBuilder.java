package com.practice.bugtracker.helpers;

import com.practice.bugtracker.dtos.StatusDTO;
import com.practice.bugtracker.models.Status;
import java.util.ArrayList;
import java.util.List;
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

  public static List<StatusDTO> buildStatusDtoList(int size, boolean createDto) {
    List<StatusDTO> statusList = new ArrayList<>();

    for (int x = 0; x < size; x++) {
      statusList.add(buildStatusDto());
    }

    return statusList;
  }

  public static List<Status> buildStatusList(int size) {
    List<Status> statusList = new ArrayList<>();

    for (int x = 0; x < size; x++) {
      statusList.add(buildStatus());
    }

    return statusList;
  }

}
