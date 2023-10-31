package com.practice.bugtracker.controllers;

import com.practice.bugtracker.dtos.StatusDTO;
import com.practice.bugtracker.services.StatusService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatusController {

  private final StatusService statusService;

  @GetMapping("/{id}")
  public ResponseEntity<StatusDTO> getStatusById(@PathVariable UUID id) {
    return ResponseEntity.ok(statusService.getStatusById(id));
  }

}
