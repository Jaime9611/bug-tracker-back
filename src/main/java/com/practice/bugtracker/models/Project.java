package com.practice.bugtracker.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
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
@Entity
public class Project {

  @Id
  @GeneratedValue(generator = "UUID")
  @JdbcTypeCode(SqlTypes.VARCHAR)
  @Column(length = 36, columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
  private UUID projectId;

  @NotNull
  @NotBlank
  @Column(length = 50, columnDefinition = "VARCHAR(50)")
  private String title;

  @OneToOne
  @JoinColumn(name = "team_id")
  private Team team;

  @OneToMany(mappedBy = "project")
  private Set<Ticket> tickets;

  private LocalDateTime startsAt;
  private LocalDateTime endsAt;
}
