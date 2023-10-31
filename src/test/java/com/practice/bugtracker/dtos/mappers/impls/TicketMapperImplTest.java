package com.practice.bugtracker.dtos.mappers.impls;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.practice.bugtracker.dtos.TicketDTO;
import com.practice.bugtracker.dtos.mappers.TicketMapper;
import com.practice.bugtracker.helpers.PriorityBuilder;
import com.practice.bugtracker.helpers.ProjectBuilder;
import com.practice.bugtracker.helpers.StatusBuilder;
import com.practice.bugtracker.helpers.TicketBuilder;
import com.practice.bugtracker.helpers.TicketTypeBuilder;
import com.practice.bugtracker.models.Status;
import com.practice.bugtracker.models.Ticket;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicketMapperImplTest {

  private TicketMapper ticketMapper;

  @BeforeEach
  void setUp() {
    ProjectMapperImpl projectMapper = new ProjectMapperImpl(new TeamMapperImpl());
    ticketMapper = new TicketMapperImpl(new PriorityMapperImpl(), new StatusMapperImpl(),
        new TicketTypeMapperImpl(), projectMapper);
  }

  @Test
  void shouldMapTicketToTicketDto() {
    Ticket ticket = TicketBuilder.buildTicket();

    TicketDTO resultDto = ticketMapper.ticketToTicketDto(ticket);

    assertThat(ticket.getTicketId()).isEqualTo(resultDto.getId());
    assertThat(ticket.getDescription()).isEqualTo(resultDto.getDescription());
    assertThat(ticket.getTitle()).isEqualTo(resultDto.getTitle());
    assertThat(ticket.getStatus().getId()).isEqualTo(resultDto.getStatus().getId());
    assertThat(ticket.getPriority().getId()).isEqualTo(resultDto.getPriority().getId());
    assertThat(ticket.getTicketType().getId()).isEqualTo(resultDto.getTicketType().getId());
    assertThat(ticket.getProject().getProjectId()).isEqualTo(resultDto.getProject().getId());
    assertThat(ticket.getCreatedAt()).isEqualTo(resultDto.getCreatedAt());
    assertThat(ticket.getUpdatedAt()).isEqualTo(resultDto.getUpdatedAt());
  }

  @Test
  void shouldMapTicketDtoToTicket() {
    TicketDTO dto = TicketBuilder.buildTicketDto();

    Ticket resultModel = ticketMapper.ticketDtoToTicket(dto);

    assertThat(dto.getId()).isEqualTo(resultModel.getTicketId());
    assertThat(dto.getDescription()).isEqualTo(resultModel.getDescription());
    assertThat(dto.getTitle()).isEqualTo(resultModel.getTitle());
    assertThat(dto.getStatus().getId()).isEqualTo(resultModel.getStatus().getId());
    assertThat(dto.getPriority().getId()).isEqualTo(resultModel.getPriority().getId());
    assertThat(dto.getTicketType().getId()).isEqualTo(resultModel.getTicketType().getId());
    assertThat(dto.getProject().getId()).isEqualTo(resultModel.getProject().getProjectId());
    assertThat(dto.getCreatedAt()).isEqualTo(resultModel.getCreatedAt());
    assertThat(dto.getUpdatedAt()).isEqualTo(resultModel.getUpdatedAt());
  }
}