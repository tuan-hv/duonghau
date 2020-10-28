package com.duong.hau.convertDTO;


import com.duong.hau.dto.EmployeeDTO;
import com.duong.hau.entity.Employee;

public class EmployeeConvert {

    public static EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setAddress(employee.getAddress());
        employeeDTO.setPhoneNumber(employee.getPhoneNumber());
        employeeDTO.setStatus(employee.getStatus());

        return employeeDTO;
    }

    public static Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();

        employee.setId(employeeDTO.getId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setAddress(employeeDTO.getAddress());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setStatus(employeeDTO.getStatus());

        return employee;
    }
}
