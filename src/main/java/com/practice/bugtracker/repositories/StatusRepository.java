package com.practice.bugtracker.repositories;

import com.practice.bugtracker.models.Status;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends CrudRepository<Status, UUID> {
  Status findByTitle(String title);
}
