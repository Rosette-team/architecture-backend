package edu.rosette.architecturebackend.repositories;

import edu.rosette.architecturebackend.models.WorkingWindow;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkingWindowRepository extends CrudRepository<WorkingWindow, Long> {
    List<WorkingWindow> findAllByDoctorId(long doctorId);
}
