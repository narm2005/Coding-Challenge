package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee create(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);

        return employeeService.create(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        return employeeService.read(id);
    }

    @GetMapping("/employees")
    public List<Employee> all() {
        LOG.debug("Received employee real all");

        return employeeService.all();
    }

    
    /* Task 1
     * 
     */
    @GetMapping(value ="/employee/{id}/getnoofreports")
    public String getNoOfReports(@PathVariable String id) {
    	// id="16a596ae-edd3-4847-99fe-c4518e82c86f";
    	 LOG.debug("Received employee get no of Report for id [{}]", id);

         return employeeService.getNoOfReports(id);
    	
    }    

    
    @PutMapping("/employee/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee create request for id [{}] and employee [{}]", id, employee);

        employee.setEmployeeId(id);
        return employeeService.update(employee);
    }
    
    
    /* Task 2
     * 
     */
    @PostMapping("/employee/{id}/compensation")
    public Compensation createCompensation(@PathVariable String id, @RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for [{}]", compensation);

        return employeeService.createCompensation(id, compensation);
    }

    @GetMapping("/employee/compensation/{id}")
    public Employee readCompensation(@PathVariable String id) {
        LOG.debug("Read Compensation by Employee Id. [{}]", id);

        return employeeService.read(id);
    }

        
}
