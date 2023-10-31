package com.practice.bugtracker.dtos.mappers.impls;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.practice.bugtracker.dtos.TicketTypeDTO;
import com.practice.bugtracker.dtos.mappers.TicketTypeMapper;
import com.practice.bugtracker.models.TicketType;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicketTypeMapperImplTest {

  private TicketTypeMapper ticketTypeMapper;

  @BeforeEach
  void setUp() {
    ticketTypeMapper = new TicketTypeMapperImpl();
  }

  @Test
  void shouldMapTicketTypeToTicketTypeDto() {
    TicketType ticketType = TicketType.builder()
        .id(UUID.randomUUID())
        .title("TicketType test dto")
        .build();

    TicketTypeDTO resultDto = ticketTypeMapper.ticketTypeToTicketTypeDto(ticketType);

    assertThat(ticketType.getId()).isEqualTo(resultDto.getId());
    assertThat(ticketType.getTitle()).isEqualTo(resultDto.getTitle());
  }

  @Test
  void shouldMapTicketTypeDtoToTicketType() {
    TicketTypeDTO dto = TicketTypeDTO.builder()
        .id(UUID.randomUUID())
        .title("TicketType test dto")
        .build();

    TicketType resultModel = ticketTypeMapper.ticketTypeDTOToTicketType(dto);

    assertThat(dto.getId()).isEqualTo(resultModel.getId());
    assertThat(dto.getTitle()).isEqualTo(resultModel.getTitle());
  }
}