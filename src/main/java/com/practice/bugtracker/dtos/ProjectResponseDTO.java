package com.practice.bugtracker.dtos;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectResponseDTO {
  private UUID projectId;
  private String title;
}
