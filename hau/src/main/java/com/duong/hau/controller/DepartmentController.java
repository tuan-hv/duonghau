package com.duong.hau.controller;

import com.duong.hau.dto.DepartmentDTO;
import com.duong.hau.exception.APIResponse;
import com.duong.hau.repository.DepartmentRepository;
import com.duong.hau.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

    @GetMapping
    public ResponseEntity<APIResponse<List<DepartmentDTO>>> findAllDepartment(){
        Optional<List<DepartmentDTO>> departmentDTOList = departmentService.findAllDepartment();
        if(departmentDTOList.isPresent()){
            return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.value(), "Success!", departmentDTOList.get()));
        }
        LOGGER.info("No content!");
        return  ResponseEntity.noContent().build();
    }
}
