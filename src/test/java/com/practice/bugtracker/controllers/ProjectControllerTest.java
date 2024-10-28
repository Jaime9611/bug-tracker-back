package com.practice.bugtracker.controllers;

import com.practice.bugtracker.services.ProjectService;
import com.practice.bugtracker.utils.constants.Endpoints;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  ProjectService projectService;

  @Test
  void shouldGetProjectById() throws Exception {
    mockMvc.perform(get(Endpoints.PROJECT + "/" + UUID.randomUUID())
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

  }
}