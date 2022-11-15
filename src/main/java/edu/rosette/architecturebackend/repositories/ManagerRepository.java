package edu.rosette.architecturebackend.repositories;

import edu.rosette.architecturebackend.models.Manager;
import org.springframework.data.repository.CrudRepository;

public interface ManagerRepository extends CrudRepository<Manager, Long> {
}
