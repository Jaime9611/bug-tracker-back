package com.practice.bugtracker.dtos.mappers.impls;

import com.practice.bugtracker.dtos.PriorityDTO;
import com.practice.bugtracker.dtos.PriorityDTO.PriorityDTOBuilder;
import com.practice.bugtracker.dtos.StatusDTO;
import com.practice.bugtracker.dtos.mappers.StatusMapper;
import com.practice.bugtracker.models.Priority;
import com.practice.bugtracker.models.Status;
import org.springframework.stereotype.Component;

@Component
public class StatusMapperImpl implements StatusMapper {

  @Override
  public Status statusDtoToStatus(StatusDTO dto) {
    if (dto == null) {
      return null;
    }

    Status.StatusBuilder status = Status.builder();

    status.id(dto.getId());
    status.title(dto.getTitle());


    return status.build();
  }

  @Override
  public StatusDTO statusToStatusDto(Status status) {
    if (status == null) {
      return null;
    }

    StatusDTO.StatusDTOBuilder dto = StatusDTO.builder();

    dto.id(status.getId());
    dto.title(status.getTitle());

    return dto.build();
  }
}
