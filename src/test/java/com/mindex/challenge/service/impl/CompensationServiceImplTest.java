/**
 * 
 */
package com.mindex.challenge.service.impl;


import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;

/**
 * @author narmadha.palanisamy
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {
	
	private String employeeUrl;
	private String createCompensationUrl;
    private String readCompensationUrl;
    private Employee testEmployee;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private CompensationRepository compensationRepository;
    
    
    @Before
    public void setup() {
    	employeeUrl = "http://localhost:" + port + "/employee";
        createCompensationUrl = "http://localhost:" + port + "/employee/{id}/compensation";
        readCompensationUrl = "http://localhost:" + port + "/employee/{id}";
        testEmployee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
    }

    @Test
    public void testCompensation() {
    	 Compensation testCompensation = new Compensation();
         testCompensation.setEmployee(testEmployee);
         testCompensation.setSalary("12000");
         Date today = Calendar.getInstance().getTime();         
         testCompensation.setEffectiveDate(today);
         
      // Create checks
         ResponseEntity createdCompensationResponse = restTemplate.postForEntity(createCompensationUrl, testCompensation, Compensation.class);
         assertEquals(HttpStatus.OK, createdCompensationResponse.getStatusCode());
         Compensation createdCompensation = (Compensation)createdCompensationResponse.getBody();
         assertNotNull(createdCompensation);
         assertEquals(testCompensation, createdCompensation);

         // Read checks
         ResponseEntity readCompensationResponse = restTemplate.getForEntity(readCompensationUrl, Compensation.class, createdCompensation.getEmployee().getEmployeeId());
         assertEquals(HttpStatus.OK, readCompensationResponse.getStatusCode());
         Compensation readCompensation = (Compensation)readCompensationResponse.getBody();
         assertNotNull(readCompensation);
         assertEquals(readCompensation, createdCompensation);
    }

    

}
