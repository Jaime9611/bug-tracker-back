package com.practice.bugtracker.dtos.mappers.impls;

import static org.assertj.core.api.Assertions.assertThat;

import com.practice.bugtracker.dtos.StatusDTO;
import com.practice.bugtracker.dtos.mappers.StatusMapper;
import com.practice.bugtracker.models.Status;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StatusMapperImplTest {

  StatusMapper statusMapper;

  @BeforeEach
  void setUp() {
    statusMapper = new StatusMapperImpl();
  }

  @Test
  void shouldMapStatusToStatusDto() {
    Status status = Status.builder()
        .id(UUID.randomUUID())
        .title("Status test dto")
        .build();

    StatusDTO resultDto = statusMapper.statusToStatusDto(status);

    assertThat(status.getId()).isEqualTo(resultDto.getId());
    assertThat(status.getTitle()).isEqualTo(resultDto.getTitle());
  }

  @Test
  void shouldMapStatusDtotoStatus() {
    StatusDTO dto = StatusDTO.builder()
        .id(UUID.randomUUID())
        .title("Status test model")
        .build();

    Status resultModel = statusMapper.statusDtoToStatus(dto);

    assertThat(dto.getId()).isEqualTo(resultModel.getId());
    assertThat(dto.getTitle()).isEqualTo(resultModel.getTitle());
  }
}