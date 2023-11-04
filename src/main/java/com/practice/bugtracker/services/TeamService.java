package com.practice.bugtracker.services;

import com.practice.bugtracker.dtos.TeamDTO;
import java.util.List;
import java.util.UUID;

public interface TeamService {

  TeamDTO create(TeamDTO teamDTO);

  TeamDTO getById(UUID id);

  Boolean deleteById(UUID id);

  void updateById(UUID id, TeamDTO teamDTO);

  List<TeamDTO> getAll();
}
