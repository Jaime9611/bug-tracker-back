package com.practice.bugtracker.helpers;

import com.practice.bugtracker.dtos.TicketDTO;
import com.practice.bugtracker.models.Ticket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TicketBuilder {

  private static final String TITLE = "Ticket test title";
  private static final LocalDateTime CREATED_DATE = LocalDateTime.now();
  private static final LocalDateTime UPDATED_DATE = LocalDateTime.now().plusMonths(1);
  private static final String DESC = "Ticket description";


  public static Ticket buildTicket() {
    return Ticket.builder()
        .ticketId(UUID.randomUUID())
        .title(TITLE)
        .createdAt(CREATED_DATE)
        .updatedAt(UPDATED_DATE)
        .project(ProjectBuilder.buildProject())
        .priority(PriorityBuilder.buildPriority())
        .status(StatusBuilder.buildStatus())
        .ticketType(TicketTypeBuilder.buildTicketType())
        .description(DESC)
        .build();
  }

  public static Ticket buildTicketWithoutId() {
    return Ticket.builder()
        .title(TITLE)
        .createdAt(CREATED_DATE)
        .updatedAt(UPDATED_DATE)
        .description(DESC)
        .build();
  }

  public static TicketDTO buildTicketDto() {
    return TicketDTO.builder()
        .id(UUID.randomUUID())
        .title(TITLE)
        .createdAt(CREATED_DATE)
        .updatedAt(UPDATED_DATE)
        .project(ProjectBuilder.buildProjectDto())
        .priority(PriorityBuilder.buildPriorityDto())
        .status(StatusBuilder.buildStatusDto())
        .ticketType(TicketTypeBuilder.buildTicketTypeDto())
        .description(DESC)
        .build();
  }

  public static List<Ticket> buildTicketList(int size) {
    List<Ticket> statusList = new ArrayList<>();

    for (int x = 0; x < size; x++) {
      statusList.add(buildTicket());
    }

    return statusList;
  }

}
