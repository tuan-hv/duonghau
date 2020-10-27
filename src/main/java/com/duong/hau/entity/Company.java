package com.duong.hau.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "company")
@Data
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "company_name", nullable = false)
    private String companyName;
    @Column(name = "address")
    private String address;
    @Column(name = "status")
    private int status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private Set<Employee> listEmployee = new HashSet<>();

}
