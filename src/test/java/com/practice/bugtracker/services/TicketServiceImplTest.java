package com.practice.bugtracker.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.practice.bugtracker.dtos.TicketDTO;
import com.practice.bugtracker.dtos.mappers.impls.PriorityMapperImpl;
import com.practice.bugtracker.dtos.mappers.impls.ProjectMapperImpl;
import com.practice.bugtracker.dtos.mappers.impls.StatusMapperImpl;
import com.practice.bugtracker.dtos.mappers.impls.TeamMapperImpl;
import com.practice.bugtracker.dtos.mappers.impls.TicketMapperImpl;
import com.practice.bugtracker.dtos.mappers.impls.TicketTypeMapperImpl;
import com.practice.bugtracker.helpers.TicketBuilder;
import com.practice.bugtracker.models.Ticket;
import com.practice.bugtracker.repositories.TicketRepository;
import com.practice.bugtracker.services.impls.TicketServiceImpl;
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
class TicketServiceImplTest {

  @Mock
  TicketRepository repository;

  @InjectMocks
  TicketServiceImpl service;

  @Captor
  ArgumentCaptor<UUID> uuidArgumentCaptor;

  @Captor
  ArgumentCaptor<Ticket> ticketArgumentCaptor;

  @BeforeEach
  void setUp() {
    service = new TicketServiceImpl(repository, new TicketMapperImpl(
        new PriorityMapperImpl(),
        new StatusMapperImpl(),
        new TicketTypeMapperImpl(),
        new ProjectMapperImpl(new TeamMapperImpl(), new StatusMapperImpl())
    ));
  }

  @Test
  void create() {
    Ticket ticketMock = TicketBuilder.buildTicket();

    when(repository.save(any(Ticket.class))).thenReturn(ticketMock);

    TicketDTO dto = TicketBuilder.buildTicketDto();
    TicketDTO ticketSaved = service.create(dto);

    verify(repository).save(ticketArgumentCaptor.capture());

    assertThat(dto.getTitle()).isEqualTo(ticketArgumentCaptor.getValue().getTitle());
  }

  @Test
  void shouldThrowNotFoundExceptionWhenIdNotExistsGetById() {
    when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> {
      service.getById(UUID.randomUUID());
    });
  }

  @Test
  void getById() {
    Ticket ticketMock = TicketBuilder.buildTicket();

    when(repository.findById(any(UUID.class))).thenReturn(Optional.of(ticketMock));

    TicketDTO result = service.getById(ticketMock.getTicketId());

    verify(repository).findById(uuidArgumentCaptor.capture());

    assertThat(result.getId()).isEqualTo(uuidArgumentCaptor.getValue());
  }

  @Test
  void shouldThrowNotFoundExceptionWhenIdDoesNotExistDeleteById() {
    when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> service.deleteById(UUID.randomUUID()));
  }

  @Test
  void deleteById() {
    Ticket ticketMock = TicketBuilder.buildTicket();

    when(repository.findById(any(UUID.class))).thenReturn(Optional.of(ticketMock));
    doNothing().when(repository).deleteById(uuidArgumentCaptor.capture());

    Boolean result = service.deleteById(ticketMock.getTicketId());

    assertThat(uuidArgumentCaptor.getValue()).isEqualTo(ticketMock.getTicketId());
    assertThat(result).isTrue();
  }

  @Test
  void getAll() {
    List<Ticket> ticketList = TicketBuilder.buildTicketList(3);

    when(repository.findAll()).thenReturn(ticketList);

    List<TicketDTO> result = service.getAll();

    assertThat(result.size()).isEqualTo(3);
  }
}