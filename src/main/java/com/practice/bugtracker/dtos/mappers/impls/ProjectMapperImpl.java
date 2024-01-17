package com.practice.bugtracker.dtos.mappers.impls;

import com.practice.bugtracker.dtos.ProjectDTO;
import com.practice.bugtracker.dtos.TicketDTO;
import com.practice.bugtracker.dtos.TicketResponseDTO;
import com.practice.bugtracker.dtos.mappers.ProjectMapper;
import com.practice.bugtracker.dtos.mappers.TeamMapper;
import com.practice.bugtracker.dtos.mappers.TicketMapper;
import com.practice.bugtracker.models.Project;
import com.practice.bugtracker.models.Ticket;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectMapperImpl implements ProjectMapper {

  private final TeamMapper teamMapper;

  @Override
  public Project projectDtoToProject(ProjectDTO dto) {
    if (dto == null) {
      return null;
    }

    Project.ProjectBuilder project = Project.builder();
    project.projectId(dto.getId());
    project.title(dto.getTitle());
    project.startsAt(dto.getStartsAt());
    project.endsAt(dto.getEndsAt());
    project.team(teamMapper.teamDtoToTeam(dto.getTeam()));

    return project.build();

  }

  @Override
  public ProjectDTO projectToProjectDto(Project project) {

    if (project == null) {
      return null;
    }

    ProjectDTO.ProjectDTOBuilder dto = ProjectDTO.builder();

    dto.id(project.getProjectId());
    dto.title(project.getTitle());
    dto.startsAt(project.getStartsAt());
    dto.endsAt(project.getEndsAt());
    dto.team(teamMapper.teamToTeamDto(project.getTeam()));

    List<TicketResponseDTO> ticketList = project.getTickets().stream()
        .map(ticket -> TicketResponseDTO.builder().ticketId(ticket.getTicketId()).title(
            ticket.getTitle()).build()).toList();

    dto.tickets(ticketList);

    return dto.build();
  }
}
