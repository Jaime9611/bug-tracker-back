package com.practice.bugtracker.repositories;

import com.practice.bugtracker.models.Priority;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepository extends CrudRepository<Priority, UUID> {

}
