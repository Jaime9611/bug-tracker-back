package com.practice.bugtracker.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import com.practice.bugtracker.dtos.PriorityDTO;
import com.practice.bugtracker.dtos.mappers.impls.PriorityMapperImpl;
import com.practice.bugtracker.helpers.PriorityBuilder;
import com.practice.bugtracker.models.Priority;
import com.practice.bugtracker.repositories.PriorityRepository;
import com.practice.bugtracker.services.impls.PriorityServiceImpl;
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
class PriorityServiceTest {

  @Mock
  PriorityRepository priorityRepository;

  @InjectMocks
  private PriorityServiceImpl priorityService;

  @Captor
  ArgumentCaptor<UUID> uuidArgumentCaptor;

  @Captor
  ArgumentCaptor<Priority> priorityArgumentCaptor;

  @BeforeEach
  void setUp() {
    priorityService = new PriorityServiceImpl(priorityRepository, new PriorityMapperImpl());
  }

  @Test
  void shouldCreate() {
    PriorityDTO mockDto = PriorityBuilder.buildPriorityDto();

    when(priorityRepository.save(any(Priority.class))).thenReturn(PriorityBuilder.buildPriority());

    PriorityDTO savedPriority = priorityService.create(mockDto);

    verify(priorityRepository).save(priorityArgumentCaptor.capture());

    assertThat(savedPriority.getTitle()).isEqualTo(priorityArgumentCaptor.getValue().getTitle());
  }

  @Test
  void shouldThrowNotFoundWhenIdNotExistFindById() {
    when(priorityRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> {
      priorityService.getById(UUID.randomUUID());
    });
  }

  @Test
  void getById() {
    Priority mockPriority = PriorityBuilder.buildPriority();

    when(priorityRepository.findById(any(UUID.class))).thenReturn(Optional.of(mockPriority));

    PriorityDTO foundObj = priorityService.getById(mockPriority.getId());

    verify(priorityRepository).findById(uuidArgumentCaptor.capture());

    assertThat(mockPriority.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    assertThat(foundObj.getId()).isEqualTo(mockPriority.getId());
    assertThat(foundObj.getTitle()).isEqualTo(mockPriority.getTitle());
  }

  @Test
  void getAll() {
    List<Priority> mockList = PriorityBuilder.buildPriorityList(3);

    when(priorityRepository.findAll()).thenReturn(mockList);

    List<PriorityDTO> result = priorityService.getAll();

    assertThat(result.size()).isEqualTo(3);
  }
}