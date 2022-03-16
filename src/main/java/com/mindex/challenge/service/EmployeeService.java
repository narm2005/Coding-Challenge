package com.mindex.challenge.service;

import java.util.List;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;

public interface EmployeeService {
    Employee create(Employee employee);
    Employee read(String id);
    Employee update(Employee employee);
  //  Employee updateById(String Id, Employee employee);
    List<Employee> all();
    String getNoOfReports(String id);
    
    Compensation createCompensation(String Id, Compensation compensation);
	Compensation readCompensation(String id);
    List<Compensation> findAllCompensation();
    
}
