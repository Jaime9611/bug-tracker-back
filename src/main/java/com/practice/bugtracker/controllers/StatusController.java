package com.practice.bugtracker.controllers;

import com.practice.bugtracker.dtos.StatusDTO;
import com.practice.bugtracker.services.StatusService;
import com.practice.bugtracker.utils.constants.Endpoints;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoints.STATUS)
public class StatusController {

  private final StatusService statusService;

  @PostMapping
  public ResponseEntity<?> createStatus(@RequestBody StatusDTO statusDTO) {

    log.debug("POST - Create status in Controller");

    StatusDTO savedStatus = statusService.create(statusDTO);

    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", Endpoints.STATUS + "/" + savedStatus.getId().toString());

    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  @GetMapping(Endpoints.STATUS_ID)
  public ResponseEntity<StatusDTO> getStatusById(@PathVariable UUID id) {
    log.debug("GET - Get status by id: " + id + "in Controller");

    return ResponseEntity.ok(statusService.getStatusById(id));
  }
}
