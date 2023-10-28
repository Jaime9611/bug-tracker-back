package com.practice.bugtracker.dtos.mappers;

import com.practice.bugtracker.dtos.ProjectDTO;
import com.practice.bugtracker.models.Project;

public interface ProjectMapper {

  Project projectDtoToProject(ProjectDTO dto);

  ProjectDTO projectToProjectDto(Project project);
}
