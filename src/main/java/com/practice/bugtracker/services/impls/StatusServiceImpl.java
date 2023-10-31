package com.practice.bugtracker.services.impls;

import com.practice.bugtracker.dtos.StatusDTO;
import com.practice.bugtracker.dtos.mappers.StatusMapper;
import com.practice.bugtracker.repositories.StatusRepository;
import com.practice.bugtracker.services.StatusService;
import com.practice.bugtracker.validations.exceptions.NotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

  private final StatusRepository statusRepository;

  private final StatusMapper statusMapper;

  @Override
  public StatusDTO getStatusById(UUID id) {
    return statusMapper.statusToStatusDto(statusRepository.findById(id).orElseThrow(
        NotFoundException::new));
  }

  @Override
  public List<StatusDTO> getAll() {
    return StreamSupport.stream(statusRepository.findAll().spliterator(),false)
        .map(statusMapper::statusToStatusDto)
        .collect(Collectors.toList());
  }
}