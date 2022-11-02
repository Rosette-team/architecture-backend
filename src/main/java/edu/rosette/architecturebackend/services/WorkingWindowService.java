package edu.rosette.architecturebackend.services;

import edu.rosette.architecturebackend.models.WorkingWindow;
import edu.rosette.architecturebackend.repositories.WorkingWindowRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class WorkingWindowService {
    WorkingWindowRepository workingWindowRepository;

    public long addWorkingWindow(WorkingWindow workingWindow) {
        return workingWindowRepository.save(workingWindow).getId();
    }

    public Optional<WorkingWindow> getWorkingWindow(long id) {
        return workingWindowRepository.findById(id);
    }

    public Optional<WorkingWindow> updateWorkingWindow(long id, WorkingWindow workingWindowDto) {
        var workingWindow = workingWindowRepository.findById(id);
        if (workingWindow.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(workingWindowRepository.save(workingWindow.get()));
    }

    public void deleteWorkingWindow(long id) {
        workingWindowRepository.deleteById(id);
    }
}
