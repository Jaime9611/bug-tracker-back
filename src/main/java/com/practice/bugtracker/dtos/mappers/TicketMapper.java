package com.practice.bugtracker.dtos.mappers;

import com.practice.bugtracker.dtos.TicketDTO;
import com.practice.bugtracker.models.Ticket;

public interface TicketMapper {

  Ticket ticketDtoToTicket(TicketDTO dto);

  TicketDTO ticketToTicketDto(Ticket issue);
}
