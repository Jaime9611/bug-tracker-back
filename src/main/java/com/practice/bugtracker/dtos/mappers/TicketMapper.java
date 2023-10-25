package com.practice.bugtracker.dtos.mappers;

import com.practice.bugtracker.dtos.TicketDTO;
import com.practice.bugtracker.models.Ticket;

public interface TicketMapper {

    Ticket IssueDtoToIssue(TicketDTO dto);
    TicketDTO IssueToIssueDto(Ticket issue);
}
