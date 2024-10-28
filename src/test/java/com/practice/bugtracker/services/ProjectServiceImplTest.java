package com.practice.bugtracker.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.practice.bugtracker.dtos.ProjectDTO;
import com.practice.bugtracker.dtos.mappers.impls.ProjectMapperImpl;
import com.practice.bugtracker.dtos.mappers.impls.StatusMapperImpl;
import com.practice.bugtracker.dtos.mappers.impls.TeamMapperImpl;
import com.practice.bugtracker.helpers.ProjectBuilder;
import com.practice.bugtracker.models.Project;
import com.practice.bugtracker.repositories.ProjectRepository;
import com.practice.bugtracker.services.impls.ProjectServiceImpl;
import com.practice.bugtracker.validations.exceptions.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

  @Mock
  ProjectRepository repository;

  @InjectMocks
  ProjectServiceImpl service;

  @Captor
  ArgumentCaptor<UUID> uuidArgumentCaptor;

  @Captor
  ArgumentCaptor<Project> projectArgumentCaptor;

  @BeforeEach
  void setUp() {
    service = new ProjectServiceImpl(repository, new ProjectMapperImpl(new TeamMapperImpl(), new StatusMapperImpl()));
  }

  @Test
  void create() {
    Project projectMock = ProjectBuilder.buildProject();

    when(repository.save(any(Project.class))).thenReturn(projectMock);

    ProjectDTO dto = ProjectBuilder.buildProjectDto();
    dto.setTitle("New title");
    ProjectDTO result = service.create(dto);

    verify(repository).save(projectArgumentCaptor.capture());

    assertThat(dto.getTitle()).isEqualTo(projectArgumentCaptor.getValue().getTitle());
  }

  @Test
  void shouldThrowNotFoundWhenGetById() {
    when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> service.getById(UUID.randomUUID()));
  }

  @Test
  void getById() {
    Project projectMock = ProjectBuilder.buildProject();

    when(repository.findById(any(UUID.class))).thenReturn(Optional.of(projectMock));

    ProjectDTO result = service.getById(projectMock.getProjectId());

    verify(repository).findById(uuidArgumentCaptor.capture());

    assertThat(projectMock.getProjectId()).isEqualTo(uuidArgumentCaptor.getValue());
    assertThat(result.getTitle()).isEqualTo(projectMock.getTitle());
    assertThat(result.getTeam().getId()).isEqualTo(projectMock.getTeam().getId());

  }

  @Test
  void shouldThrowNotFoundExceptionInDeleteById() {
    when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> service.deleteById(UUID.randomUUID()));
  }

  @Test
  void deleteById() {
    Project projectMock = ProjectBuilder.buildProject();

    when(repository.findById(any(UUID.class))).thenReturn(
        Optional.of(projectMock));
    doNothing().when(repository).deleteById(uuidArgumentCaptor.capture());

    Boolean result = service.deleteById(UUID.randomUUID());

    assertThat(uuidArgumentCaptor.getValue()).isEqualTo(projectMock.getProjectId());
    assertThat(result).isTrue();
  }

  @Test
  void getAll() {
    List<Project> projectList = ProjectBuilder.buildProjectList(2);

    when(repository.findAll()).thenReturn(projectList);

    List<ProjectDTO> result = service.getAll();

    assertThat(result).hasSize(2);
  }
}