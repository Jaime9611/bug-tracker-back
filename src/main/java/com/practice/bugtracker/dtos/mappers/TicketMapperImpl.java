package com.practice.bugtracker.dtos.mappers;

import com.practice.bugtracker.dtos.TicketDTO;
import com.practice.bugtracker.models.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapperImpl implements TicketMapper {
    @Override
    public Ticket IssueDtoToIssue(TicketDTO dto) {
        if(dto == null) {
            return null;
        }

        Ticket.TicketBuilder issue = Ticket.builder();

        issue.ticketId(dto.getId());
        issue.title(dto.getTitle());
        issue.description(dto.getDescription());

        return issue.build();
    }

    @Override
    public TicketDTO IssueToIssueDto(Ticket issue) {
        if(issue == null) {
            return null;
        }

        TicketDTO.TicketDTOBuilder dto = TicketDTO.builder();

        dto.id(issue.getTicketId());
        dto.title(issue.getTitle());
        dto.description(issue.getDescription());

        return dto.build();
    }
}
