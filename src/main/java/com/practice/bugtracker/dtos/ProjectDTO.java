package com.practice.bugtracker.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectDTO {
  private UUID id;
  private String title;
  private TeamDTO team;
  private LocalDateTime startsAt;
  private LocalDateTime endsAt;
  private StatusDTO status;
  private List<TicketResponseDTO> tickets;
}
