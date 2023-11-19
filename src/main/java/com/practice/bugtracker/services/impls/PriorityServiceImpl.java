package com.practice.bugtracker.services.impls;

import com.practice.bugtracker.dtos.PriorityDTO;
import com.practice.bugtracker.dtos.mappers.PriorityMapper;
import com.practice.bugtracker.models.Priority;
import com.practice.bugtracker.repositories.PriorityRepository;
import com.practice.bugtracker.services.PriorityService;
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
public class PriorityServiceImpl implements PriorityService {

  private final PriorityRepository priorityRepository;
  private final PriorityMapper priorityMapper;

  @Override
  public PriorityDTO create(PriorityDTO priorityDTO) {
    return priorityMapper.priorityToPriorityDto(
        priorityRepository.save(priorityMapper.priorityDtoToPriority(priorityDTO))
    );
  }

  @Override
  public PriorityDTO getById(UUID id) {
    Priority foundedPriority = priorityRepository.findById(id).orElseThrow(
        NotFoundException::new);

    return priorityMapper.priorityToPriorityDto(foundedPriority);
  }

  @Override
  public List<PriorityDTO> getAll() {
    return StreamSupport.stream(priorityRepository.findAll().spliterator(), false)
        .map(priority -> priorityMapper.priorityToPriorityDto(priority)).collect(
            Collectors.toList());
  }
}
