package com.practice.bugtracker.repositories;

import com.practice.bugtracker.models.Team;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, UUID> {

}
