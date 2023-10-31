package com.practice.bugtracker.dtos.mappers.impls;

import com.practice.bugtracker.dtos.TeamDTO;
import com.practice.bugtracker.dtos.TicketTypeDTO;
import com.practice.bugtracker.dtos.mappers.TicketTypeMapper;
import com.practice.bugtracker.models.Team;
import com.practice.bugtracker.models.TicketType;
import org.springframework.stereotype.Component;

@Component
public class TicketTypeMapperImpl implements TicketTypeMapper {

  @Override
  public TicketTypeDTO ticketTypeToTicketTypeDto(TicketType ticketType) {
    if (ticketType == null) {
      return null;
    }

    TicketTypeDTO.TicketTypeDTOBuilder dto = TicketTypeDTO.builder();

    dto.id(ticketType.getId());
    dto.title(ticketType.getTitle());

    return dto.build();
  }

  @Override
  public TicketType ticketTypeDTOToTicketType(TicketTypeDTO dto) {
    if (dto == null) {
      return null;
    }

    TicketType.TicketTypeBuilder ticketType = TicketType.builder();

    ticketType.id(dto.getId());
    ticketType.title(dto.getTitle());

    return ticketType.build();
  }
}
