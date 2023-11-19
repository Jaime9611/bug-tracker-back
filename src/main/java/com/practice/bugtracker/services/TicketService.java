package com.practice.bugtracker.services;

import com.practice.bugtracker.dtos.TicketDTO;
import java.util.List;
import java.util.UUID;

public interface TicketService {

  TicketDTO create(TicketDTO ticketDTO);

  TicketDTO getById(UUID id);

  Boolean deleteById(UUID id);

  List<TicketDTO> getAll();
}
