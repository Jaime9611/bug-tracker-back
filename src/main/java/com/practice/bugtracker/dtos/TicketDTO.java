package com.practice.bugtracker.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class TicketDTO {
    private UUID id;

    @NotBlank
    @NotNull
    private String title;

    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
