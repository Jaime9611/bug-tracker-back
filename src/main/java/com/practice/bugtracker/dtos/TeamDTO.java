package com.practice.bugtracker.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamDTO {

  private UUID id;

  @NotNull
  @NotBlank
  @Size(min = 1, max = 50)
  private String title;
}
