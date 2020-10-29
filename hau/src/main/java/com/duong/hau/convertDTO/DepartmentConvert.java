package com.duong.hau.convertDTO;

import com.duong.hau.dto.DepartmentDTO;
import com.duong.hau.entity.Department;

public class DepartmentConvert {
    public static DepartmentDTO convertDepartmentToDepartmentDTO (Department department){
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getDeparmentId());
        departmentDTO.setDepartmentName(department.getDeparmentName());
        departmentDTO.setMission(department.getMission());
        departmentDTO.setQualityPeople(department.getQualityPeople());
        departmentDTO.setStatus(department.getStatus());

        return departmentDTO;
    }
    public static Department convertDepartmentDTOToDepartment (DepartmentDTO departmentDTO){
        Department department = new Department();
        department.setDeparmentId(department.getDeparmentId());
        department.setDeparmentName(departmentDTO.getDepartmentName());
        department.setMission(departmentDTO.getMission());
        department.setQualityPeople(departmentDTO.getQualityPeople());
        department.setStatus(departmentDTO.getStatus());
        return department;
    }
}
