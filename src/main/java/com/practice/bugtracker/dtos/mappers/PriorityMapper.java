package com.practice.bugtracker.dtos.mappers;

import com.practice.bugtracker.dtos.PriorityDTO;
import com.practice.bugtracker.models.Priority;

public interface PriorityMapper {

  PriorityDTO priorityToPriorityDto(Priority priority);

  Priority priorityDtoToPriority(PriorityDTO dto);
}
