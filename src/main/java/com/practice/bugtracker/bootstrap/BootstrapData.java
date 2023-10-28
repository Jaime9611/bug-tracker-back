package com.practice.bugtracker.bootstrap;

import com.practice.bugtracker.models.Ticket;
import com.practice.bugtracker.repositories.TicketRepository;
import java.util.Arrays;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

  private final TicketRepository ticketRepository;

  @Override
  public void run(String... args) throws Exception {
//    loadTicketData();
  }

  private void loadTicketData() {
    if(ticketRepository.count() == 0) {
      Ticket ticket1 = Ticket.builder()
          .ticketId(UUID.randomUUID())
          .title("Create Backend project")
          .description("Ticket 1 descp")
          .build();

      Ticket ticket2 = Ticket.builder()
          .ticketId(UUID.randomUUID())
          .title("Create Frontend project")
          .description("Ticket 2 descp")
          .build();

      Ticket ticket3 = Ticket.builder()
          .ticketId(UUID.randomUUID())
          .title("Create Logo for the project")
          .description("Ticket 3 descp")
          .build();

      ticketRepository.saveAll(Arrays.asList(ticket1, ticket2, ticket3));
    }

  }
}
