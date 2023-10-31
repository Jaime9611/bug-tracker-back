package com.practice.bugtracker.bootstrap;

import com.practice.bugtracker.models.Priority;
import com.practice.bugtracker.models.Project;
import com.practice.bugtracker.models.Status;
import com.practice.bugtracker.models.Team;
import com.practice.bugtracker.models.Ticket;
import com.practice.bugtracker.models.TicketType;
import com.practice.bugtracker.repositories.PriorityRepository;
import com.practice.bugtracker.repositories.ProjectRepository;
import com.practice.bugtracker.repositories.StatusRepository;
import com.practice.bugtracker.repositories.TeamRepository;
import com.practice.bugtracker.repositories.TicketRepository;
import com.practice.bugtracker.repositories.TicketTypeRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

  private final TicketRepository ticketRepository;
  private final TeamRepository teamRepository;
  private final ProjectRepository projectRepository;
  private final StatusRepository statusRepository;
  private final PriorityRepository priorityRepository;
  private final TicketTypeRepository ticketTypeRepository;

  @Override
  public void run(String... args) throws Exception {
    loadProjectData();
    loadTicketData();
  }

  private void loadTicketData() {
    if (ticketRepository.count() == 0) {
      Project project = projectRepository.findAll().get(0);

      Status status1 = Status.builder()
          .title("OPEN")
          .build();
      Status status2 = Status.builder()
          .title("IN_PROGRESS")
          .build();
      Status status3 = Status.builder()
          .title("DONE")
          .build();

      Status openStatus = statusRepository.save(status1);
      Status inProgressStatus = statusRepository.save(status2);
      Status doneStatus = statusRepository.save(status3);


      Priority priority1 = Priority.builder()
          .title("LOW")
          .build();
      Priority priority2 = Priority.builder()
          .title("MEDIUM")
          .build();
      Priority priority3 = Priority.builder()
          .title("HIGH")
          .build();

      Priority lowPriority = priorityRepository.save(priority1);
      Priority mediumPriority = priorityRepository.save(priority2);
      Priority highPriority = priorityRepository.save(priority3);

      TicketType type1 = TicketType.builder()
          .title("BACKEND")
          .build();
      TicketType type2 = TicketType.builder()
          .title("FRONTEND")
          .build();

      TicketType backendType = ticketTypeRepository.save(type1);
      TicketType frontendType = ticketTypeRepository.save(type2);

      Ticket ticket1 = Ticket.builder()
          .ticketId(UUID.randomUUID())
          .title("Create Backend project")
          .description("Ticket 1 desc")
          .description("Ticket 1 descp")
          .project(project)
          .priority(lowPriority)
          .status(openStatus)
          .ticketType(backendType)
          .build();

      Ticket ticket2 = Ticket.builder()
          .ticketId(UUID.randomUUID())
          .title("Create Frontend project")
          .description("Ticket 2 desc")
          .description("Ticket 2 descp")
          .project(project)
          .priority(mediumPriority)
          .status(inProgressStatus)
          .ticketType(frontendType)
          .build();

      Ticket ticket3 = Ticket.builder()
          .ticketId(UUID.randomUUID())
          .title("Create Logo for the project")
          .description("Ticket 3 desc")
          .description("Ticket 3 descp")
          .project(project)
          .priority(highPriority)
          .status(doneStatus)
          .ticketType(frontendType)
          .build();

      ticketRepository.saveAll(Arrays.asList(ticket1, ticket2, ticket3));
    }
  }

  private void loadProjectData() {
    if (projectRepository.count() == 0) {
      Team team = Team.builder()
          .title("AvengersCode")
          .build();

      Team savedTeam = teamRepository.save(team);

      Project project = Project.builder()
          .title("BugTracker")
          .team(savedTeam)
          .startsAt(LocalDateTime.now())
          .endsAt(LocalDateTime.now().plusMonths(1))
          .build();

      projectRepository.save(project);
    }

  }
}
