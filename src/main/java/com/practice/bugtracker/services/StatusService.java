package com.practice.bugtracker.services;

import com.practice.bugtracker.dtos.StatusDTO;
import java.util.List;
import java.util.UUID;

public interface StatusService {

  StatusDTO getStatusById(UUID id);

  List<StatusDTO> getAll();

  StatusDTO create(StatusDTO status);

  void updateById(UUID id, StatusDTO status);

  Boolean deleteById(UUID id);
}
