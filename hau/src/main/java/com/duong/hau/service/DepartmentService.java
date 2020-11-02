package com.duong.hau.service;

import com.duong.hau.convertDTO.CompanyConvert;
import com.duong.hau.convertDTO.DepartmentConvert;
import com.duong.hau.convertDTO.EmployeeConvert;
import com.duong.hau.dto.DepartmentDTO;
import com.duong.hau.dto.EmployeeDTO;
import com.duong.hau.entity.Company;
import com.duong.hau.entity.Department;
import com.duong.hau.entity.Employee;
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

    public Optional<DepartmentDTO> getById(Long departmentId){
        Optional<Department> department = departmentRepository.findById(departmentId);
        if(department.isPresent()){
            LOGGER.info("Success!");
            return Optional.of(DepartmentConvert.convertDepartmentToDepartmentDTO(department.get()));
        }
        LOGGER.error("Fail :(");
        return null;
    }

    public Optional<DepartmentDTO> createDepartment(DepartmentDTO departmentDTO){
        try{
            Optional<Department> department = departmentRepository.findById(departmentDTO.getId());
            if(department.isPresent()){
                LOGGER.info("Success! updated!");
                department.get().setDeparmentName(departmentDTO.getDepartmentName());
                department.get().setMission(departmentDTO.getMission());
                department.get().setQualityPeople(departmentDTO.getQualityPeople());
                department.get().setStatus(departmentDTO.getStatus());
                List<Employee> employeeList = new ArrayList<>();
                List<EmployeeDTO> employeeDTOList = departmentDTO.getListEmployee();
                if(employeeDTOList!=null)employeeDTOList.forEach(c-> {
                    employeeList.add(EmployeeConvert.convertEmployeeDTOToEmployee(c));
                });
                department.get().setListEmployee(employeeList);
                Department departmentUpdate = departmentRepository.save(department.get());
                departmentDTO.setId(departmentUpdate.getDeparmentId());
            } else{
                LOGGER.info("Success! Created!");
                 Department departmentAdd = departmentRepository.save(DepartmentConvert.convertDepartmentDTOToDepartment(departmentDTO));
                 departmentDTO.setId(departmentAdd.getDeparmentId());
            }
            return Optional.of(departmentDTO);
        }
        catch (Exception e){
            LOGGER.error("error with save department ::",e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<DepartmentDTO> updateDepartment (DepartmentDTO departmentDTO){

            Optional<Department> department = departmentRepository.findById(departmentDTO.getId());
            if(department.isPresent()){
                LOGGER.info("Success! updated!");
                department.get().setDeparmentName(departmentDTO.getDepartmentName());
                department.get().setMission(departmentDTO.getMission());
                department.get().setQualityPeople(departmentDTO.getQualityPeople());
                department.get().setStatus(departmentDTO.getStatus());
                List<Employee> employeeList = new ArrayList<>();
                List<EmployeeDTO> employeeDTOList = departmentDTO.getListEmployee();
                if(employeeDTOList!=null)employeeDTOList.forEach(c-> {
                    employeeList.add(EmployeeConvert.convertEmployeeDTOToEmployee(c));
                });
                department.get().setListEmployee(employeeList);
                Department departmentUpdate = departmentRepository.save(department.get());
                departmentDTO.setId(departmentUpdate.getDeparmentId());
                return Optional.of(departmentDTO);
            }

            LOGGER.error("error with save department!");
            return Optional.empty();
    }

    public boolean deleteDepartment (Long departmentId){
        try{
            Optional<Department> department = departmentRepository.findById(departmentId);
            if(department.isPresent()){
                departmentRepository.deleteById(departmentId);
                return true;
            }
            return false;
        } catch (Exception e){
            LOGGER.error("error");
            return false;
        }
    }
}
