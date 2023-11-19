package com.practice.bugtracker.services;

import com.practice.bugtracker.dtos.ProjectDTO;
import java.util.List;
import java.util.UUID;

public interface ProjectService {

  ProjectDTO create(ProjectDTO projectDTO);

  ProjectDTO getById(UUID id);

  Boolean deleteById(UUID id);

  List<ProjectDTO> getAll();
}
