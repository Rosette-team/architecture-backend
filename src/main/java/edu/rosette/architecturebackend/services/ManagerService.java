package edu.rosette.architecturebackend.services;

import edu.rosette.architecturebackend.datatransfer.ManagerDto;
import edu.rosette.architecturebackend.mappers.ManagerMapper;
import edu.rosette.architecturebackend.repositories.ManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

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

    public List<ManagerDto> getManagers(Long departmentId) {
        if (departmentId != null) {
            var managers = managerRepository.findAllByDepartmentId(departmentId);
            return managers.stream().map(doctor -> managerMapper.managerToManagerDto(doctor)).toList();
        } else {
            var doctors = managerRepository.findAll();
            return StreamSupport.stream(doctors.spliterator(), false).map(doctor -> managerMapper.managerToManagerDto(doctor)).toList();
        }
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
