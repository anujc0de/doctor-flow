package com.doctor_service.imp;

import com.doctor_service.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Department findByName(String name);
}
