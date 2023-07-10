package com.doctor_service.mapper;


import com.doctor_service.entities.Department;
import com.common.response.DepartmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);


    DepartmentResponse departmentDepartmentResponse(Department department);
}
