package com.duong.hau.service;

import com.duong.hau.convertDTO.DepartmentConvert;
import com.duong.hau.dto.DepartmentDTO;
import com.duong.hau.dto.EmployeeDTO;
import com.duong.hau.entity.Department;
import com.duong.hau.repository.CompanyRepository;
import com.duong.hau.repository.DepartmentRepository;
import com.duong.hau.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);

    public Optional<List<DepartmentDTO>> findAllDepartment() {
        List<Department> departmentList = departmentRepository.findAll();
        if(departmentList != null){
            LOGGER.info("Success!");
            List<DepartmentDTO> departmentDTOList = new ArrayList<>();
            departmentList.forEach(c -> {
                departmentDTOList.add(DepartmentConvert.convertDepartmentToDepartmentDTO(c));
            });
            return Optional.of(departmentDTOList);
        }

        LOGGER.error("Error!");
        return null;
    }
}
