package com.duong.hau.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "department")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deparmentId;

    @Column(name = "department_name", nullable = false)
    private String deparmentName;

    @Column(name = "mission")
    private String mission;

    @Column(name = "quality_people")
    private int qualityPeople;

    @Column(name = "status")
    private int status;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(name = "department_employee", joinColumns = @JoinColumn(name = "deparment_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> listEmployee;

}
