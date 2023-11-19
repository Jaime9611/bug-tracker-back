package com.practice.bugtracker.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.practice.bugtracker.dtos.TicketTypeDTO;
import com.practice.bugtracker.dtos.mappers.impls.TicketTypeMapperImpl;
import com.practice.bugtracker.helpers.TicketTypeBuilder;
import com.practice.bugtracker.models.TicketType;
import com.practice.bugtracker.repositories.TicketTypeRepository;
import com.practice.bugtracker.services.impls.TicketTypeServiceImpl;
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
class TicketTypeServiceTest {

  @Mock
  TicketTypeRepository repository;

  @InjectMocks
  TicketTypeServiceImpl service;

  @Captor
  ArgumentCaptor<UUID> uuidArgumentCaptor;

  @Captor
  ArgumentCaptor<TicketType> ticketTypeArgumentCaptor;

  @BeforeEach
  void setUp() {
    service = new TicketTypeServiceImpl(repository, new TicketTypeMapperImpl());
  }

  @Test
  void create() {
    TicketType modelObj = TicketTypeBuilder.buildTicketType();

    when(repository.save(any(TicketType.class))).thenReturn(modelObj);

    TicketTypeDTO dto = TicketTypeBuilder.buildTicketTypeDto();
    TicketTypeDTO savedObj = service.create(dto);

    verify(repository).save(ticketTypeArgumentCaptor.capture());

    assertThat(dto.getTitle()).isEqualTo(ticketTypeArgumentCaptor.getValue().getTitle());
    assertThat(dto.getTitle()).isEqualTo(savedObj.getTitle());
  }

  @Test
  void shouldThrowNotFoundWhenIdDoesNotExistsGetById() {
    when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> {
      service.getById(UUID.randomUUID());
    });
  }

  @Test
  void getById() {
    TicketType mockObj = TicketTypeBuilder.buildTicketType();

    when(repository.findById(any(UUID.class))).thenReturn(Optional.of(mockObj));

    TicketTypeDTO foundObj = service.getById(mockObj.getId());

    verify(repository).findById(uuidArgumentCaptor.capture());

    assertThat(mockObj.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    assertThat(foundObj.getId()).isEqualTo(mockObj.getId());
  }

  @Test
  void getAll() {
    List<TicketType> mockList = TicketTypeBuilder.buildTicketTypeList(3);

    when(repository.findAll()).thenReturn(mockList);

    List<TicketTypeDTO> result = service.getAll();

    assertThat(result).hasSize(3);
  }
}