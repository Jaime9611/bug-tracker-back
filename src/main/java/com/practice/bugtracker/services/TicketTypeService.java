package com.practice.bugtracker.services;

import com.practice.bugtracker.dtos.TicketTypeDTO;
import java.util.List;
import java.util.UUID;

public interface TicketTypeService {

  TicketTypeDTO create(TicketTypeDTO ticketTypeDTO);

  TicketTypeDTO getById(UUID id);

  List<TicketTypeDTO> getAll();
}
