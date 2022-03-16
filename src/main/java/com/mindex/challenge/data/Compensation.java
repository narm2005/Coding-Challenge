package com.mindex.challenge.data;

import java.util.Date;
import java.util.Objects;


public class Compensation {
	
		
	/* Task 2:
	 * employee, Salary & Set Effective Date
	 */
    private String salary;
    private Date effectiveDate;
    
    
    private Employee employee;
    
    public Compensation() {
    }
   
    public Compensation(String salary, Date effectiveDate, Employee employee)
    {
    	this.salary=salary;
    	this.effectiveDate=effectiveDate;
    	this.employee=employee;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    
    public String getSalary() {
    	return this.salary;
    }
    
    public void setSalary(String salary) {
    	this.salary = salary;
    }
    
    public void setEffectiveDate(Date effectiveDate) {
    	this.effectiveDate = effectiveDate;
    }
    
    public Date getEffectvieDate() {
    	return this.effectiveDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, salary, effectiveDate);
    }

    @Override
    public String toString() {
        return "{" +
            " employee='" + getEmployee() + "'" +
            ", salary='" + getSalary() + "'" +
            ", effectiveDate='" + getEffectvieDate() + "'" +
            "}";
    }
	
}
