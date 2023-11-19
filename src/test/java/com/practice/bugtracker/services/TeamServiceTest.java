package com.practice.bugtracker.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.practice.bugtracker.dtos.TeamDTO;
import com.practice.bugtracker.dtos.mappers.impls.TeamMapperImpl;
import com.practice.bugtracker.helpers.TeamBuilder;
import com.practice.bugtracker.models.Team;
import com.practice.bugtracker.repositories.TeamRepository;
import com.practice.bugtracker.services.impls.TeamServiceImpl;
import com.practice.bugtracker.validations.exceptions.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

  @Mock
  TeamRepository teamRepository;

  @InjectMocks
  private TeamServiceImpl teamService;

  @Captor
  ArgumentCaptor<Team> teamArgumentCaptor;

  @Captor
  ArgumentCaptor<UUID> uuidArgumentCaptor;

  @BeforeEach
  void setUp() {
    teamService = new TeamServiceImpl(teamRepository, new TeamMapperImpl());
  }

  @Test
  void shouldReturnAListOfTeams() {
    List<Team> mockList = TeamBuilder.buildTeamList(3);

    when(teamRepository.findAll()).thenReturn(mockList);

    List<TeamDTO> dtoList = teamService.getAll();

    assertThat(dtoList).hasSize(3);
  }

  @Test
  void shouldThrowNotFoundExceptionWhenIdNotExistsUpdate() {
    when(teamRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> {
      teamService.updateById(UUID.randomUUID(), null);
    });
  }

  @Test
  void shouldUpdateTeamById() {
    Team mockTeam = TeamBuilder.buildTeam();

    when(teamRepository.findById(any(UUID.class))).thenReturn(Optional.of(mockTeam));
    when(teamRepository.save(any(Team.class))).thenReturn(mockTeam);

    TeamDTO mockDto = TeamBuilder.buildTeamDto();
    mockDto.setTitle("New title");
    teamService.updateById(mockDto.getId(), mockDto);

    verify(teamRepository).findById(uuidArgumentCaptor.capture());
    verify(teamRepository).save(teamArgumentCaptor.capture());

    assertThat(mockDto.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    assertThat(mockDto.getTitle()).isEqualTo(teamArgumentCaptor.getValue().getTitle());
  }

  @Test
  void shouldThrowNotFoundExceptionWhenIdNotExistsDelete() {
    given(teamRepository.findById(any(UUID.class))).willReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> {
      teamService.deleteById(UUID.randomUUID());
    });
  }

  @Test
  void shouldDeleteTeamById() {
    Team mockTeam = TeamBuilder.buildTeam();

    when(teamRepository.findById(any(UUID.class))).thenReturn(Optional.of(mockTeam));
    doNothing().when(teamRepository).deleteById(uuidArgumentCaptor.capture());

    Boolean result = teamService.deleteById(mockTeam.getId());

    assertThat(mockTeam.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    assertThat(result).isTrue();
  }

  @Test
  void shouldThrowNotFoundExceptionWhenIdDontExists() {
    when(teamRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> {
      teamService.getById(UUID.randomUUID());
    });
  }

  @Test
  void shouldGetTeamById() {
    Team mockTeam = TeamBuilder.buildTeam();

    when(teamRepository.findById(any(UUID.class))).thenReturn(Optional.of(mockTeam));

    TeamDTO findedTeam = teamService.getById(mockTeam.getId());

    verify(teamRepository).findById(uuidArgumentCaptor.capture());

    assertThat(mockTeam.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    assertThat(mockTeam.getId()).isEqualTo(findedTeam.getId());
  }

  @Test
  void shouldCreateTeam() {
    Team mockTeam = TeamBuilder.buildTeam();
    TeamDTO dto = TeamBuilder.buildTeamDto();

    when(teamRepository.save(any(Team.class))).thenReturn(mockTeam);

    TeamDTO savedTeam = teamService.create(dto);

    verify(teamRepository).save(teamArgumentCaptor.capture());

    assertThat(dto.getTitle()).isEqualTo(teamArgumentCaptor.getValue().getTitle());
  }
}
