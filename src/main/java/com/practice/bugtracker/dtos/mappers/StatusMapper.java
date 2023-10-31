package com.practice.bugtracker.dtos.mappers;

import com.practice.bugtracker.dtos.StatusDTO;
import com.practice.bugtracker.dtos.TeamDTO;
import com.practice.bugtracker.models.Status;
import com.practice.bugtracker.models.Team;

public interface StatusMapper {

  Status statusDtoToStatus(StatusDTO dto);

  StatusDTO statusToStatusDto(Status status);
}
