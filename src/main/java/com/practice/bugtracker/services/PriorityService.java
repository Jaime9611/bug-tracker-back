package com.practice.bugtracker.services;

import com.practice.bugtracker.dtos.PriorityDTO;
import java.util.List;
import java.util.UUID;

public interface PriorityService {

  PriorityDTO create(PriorityDTO priorityDTO);

  PriorityDTO getById(UUID id);

  List<PriorityDTO> getAll();
}
