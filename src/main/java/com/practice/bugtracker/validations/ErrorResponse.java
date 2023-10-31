package com.practice.bugtracker.validations;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
  private String error;
  private Integer status;
}
