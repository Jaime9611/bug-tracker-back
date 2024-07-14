package com.practice.bugtracker.controllers;

import com.practice.bugtracker.dtos.ProjectDTO;
import com.practice.bugtracker.services.ProjectService;
import com.practice.bugtracker.utils.constants.Endpoints;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(Endpoints.PROJECT)
public class ProjectController {

  private final ProjectService projectService;

  @GetMapping()
  public ResponseEntity<List<ProjectDTO>> getAll() {
    log.info("GET - All Projects");
    return ResponseEntity.ok(projectService.getAll());
  }

  @GetMapping(Endpoints.PROJECT_ID)
  public ResponseEntity<ProjectDTO> getById(@PathVariable UUID id) {
    log.debug("GET - Project by ID: " + id);
    return ResponseEntity.ok(projectService.getById(id));
  }
}
