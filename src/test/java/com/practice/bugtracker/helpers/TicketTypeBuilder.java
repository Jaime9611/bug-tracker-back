package com.practice.bugtracker.helpers;

import com.practice.bugtracker.dtos.TicketTypeDTO;
import com.practice.bugtracker.models.TicketType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TicketTypeBuilder {

  private static final String TITLE = "TicketType test title";

  public static TicketType buildTicketType() {
    return TicketType.builder()
        .id(UUID.randomUUID())
        .title(TITLE)
        .build();
  }

  public static TicketTypeDTO buildTicketTypeDto() {
    return TicketTypeDTO.builder()
        .id(UUID.randomUUID())
        .title(TITLE)
        .build();
  }

  public static List<TicketType> buildTicketTypeList(int size) {
    List<TicketType> statusList = new ArrayList<>();

    for (int x = 0; x < size; x++) {
      statusList.add(buildTicketType());
    }

    return statusList;
  }
}
