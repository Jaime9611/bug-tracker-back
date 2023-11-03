package com.practice.bugtracker.services.impls;

import com.practice.bugtracker.dtos.StatusDTO;
import com.practice.bugtracker.dtos.mappers.StatusMapper;
import com.practice.bugtracker.models.Status;
import com.practice.bugtracker.repositories.StatusRepository;
import com.practice.bugtracker.services.StatusService;
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
    return StreamSupport.stream(statusRepository.findAll().spliterator(), false)
        .map(statusMapper::statusToStatusDto)
        .collect(Collectors.toList());
  }

  @Override
  public StatusDTO create(StatusDTO status) {
    return statusMapper.statusToStatusDto(
        statusRepository.save(statusMapper.statusDtoToStatus(status)));
  }

  @Override
  public void updateById(UUID id, StatusDTO status) {
    Optional<Status> existingStatus = statusRepository.findById(id);

    if (existingStatus.isPresent()) {
      Status updatedStatus = existingStatus.get();
      updatedStatus.setTitle(status.getTitle());

      statusRepository.save(updatedStatus);
    } else {
      throw new NotFoundException(String.format("Status with id: %s doest not exists.", id));
    }
  }

  @Override
  public Boolean deleteById(UUID id) {
    Optional<Status> foundStatus = statusRepository.findById(id);

    if(foundStatus.isPresent()) {
      statusRepository.deleteById(id);

      return true;
    } else {
      throw new NotFoundException(String.format("Status with id: %s doest not exists.", id));
    }
  }
}
