package com.duong.hau.controller;

import com.duong.hau.dto.DepartmentDTO;
import com.duong.hau.entity.Department;
import com.duong.hau.exception.APIResponse;
import com.duong.hau.repository.DepartmentRepository;
import com.duong.hau.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

    @GetMapping("/department")
    public ResponseEntity<APIResponse<List<DepartmentDTO>>> findAllDepartment(){
        Optional<List<DepartmentDTO>> departmentDTOList = departmentService.findAllDepartment();
        if(departmentDTOList.isPresent()){
            return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.value(), "Success!", departmentDTOList.get()));
        }
        LOGGER.info("No content!");
        return  ResponseEntity.noContent().build();
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<DepartmentDTO> getById(@PathVariable(value = "id") Long departmentID){
        Optional<DepartmentDTO> departmentDTO = departmentService.getById(departmentID);
        if(departmentDTO.isPresent()){
            return ResponseEntity.ok( departmentDTO.get());
        }
        LOGGER.info("No content :D");
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/department")
    public ResponseEntity<APIResponse<DepartmentDTO>> createDepartment(@Valid @RequestBody DepartmentDTO departmentDTO){
        LOGGER.info("Starting save department. . .");
        DepartmentDTO createDepartment = departmentService.createDepartment(departmentDTO).get();
        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.value(), "Starting save department. . .", createDepartment));
    }

    @PutMapping("/department")
    public ResponseEntity<APIResponse<DepartmentDTO>> updateDepartment(@RequestBody DepartmentDTO departmentDTO){
        LOGGER.info("updating. . .");
        DepartmentDTO updateDepartment = departmentService.updateDepartment(departmentDTO).get();
        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.value(), "Updating. . .", updateDepartment));
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<APIResponse<Boolean>> deleteDepartment(@PathVariable(value =  "id") Long departmentId){
        return ResponseEntity.ok(new APIResponse(HttpStatus.OK.value(), "delete by id", departmentService.deleteDepartment(departmentId)));
    }
}
