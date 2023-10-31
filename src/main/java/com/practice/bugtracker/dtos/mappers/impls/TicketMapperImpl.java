package com.practice.bugtracker.dtos.mappers.impls;

import com.practice.bugtracker.dtos.TicketDTO;
import com.practice.bugtracker.dtos.mappers.PriorityMapper;
import com.practice.bugtracker.dtos.mappers.ProjectMapper;
import com.practice.bugtracker.dtos.mappers.StatusMapper;
import com.practice.bugtracker.dtos.mappers.TicketMapper;
import com.practice.bugtracker.dtos.mappers.TicketTypeMapper;
import com.practice.bugtracker.models.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketMapperImpl implements TicketMapper {

  private final PriorityMapper priorityMapper;
  private final StatusMapper statusMapper;
  private final TicketTypeMapper ticketTypeMapper;
  private final ProjectMapper projectMapper;

  @Override
  public Ticket ticketDtoToTicket(TicketDTO dto) {
    if (dto == null) {
      return null;
    }

    Ticket.TicketBuilder ticket = Ticket.builder();

    ticket.ticketId(dto.getId());
    ticket.title(dto.getTitle());
    ticket.description(dto.getDescription());
    ticket.createdAt(dto.getCreatedAt());
    ticket.updatedAt(dto.getUpdatedAt());
    ticket.priority(priorityMapper.priorityDtoToPriority(dto.getPriority()));
    ticket.status(statusMapper.statusDtoToStatus(dto.getStatus()));
    ticket.ticketType(ticketTypeMapper.ticketTypeDTOToTicketType(dto.getTicketType()));
    ticket.project(projectMapper.projectDtoToProject(dto.getProject()));

    return ticket.build();
  }

  @Override
  public TicketDTO ticketToTicketDto(Ticket ticket) {
    if (ticket == null) {
      return null;
    }

    TicketDTO.TicketDTOBuilder dto = TicketDTO.builder();

    dto.id(ticket.getTicketId());
    dto.title(ticket.getTitle());
    dto.description(ticket.getDescription());
    dto.createdAt(ticket.getCreatedAt());
    dto.updatedAt(ticket.getUpdatedAt());
    dto.priority(priorityMapper.priorityToPriorityDto(ticket.getPriority()));
    dto.status(statusMapper.statusToStatusDto(ticket.getStatus()));
    dto.ticketType(ticketTypeMapper.ticketTypeToTicketTypeDto(ticket.getTicketType()));
    dto.project(projectMapper.projectToProjectDto(ticket.getProject()));

    return dto.build();
  }
}
