package com.practice.bugtracker.services.impls;

import com.practice.bugtracker.dtos.ProjectDTO;
import com.practice.bugtracker.dtos.mappers.ProjectMapper;
import com.practice.bugtracker.models.Project;
import com.practice.bugtracker.repositories.ProjectRepository;
import com.practice.bugtracker.services.ProjectService;
import com.practice.bugtracker.validations.exceptions.NotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

  private final ProjectRepository projectRepository;
  private final ProjectMapper projectMapper;

  @Override
  public ProjectDTO create(ProjectDTO projectDTO) {
    return projectMapper.projectToProjectDto(
        projectRepository.save(projectMapper.projectDtoToProject(projectDTO))
    );
  }

  @Override
  public ProjectDTO getById(UUID id) {
    Project foundProject = findProjectById(id);

    return projectMapper.projectToProjectDto(foundProject);
  }

  @Override
  public Boolean deleteById(UUID id) {
    Project foundProject = findProjectById(id);

    projectRepository.deleteById(foundProject.getProjectId());

    return true;
  }

  @Override
  public List<ProjectDTO> getAll() {
    return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
        .map(projectMapper::projectToProjectDto)
        .collect(Collectors.toList());
  }

  private Project findProjectById(UUID id) {
    return projectRepository.findById(id).orElseThrow(NotFoundException::new);
  }
}
