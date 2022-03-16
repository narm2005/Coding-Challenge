package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	private int noOfReports = 0;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CompensationRepository compensationRepository;

	
	@Override
	public Employee create(Employee employee) {
		LOG.debug("Creating employee [{}]", employee);

		employee.setEmployeeId(UUID.randomUUID().toString());
		employeeRepository.insert(employee);

		return employee;

	}

	@Override
	public Employee read(String id) {
		LOG.debug("Creating employee with id [{}]", id);

		Employee employee = employeeRepository.findByEmployeeId(id);

		if (employee == null) {
			throw new RuntimeException("Invalid employeeId: " + id);
		}

		return employee;
	}

	@Override
	public String getNoOfReports(String id) {
		LOG.debug("getting employee with id [{}]", id);		

		//Getting no of Reports for employee id
		noOfReports = getTotal(id);

		String returnStr = "numberOfReports : "+noOfReports;
		return returnStr;
	}

	
	// Helping function to get No of Reports for specified employee Id
	 int getTotal(String id) {
		Employee employee = employeeRepository.findByEmployeeId(id);
		
		if (employee == null) {
			throw new RuntimeException("Invalid employeeId from getNoOfreports: " + id);
		}


		if (employee.getDirectReports() != null) {
			Iterator<Employee> map = employee.getDirectReports().iterator();

			while (map.hasNext()) {
				Employee pair = map.next();
				//System.out.println(pair.getEmployeeId());
				
				//Iterate through another empid and get no of direct reports
				getTotal(pair.getEmployeeId());
				noOfReports = noOfReports + 1;	
				//System.out.println("noOfReports"+noOfReports);
			}
		}
		return noOfReports;
	}

	@Override
	public List<Employee> all() {
		LOG.debug("getting All employees");

		List<Employee> employee = employeeRepository.findAll();

		if (employee == null) {
			throw new RuntimeException("No employees found: ");
		}

		return employee;
	}

	@Override
	public Employee update(Employee employee) {
		LOG.debug("Updating employee [{}]", employee);

		return employeeRepository.save(employee);
	}

	
	
	
	public Compensation createCompensation(String id, Compensation compensation) {	
		
			LOG.debug("Creating compensation: [{}]", compensation);
		    Employee employee = read(compensation.getEmployee().getEmployeeId());
	    	compensation.setEmployee(employee);
	        compensationRepository.insert(compensation);
	        
	        return compensation;
	}

	@Override 
	public Compensation readCompensation(String id) { 
		LOG.debug("Creating compensation with id [{}]", id);

		Employee employee=employeeRepository.findByEmployeeId(id);
		
		Compensation compensation = compensationRepository.findByEmployee(employee);

		if (compensation == null) {
			throw new RuntimeException("No valid Employee: " + id);
		}

		return compensation;
 
	  }
	  
	  
	  
	  @Override public List<Compensation> findAllCompensation() { 
		  // TODO	  Auto-generated method stub 
	  return null; 
	  }
	 
}
