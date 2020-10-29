package com.duong.hau.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CompanyDTO {
    private long id;
    private String companyName;
    private String address;
    private int status;

    private List<EmployeeDTO> listEmployee;

    public CompanyDTO(String companyName, String address, int status) {
        this.companyName = companyName;
        this.address = address;
        this.status = status;
    }

    public CompanyDTO(long id, String companyName, String address, int status) {
        this.id = id;
        this.companyName = companyName;
        this.address = address;
        this.status = status;
    }


}
