package edu.rosette.architecturebackend.services;

import edu.rosette.architecturebackend.datatransfer.WorkingWindowDto;
import edu.rosette.architecturebackend.mappers.WorkingWindowMapper;
import edu.rosette.architecturebackend.repositories.WorkingWindowRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class WorkingWindowService {
    WorkingWindowRepository workingWindowRepository;

    WorkingWindowMapper workingWindowMapper;

    public long addWorkingWindow(WorkingWindowDto workingWindowDto) {
        var workingWindow = workingWindowMapper.workingWindowDtoToWorkingWindow(workingWindowDto);
        return workingWindowRepository.save(workingWindow).getId();
    }

    public Optional<WorkingWindowDto> getWorkingWindow(long id) {
        var workingWindow =  workingWindowRepository.findById(id);
        if (workingWindow.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(workingWindowMapper.workingWindowToWorkingWindowDto(workingWindow.get()));
    }

    public Optional<WorkingWindowDto> updateWorkingWindow(long id, WorkingWindowDto workingWindowDto) {
        var workingWindow = workingWindowRepository.findById(id);
        if (workingWindow.isEmpty()) {
            return Optional.empty();
        }
        var updatedWorkingWindow = workingWindowMapper.updateWorkingWindowFromWorkingWindowDto(workingWindowDto, workingWindow.get());
        return Optional.of(workingWindowMapper.workingWindowToWorkingWindowDto(workingWindowRepository.save(updatedWorkingWindow)));
    }

    public void deleteWorkingWindow(long id) {
        workingWindowRepository.deleteById(id);
    }
}
