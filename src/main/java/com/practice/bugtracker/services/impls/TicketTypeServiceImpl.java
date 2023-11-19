package com.practice.bugtracker.services.impls;

import com.practice.bugtracker.dtos.TicketTypeDTO;
import com.practice.bugtracker.dtos.mappers.TicketTypeMapper;
import com.practice.bugtracker.models.TicketType;
import com.practice.bugtracker.repositories.TicketTypeRepository;
import com.practice.bugtracker.services.TicketTypeService;
import com.practice.bugtracker.validations.exceptions.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketTypeServiceImpl implements TicketTypeService {

  private final TicketTypeRepository ticketTypeRepository;
  private final TicketTypeMapper ticketTypeMapper;

  @Override
  public TicketTypeDTO create(TicketTypeDTO ticketTypeDTO) {
    return ticketTypeMapper.ticketTypeToTicketTypeDto(
        ticketTypeRepository.save(ticketTypeMapper.ticketTypeDTOToTicketType(ticketTypeDTO))
    );
  }

  @Override
  public TicketTypeDTO getById(UUID id) {
    TicketType foundTicketType = ticketTypeRepository.findById(id)
        .orElseThrow(NotFoundException::new);

    return ticketTypeMapper.ticketTypeToTicketTypeDto(foundTicketType);
  }

  @Override
  public List<TicketTypeDTO> getAll() {
    return StreamSupport.stream(ticketTypeRepository.findAll().spliterator(), false)
        .map(ticketTypeMapper::ticketTypeToTicketTypeDto)
        .collect(Collectors.toList());
  }
}
