package com.practice.bugtracker.dtos.mappers.impls;

import static org.assertj.core.api.Assertions.assertThat;

import com.practice.bugtracker.dtos.PriorityDTO;
import com.practice.bugtracker.dtos.mappers.PriorityMapper;
import com.practice.bugtracker.models.Priority;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class PriorityMapperImplTest {

  private PriorityMapper priorityMapper;

  @BeforeEach
  void setUp() {
    priorityMapper = new PriorityMapperImpl();
  }

  @Test
  void shouldMapPriorityDtoToPriority() {
    PriorityDTO priorityDTO = PriorityDTO.builder()
        .id(UUID.randomUUID())
        .title("Priority test model").build();

    Priority resultModel = priorityMapper.priorityDtoToPriority(priorityDTO);

    assertThat(priorityDTO.getId()).isEqualTo(resultModel.getId());
    assertThat(priorityDTO.getTitle()).isEqualTo(resultModel.getTitle());
  }

  @Test
  void shouldMapPriorityToPriorityDto() {
    Priority priority = Priority.builder()
        .id(UUID.randomUUID())
        .title("Priority test dto")
        .build();

    PriorityDTO resultDto = priorityMapper.priorityToPriorityDto(priority);

    assertThat(priority.getId()).isEqualTo(resultDto.getId());
    assertThat(priority.getTitle()).isEqualTo(resultDto.getTitle());
  }
}
