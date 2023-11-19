package com.practice.bugtracker.services.impls;

import com.practice.bugtracker.dtos.TicketDTO;
import com.practice.bugtracker.dtos.mappers.TicketMapper;
import com.practice.bugtracker.models.Ticket;
import com.practice.bugtracker.repositories.TicketRepository;
import com.practice.bugtracker.services.TicketService;
import com.practice.bugtracker.validations.exceptions.NotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

  private final TicketRepository ticketRepository;
  private final TicketMapper ticketMapper;

  @Override
  public TicketDTO create(TicketDTO ticketDTO) {
    return ticketMapper.ticketToTicketDto(
        ticketRepository.save(ticketMapper.ticketDtoToTicket(ticketDTO)));
  }

  @Override
  public TicketDTO getById(UUID id) {
    Ticket foundTicket = findTicketById(id);

    return ticketMapper.ticketToTicketDto(foundTicket);
  }

  @Override
  public Boolean deleteById(UUID id) {
    Ticket foundTicket = findTicketById(id);

    ticketRepository.deleteById(foundTicket.getTicketId());

    return true;
  }

  @Override
  public List<TicketDTO> getAll() {
    return StreamSupport.stream(ticketRepository.findAll().spliterator(), false)
        .map(ticketMapper::ticketToTicketDto)
        .collect(Collectors.toList());
  }

  private Ticket findTicketById(UUID id) {
    return ticketRepository.findById(id).orElseThrow(NotFoundException::new);
  }
}
