package com.practice.bugtracker.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import com.practice.bugtracker.dtos.StatusDTO;
import com.practice.bugtracker.helpers.StatusBuilder;
import com.practice.bugtracker.models.Status;
import com.practice.bugtracker.repositories.StatusRepository;
import jakarta.transaction.Transactional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class StatusControllerIT {

  @Autowired
  StatusController statusController;

  @Autowired
  StatusRepository statusRepository;

  @Rollback
  @Transactional
  @Test
  void shouldCreateStatusOnDB() {
    StatusDTO dto = StatusBuilder.buildStatusDto();

    ResponseEntity<?> response = statusController.createStatus(dto);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(201));
    assertThat(response.getHeaders().getLocation()).isNotNull();

    String[] location = response.getHeaders().getLocation().getPath().split("/");
    UUID savedId = UUID.fromString(location[4]);

    Status savedStatus = statusRepository.findById(savedId).orElse(null);
    assertThat(savedStatus).isNotNull();
  }

  @Test
  void shouldReturnStatusById() {
    Status status = statusRepository.findAll().iterator().next();

    StatusDTO result = statusController.getStatusById(status.getId()).getBody();

    assertThat(result).isNotNull();
    assertThat(status.getId()).isEqualTo(result.getId());
  }
}