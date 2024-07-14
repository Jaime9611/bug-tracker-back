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
import java.util.List;
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
    loadStatusData();
    loadProjectData();
    loadTicketData();
  }

  private void loadStatusData() {
    if (statusRepository.count() == 0) {
      Status status1 = Status.builder()
          .title("OPEN")
          .build();
      Status status2 = Status.builder()
          .title("IN_PROGRESS")
          .build();
      Status status3 = Status.builder()
          .title("DONE")
          .build();
      Status status4 = Status.builder()
          .title("DROPPED")
          .build();

      statusRepository.save(status1);
      statusRepository.save(status2);
      statusRepository.save(status3);
      statusRepository.save(status4);
    }
  }

  private void loadTicketData() {
    if (ticketRepository.count() == 0) {
      Project project = projectRepository.findAll().get(0);

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
          .status(statusRepository.findByTitle("OPEN"))
          .ticketType(backendType)
          .build();

      Ticket ticket2 = Ticket.builder()
          .ticketId(UUID.randomUUID())
          .title("Create Frontend project")
          .description("Ticket 2 desc")
          .description("Ticket 2 descp")
          .project(project)
          .priority(mediumPriority)
          .status(statusRepository.findByTitle("IN_PROGRESS"))
          .ticketType(frontendType)
          .build();

      Ticket ticket3 = Ticket.builder()
          .ticketId(UUID.randomUUID())
          .title("Create Logo for the project")
          .description("Ticket 3 desc")
          .description("Ticket 3 descp")
          .project(project)
          .priority(highPriority)
          .status(statusRepository.findByTitle("DONE"))
          .ticketType(frontendType)
          .build();

      ticketRepository.saveAll(Arrays.asList(ticket1, ticket2, ticket3));
    }
  }

  private void loadProjectData() {
    if (projectRepository.count() == 0) {
      Team team1 = Team.builder()
          .title("AvengersCode")
          .build();

      Team team2 = Team.builder()
          .title("Akatsuki")
          .build();

      Team team3 = Team.builder()
          .title("SpiceTeam")
          .build();

      Team savedTeam1 = teamRepository.save(team1);
      Team savedTeam2 = teamRepository.save(team2);
      Team savedTeam3 = teamRepository.save(team3);

      Project project1 = Project.builder()
          .title("BugTracker")
          .team(savedTeam1)
          .startsAt(LocalDateTime.now())
          .endsAt(LocalDateTime.now().plusMonths(1))
          .status(statusRepository.findByTitle("IN_PROGRESS"))
          .build();

      Project project2 = Project.builder()
          .title("MyDoctorChecker")
          .team(savedTeam2)
          .startsAt(LocalDateTime.now())
          .endsAt(LocalDateTime.now().plusMonths(1))
          .status(statusRepository.findByTitle("DROPPED"))
          .build();

      Project project3 = Project.builder()
          .title("MyEnglishApp")
          .team(savedTeam3)
          .startsAt(LocalDateTime.now())
          .endsAt(LocalDateTime.now().plusMonths(1))
          .status(statusRepository.findByTitle("OPEN"))
          .build();

      projectRepository.saveAll(List.of(project1, project2, project3));
    }

  }
}
