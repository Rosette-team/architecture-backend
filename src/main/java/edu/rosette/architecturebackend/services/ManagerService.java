package edu.rosette.architecturebackend.services;

import edu.rosette.architecturebackend.datatransfer.ManagerDto;
import edu.rosette.architecturebackend.mappers.ManagerMapper;
import edu.rosette.architecturebackend.repositories.ManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ManagerService {
    ManagerRepository managerRepository;

    ManagerMapper managerMapper;

    public long addManager(ManagerDto managerDto) {
        var manager = managerMapper.managerDtoToManager(managerDto);
        return managerRepository.save(manager).getId();
    }

    public Optional<ManagerDto> getManager(long id) {
        var manager =  managerRepository.findById(id);
        if (manager.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(managerMapper.managerToManagerDto(manager.get()));
    }

    public Optional<ManagerDto> updateManager(long id, ManagerDto managerDto) {
        var manager = managerRepository.findById(id);
        if (manager.isEmpty()) {
            return Optional.empty();
        }
        var updatedManager = managerMapper.updateManagerFromManagerDto(managerDto, manager.get());
        return Optional.of(managerMapper.managerToManagerDto(managerRepository.save(updatedManager)));
    }

    public void deleteManager(long id) {
        managerRepository.deleteById(id);
    }
}
