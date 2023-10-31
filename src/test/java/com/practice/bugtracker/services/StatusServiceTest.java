package com.practice.bugtracker.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import com.practice.bugtracker.dtos.StatusDTO;
import com.practice.bugtracker.dtos.mappers.impls.StatusMapperImpl;
import com.practice.bugtracker.helpers.StatusBuilder;
import com.practice.bugtracker.models.Status;
import com.practice.bugtracker.repositories.StatusRepository;
import com.practice.bugtracker.services.impls.StatusServiceImpl;
import com.practice.bugtracker.validations.exceptions.NotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StatusServiceTest {

  @Mock
  StatusRepository statusRepository;

  @InjectMocks
  StatusServiceImpl statusService;

  @BeforeEach
  void setUp() {
    statusService = new StatusServiceImpl(statusRepository, new StatusMapperImpl());
  }

  @Test
  void shouldReturnEmptyList() {
    when(statusRepository.findAll()).thenReturn(Collections.emptyList());

    List<StatusDTO> statusList = statusService.getAll();

    assertThat(statusList.size()).isEqualTo(0);
  }

  @Test
  void shouldReturnStatusList() {
    List<Status> mockList = StatusBuilder.buildStatusList(3);

    when(statusRepository.findAll()).thenReturn(mockList);

    List<StatusDTO> statusList = statusService.getAll();

    assertThat(statusList.size()).isEqualTo(3);
  }

  @Test
  void shouldNotFoundExceptionWhenGettingUnExistingStatusId() throws Exception {
    given(statusRepository.findById(any(UUID.class))).willReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> {
      statusService.getStatusById(UUID.randomUUID());
    });
  }

  @Test
  void shouldReturnStatusDtoWhenGettingStatusById() {
    Status mockStatus = StatusBuilder.buildStatus();

    when(statusRepository.findById(any(UUID.class))).thenReturn(Optional.of(mockStatus));

    StatusDTO result = statusService.getStatusById(mockStatus.getId());

    assertThat(mockStatus.getId()).isEqualTo(result.getId());
  }
}