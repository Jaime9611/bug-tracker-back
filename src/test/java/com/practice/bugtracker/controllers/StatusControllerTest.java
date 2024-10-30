package com.practice.bugtracker.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.bugtracker.dtos.StatusDTO;
import com.practice.bugtracker.dtos.mappers.StatusMapper;
import com.practice.bugtracker.dtos.mappers.impls.StatusMapperImpl;
import com.practice.bugtracker.helpers.StatusBuilder;
import com.practice.bugtracker.repositories.StatusRepository;
import com.practice.bugtracker.services.StatusService;
import com.practice.bugtracker.services.impls.StatusServiceImpl;
import com.practice.bugtracker.utils.constants.Endpoints;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(StatusController.class)
class StatusControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  StatusRepository repository;

  @MockBean
  StatusService statusService;

  StatusServiceImpl statusServiceImpl;

  @BeforeEach
  void setUp() {
    StatusMapper mapper = new StatusMapperImpl();
    statusServiceImpl = new StatusServiceImpl(repository, mapper);
  }

  @Test
  void shouldCreateStatus() throws Exception {
    StatusDTO testStatus = StatusBuilder.buildStatusDto();

    given(statusService.create(any(StatusDTO.class))).willReturn(testStatus);

    mockMvc.perform(post(Endpoints.STATUS).accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(testStatus))).andExpect(status().isCreated())
        .andExpect(header().exists("Location"));
  }

}