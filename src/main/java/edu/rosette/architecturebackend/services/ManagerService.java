package edu.rosette.architecturebackend.services;

import edu.rosette.architecturebackend.models.Manager;
import edu.rosette.architecturebackend.repositories.ManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ManagerService {
    ManagerRepository managerRepository;

    public long addManager(Manager manager) {
        return managerRepository.save(manager).getId();
    }

    public Optional<Manager> getManager(long id) {
        return managerRepository.findById(id);
    }

    public Optional<Manager> updateManager(long id, Manager managerDto) {
        var manager = managerRepository.findById(id);
        if (manager.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(managerRepository.save(manager.get()));
    }

    public void deleteManager(long id) {
        managerRepository.deleteById(id);
    }
}
