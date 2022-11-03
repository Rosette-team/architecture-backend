package edu.rosette.architecturebackend.mappers;

import edu.rosette.architecturebackend.datatransfer.ManagerDto;
import edu.rosette.architecturebackend.models.Manager;
import edu.rosette.architecturebackend.repositories.DepartmentRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class ManagerMapper {
    @Autowired
    DepartmentRepository departmentRepository;

    public abstract Manager managerDtoToManager(ManagerDto managerDto);

    @AfterMapping
    protected void afterManagerDtoToManager(ManagerDto managerDto, @MappingTarget Manager manager) {
        var department = departmentRepository.findById(managerDto.getDepartmentId());
        if (department.isEmpty()) {
            throw new RuntimeException("Try to assign department with id %d but it's not exits".formatted(managerDto.getDepartmentId()));
        }
        manager.setDepartment(department.get());
    }

    public abstract ManagerDto managerToManagerDto(Manager manager);

    @AfterMapping
    protected void afterManagerToManagerDto(Manager manager, @MappingTarget ManagerDto managerDto) {
        if (manager.getDepartment() == null) {
            return;
        }
        managerDto.setDepartmentId(manager.getDepartment().getId());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Manager updateManagerFromManagerDto(ManagerDto managerDto, @MappingTarget Manager manager);
}
