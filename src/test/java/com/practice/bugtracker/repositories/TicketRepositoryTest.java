package com.practice.bugtracker.repositories;

import com.practice.bugtracker.helpers.TicketBuilder;
import com.practice.bugtracker.models.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class TicketRepositoryTest {

    @Autowired
    TicketRepository ticketRepository;

    @Test
    void shouldSaveIssue() {
        Ticket savedIssue = ticketRepository.save(TicketBuilder.buildTicket());

        assertThat(savedIssue).isNotNull();
        assertThat(savedIssue.getTicketId()).isNotNull();
    }
}