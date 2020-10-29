package com.duong.hau.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DepartmentDTO {
    private long id;
    private String departmentName;
    private String mission;
    private int qualityPeople;
    private int status;
    private List<EmployeeDTO> listEmployee;
}
