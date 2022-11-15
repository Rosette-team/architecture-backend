package edu.rosette.architecturebackend.mappers;

import edu.rosette.architecturebackend.models.Department;
import edu.rosette.architecturebackend.datatransfer.DepartmentDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DepartmentMapper {
    Department departmentDtoToDepartment(DepartmentDto departmentDto);

    DepartmentDto departmentToDepartmentDto(Department department);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Department updateDepartmentFromDepartmentDto(DepartmentDto departmentDto, @MappingTarget Department department);
}
