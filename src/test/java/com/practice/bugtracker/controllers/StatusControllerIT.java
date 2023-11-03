package com.practice.bugtracker.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import com.practice.bugtracker.dtos.StatusDTO;
import com.practice.bugtracker.models.Status;
import com.practice.bugtracker.repositories.StatusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StatusControllerIT {

  @Autowired
  StatusController statusController;

  @Autowired
  StatusRepository statusRepository;

  @Test
  void shouldReturnStatusById() {
    Status status = statusRepository.findAll().iterator().next();
    System.out.println(status.getId());
    StatusDTO result = statusController.getStatusById(status.getId()).getBody();

    assertThat(result).isNotNull();
    assertThat(status.getId()).isEqualTo(result.getId());
  }
}