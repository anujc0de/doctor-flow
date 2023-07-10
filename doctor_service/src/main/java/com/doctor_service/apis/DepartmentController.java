package com.doctor_service.apis;

import com.doctor_service.mapper.DepartmentMapper;
import com.doctor_service.services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    private  final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper = DepartmentMapper.INSTANCE;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("{name}")
    public ResponseEntity<?> getSlots(@PathVariable("name") String departmentName) {

        var fetchedDepartment=departmentService.getDepartment(departmentName);

        return new ResponseEntity<>(departmentMapper.departmentDepartmentResponse(fetchedDepartment), HttpStatus.OK);

    }
}
