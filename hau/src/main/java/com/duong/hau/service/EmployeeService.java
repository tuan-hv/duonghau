package com.duong.hau.service;

import com.duong.hau.convertDTO.CompanyConvert;
import com.duong.hau.convertDTO.EmployeeConvert;
import com.duong.hau.dto.CompanyDTO;
import com.duong.hau.dto.EmployeeDTO;
import com.duong.hau.entity.Company;
import com.duong.hau.entity.Employee;
import com.duong.hau.repository.CompanyRepository;
import com.duong.hau.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);

    public Optional<List<EmployeeDTO>> findAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees != null) {
            LOGGER.info("get all employee success!");
            List<EmployeeDTO> employeeDTOS = new ArrayList<>();
            employees.forEach(c ->{
                employeeDTOS.add(EmployeeConvert.convertEmployeeToEmployeeDTO(c));

            });
            return Optional.of(employeeDTOS);
        }
        LOGGER.info("get all employee fail!");
        return Optional.empty();
    }

    public Optional<EmployeeDTO> getEmployeeById(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            LOGGER.info("get employee by id success...");
            return Optional.of(EmployeeConvert.convertEmployeeToEmployeeDTO(employee.get()));
        }
        LOGGER.info("get company by id fail...");
        return Optional.empty();
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        try{
            Optional<Company> company = companyRepository.findById(employeeDTO.getCompanyDTO().getId());
            Employee employee =  EmployeeConvert.convertEmployeeDTOToEmployee(employeeDTO);
            employee.setCompany(company.get());
            Employee employeeUpdate = employeeRepository.save(employee);
            employeeDTO.setId(employeeUpdate.getEmployeeId());
            LOGGER.info("save company success!");
            return employeeDTO;
        }
        catch (Exception e){
            LOGGER.error("error with save employee ::",e.getMessage());
            return null;
        }
    }
    public boolean updateEmployee (EmployeeDTO employeeDTO){
        try{
            Optional<Employee> employee = employeeRepository.findById(employeeDTO.getId());
            employee.get().setEmployeeId(employeeDTO.getId());
            employee.get().setFirstName(employeeDTO.getFirstName());
            employee.get().setLastName(employeeDTO.getLastName());
            employee.get().setStatus(employeeDTO.getStatus());
            employee.get().setAddress(employeeDTO.getAddress());
            employee.get().setEmail(employeeDTO.getEmail());
            employee.get().setPhoneNumber(employeeDTO.getPhoneNumber());
            Optional<Company> company = companyRepository.findById(employeeDTO.getCompanyDTO().getId());
            employee.get().setCompany(company.get());
            employeeRepository.save(employee.get());
            return  true;
        }
        catch (Exception e){
            LOGGER.error("error");
            return false;
        }
    }
    public boolean deleteEmployee (Long employeeId){
        try{
            Optional<Employee> employee = employeeRepository.findById(employeeId);
            if(employee.isPresent()){
                employeeRepository.deleteById(employeeId);
                return true;
            }
            return false;
        } catch (Exception e){
            LOGGER.error("error");
            return false;
        }
    }

    public List<EmployeeDTO> findByName(String employeeName){
        try{
            List<Employee> employees = employeeRepository.findByFirstNameStartingWith(employeeName);
            List<EmployeeDTO> employeeDTOS = new ArrayList<>();
            employees.forEach(e->{
                employeeDTOS.add(EmployeeConvert.convertEmployeeToEmployeeDTO(e));
            });
            return employeeDTOS;
        } catch (Exception e){
            LOGGER.error("error!");
            return null;
        }
    }
}
