package com.practice.bugtracker.dtos.mappers;

import com.practice.bugtracker.dtos.TicketTypeDTO;
import com.practice.bugtracker.models.TicketType;

public interface TicketTypeMapper {

  TicketTypeDTO ticketTypeToTicketTypeDto(TicketType ticketType);

  TicketType ticketTypeDTOToTicketType(TicketTypeDTO dto);

}
