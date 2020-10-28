package com.duong.hau.controller;

import com.duong.hau.dto.EmployeeDTO;
import com.duong.hau.entity.Employee;
import com.duong.hau.exception.APIResponse;
import com.duong.hau.repository.EmployeeRepository;
import com.duong.hau.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v2")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<APIResponse<List<EmployeeDTO>>> getAllEmployees() {
        Optional<List<EmployeeDTO>> employeeDTOS = employeeService.findAllEmployee();
        if (employeeDTOS.isPresent()) {
            return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.value(), "get all employee success!", employeeDTOS.get()));
        }
        LOGGER.info(" No employee found!");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(value = "id") Long employeeId) {

        LOGGER.info("Find by id employee :: ", employeeId);
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(employeeId);
        LOGGER.info("Find by id employee success!");
        return ResponseEntity.ok().body(employeeDTO.get());
    }

    @GetMapping("/employees/getemployeebyname/{name}")
    public ResponseEntity<APIResponse<List<EmployeeDTO>>> findByName(@PathVariable (value = "name") String employeeName){
        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.value(), "get all employee by name success!",employeeService.findByName(employeeName)));
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {

        LOGGER.info("starting save employee...");
        EmployeeDTO createEmployee = employeeService.createEmployee(employeeDTO);

        return ResponseEntity.ok().body(createEmployee);
    }

    @PutMapping("/employees")
    public ResponseEntity<Boolean> updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok().body(employeeService.updateEmployee(employeeDTO));
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<APIResponse<Boolean>> deleteEmployee(@PathVariable(value =  "id") Long employeeId){
        return ResponseEntity.ok(new APIResponse(HttpStatus.OK.value(), "get find by id", employeeService.deleteEmployee(employeeId)));
    }

}
