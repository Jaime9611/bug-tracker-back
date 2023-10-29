package com.practice.bugtracker.dtos;

import java.time.LocalDateTime;
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
}
