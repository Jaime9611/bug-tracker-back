package com.practice.bugtracker.dtos.mappers.impls;

import com.practice.bugtracker.dtos.PriorityDTO;
import com.practice.bugtracker.dtos.TicketDTO;
import com.practice.bugtracker.dtos.mappers.PriorityMapper;
import com.practice.bugtracker.models.Priority;
import org.springframework.stereotype.Component;

@Component
public class PriorityMapperImpl implements PriorityMapper {

  @Override
  public PriorityDTO priorityToPriorityDto(Priority priority) {
    if (priority == null) {
      return null;
    }

    PriorityDTO.PriorityDTOBuilder dto = PriorityDTO.builder();

    dto.id(priority.getId());
    dto.title(priority.getTitle());

    return dto.build();
  }

  @Override
  public Priority priorityDtoToPriority(PriorityDTO dto) {
    if (dto == null) {
      return null;
    }

    Priority.PriorityBuilder priority = Priority.builder();

    priority.id(dto.getId());
    priority.title(dto.getTitle());


    return priority.build();
  }

}
