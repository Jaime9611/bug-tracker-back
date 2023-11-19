package com.practice.bugtracker.services.impls;

import com.practice.bugtracker.dtos.TeamDTO;
import com.practice.bugtracker.dtos.mappers.TeamMapper;
import com.practice.bugtracker.models.Team;
import com.practice.bugtracker.repositories.TeamRepository;
import com.practice.bugtracker.services.TeamService;
import com.practice.bugtracker.validations.exceptions.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

  private final TeamRepository teamRepository;
  private final TeamMapper teamMapper;

  @Override
  public TeamDTO create(TeamDTO teamDTO) {
    return teamMapper.teamToTeamDto(teamRepository.save(teamMapper.teamDtoToTeam(teamDTO)));
  }

  @Override
  public TeamDTO getById(UUID id) {
    return teamMapper.teamToTeamDto(findTeamById(id));
  }

  @Override
  public Boolean deleteById(UUID id) {
    Team foundedTeam = findTeamById(id);

    teamRepository.deleteById(foundedTeam.getId());

    return true;
  }

  @Override
  public void updateById(UUID id, TeamDTO teamDTO) {
    Team foundedTeam = findTeamById(id);

    foundedTeam.setTitle(teamDTO.getTitle());

    teamRepository.save(foundedTeam);
  }

  @Override
  public List<TeamDTO> getAll() {
    return StreamSupport.stream(teamRepository.findAll().spliterator(), false).map(team -> teamMapper.teamToTeamDto(team)).collect(
        Collectors.toList());
  }

  private Team findTeamById(UUID id) {
    return teamRepository.findById(id).orElseThrow(NotFoundException::new);
  }
}
