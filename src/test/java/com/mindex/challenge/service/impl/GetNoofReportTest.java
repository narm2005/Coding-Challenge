/**
 * 
 */
package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;

/**
 * @author narmadha.palanisamy
 *
 */

	
	
	import com.mindex.challenge.data.Employee;
	import com.mindex.challenge.service.EmployeeService;
	import org.junit.Before;
	import org.junit.Test;
	import org.junit.runner.RunWith;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.context.SpringBootTest;
	import org.springframework.boot.test.web.client.TestRestTemplate;
	import org.springframework.boot.web.server.LocalServerPort;
	import org.springframework.http.HttpEntity;
	import org.springframework.http.HttpHeaders;
	import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import static org.junit.Assert.assertEquals;
	import static org.junit.Assert.assertNotNull;

	@RunWith(SpringRunner.class)
	@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
	public class GetNoofReportTest {

	    private String employeeUrl;
	    private String getnoofReportsUrl;
	    private Employee testEmployee;

	    @Autowired
	    private EmployeeService employeeService;
	    
	    @Autowired
	    private EmployeeRepository employeeRepository;
	   

	    @LocalServerPort
	    private int port;

	    @Autowired
	    private TestRestTemplate restTemplate;

	    @Before
	    public void setup() {
	        employeeUrl = "http://localhost:" + port + "/employee";
	        getnoofReportsUrl = "http://localhost:" + port + "/employee/{id}/getnoofreports";
	        testEmployee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
	        
	    }

	    @Test
	    public void testGetNoOfReports() throws RestClientException {
	    	
	    	
	    	String readNoOfReportResponse;
	    	
			try {
				readNoOfReportResponse = restTemplate.getForObject(getnoofReportsUrl, String.class, testEmployee.getEmployeeId());
				assertEquals("numberOfReports : 2",readNoOfReportResponse);
			} catch (RestClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
	         
	         
	    }
	     

}
