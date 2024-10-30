package com.practice.bugtracker.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.bugtracker.dtos.ProjectDTO;
import com.practice.bugtracker.dtos.mappers.ProjectMapper;
import com.practice.bugtracker.dtos.mappers.StatusMapper;
import com.practice.bugtracker.dtos.mappers.TeamMapper;
import com.practice.bugtracker.dtos.mappers.impls.ProjectMapperImpl;
import com.practice.bugtracker.dtos.mappers.impls.StatusMapperImpl;
import com.practice.bugtracker.dtos.mappers.impls.TeamMapperImpl;
import com.practice.bugtracker.helpers.ProjectBuilder;
import com.practice.bugtracker.repositories.ProjectRepository;
import com.practice.bugtracker.services.ProjectService;
import com.practice.bugtracker.services.impls.ProjectServiceImpl;
import com.practice.bugtracker.utils.constants.Endpoints;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

  public static final int LIST_SIZE = 3;

  @Autowired
  MockMvc mockMvc;

  @MockBean
  ProjectService projectService;

  @MockBean
  ProjectRepository repository;

  ProjectServiceImpl projectServiceImpl;

  @Autowired
  ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    TeamMapper teamMapper = new TeamMapperImpl();
    StatusMapper statusMapper = new StatusMapperImpl();
    ProjectMapper projectMapper = new ProjectMapperImpl(teamMapper, statusMapper);
    projectServiceImpl = new ProjectServiceImpl(repository, projectMapper);
  }

  @Test
  void shouldGetProjectList() throws Exception {
    List<ProjectDTO> testProjects = ProjectBuilder.buildProjectDTOList(LIST_SIZE);

    given(projectService.getAll()).willReturn(testProjects);

    mockMvc.perform(get(Endpoints.PROJECT).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()", is(LIST_SIZE)));
  }

  @Test
  void shouldGetProjectById() throws Exception {

    ProjectDTO testProjectDto = ProjectBuilder.buildProjectDto();

    given(projectService.getById(testProjectDto.getId())).willReturn(testProjectDto);

    mockMvc.perform(get(Endpoints.PROJECT + "/" + testProjectDto.getId())
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(testProjectDto.getId().toString())))
        .andExpect(jsonPath("$.title", is(testProjectDto.getTitle().toString())));

  }
}