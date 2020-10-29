package com.duong.hau.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employeeId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email_address")
	private String email;
	@Column(name = "address")
	private String address;
	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "status")
	private int status;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
	@JoinTable(name = "department_employee", joinColumns = @JoinColumn(name = "employee_id"),
			inverseJoinColumns = @JoinColumn(name = "deparment_id"))
	private List<Department> department;

}
