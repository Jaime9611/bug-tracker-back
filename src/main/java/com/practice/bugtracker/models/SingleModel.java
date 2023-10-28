package com.practice.bugtracker.models;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class SingleModel {
  @Id
  @GeneratedValue(generator = "UUID")
  @JdbcTypeCode(SqlTypes.VARCHAR)
  @Column(length = 36, columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
  private UUID id;

  @NotNull
  @NotBlank
  @Column(length = 50, columnDefinition = "VARCHAR(50)")
  private String title;
}
