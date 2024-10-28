package com.practice.bugtracker.services.impls;

import com.practice.bugtracker.dtos.PriorityDTO;
import com.practice.bugtracker.dtos.mappers.PriorityMapper;
import com.practice.bugtracker.models.Priority;
import com.practice.bugtracker.repositories.PriorityRepository;
import com.practice.bugtracker.services.PriorityService;
import com.practice.bugtracker.validations.exceptions.NotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriorityServiceImpl implements PriorityService {

  private final PriorityRepository priorityRepository;
  private final PriorityMapper priorityMapper;

  @Override
  public PriorityDTO create(PriorityDTO priorityDTO) {
    log.debug(String.format("%s - create was called with priority: %s", PriorityService.class.getName(), priorityDTO.getTitle()));

    return priorityMapper.priorityToPriorityDto(
        priorityRepository.save(priorityMapper.priorityDtoToPriority(priorityDTO))
    );
  }

  @Override
  public PriorityDTO getById(UUID id) {
    log.debug(String.format("%s - getById was called with Id: %s.", PriorityService.class.getName(), id));

    Priority foundPriority = priorityRepository.findById(id).orElseThrow(
        NotFoundException::new);

    return priorityMapper.priorityToPriorityDto(foundPriority);
  }

  @Override
  public List<PriorityDTO> getAll() {
    log.debug(String.format("%s - getAll was called.", PriorityService.class.getName()));

    return StreamSupport.stream(priorityRepository.findAll().spliterator(), false)
        .map(priorityMapper::priorityToPriorityDto)
        .collect(Collectors.toList());
  }
}
