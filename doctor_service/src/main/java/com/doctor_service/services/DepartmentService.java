package com.doctor_service.services;


import com.doctor_service.entities.Department;
import com.doctor_service.imp.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    private  final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    public Department getDepartment(String name){
        return departmentRepository.findByName(name);

    }
}
