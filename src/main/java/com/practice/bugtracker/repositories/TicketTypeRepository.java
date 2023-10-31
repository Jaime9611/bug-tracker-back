package com.practice.bugtracker.repositories;

import com.practice.bugtracker.models.TicketType;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketTypeRepository extends CrudRepository<TicketType, UUID> {

}
