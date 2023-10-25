package com.practice.bugtracker.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class TicketDTO {
    private UUID id;
    private String title;
    private String description;
}
