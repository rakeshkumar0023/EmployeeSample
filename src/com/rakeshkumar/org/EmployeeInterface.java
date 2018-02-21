package com.rakeshkumar.org;

import java.util.List;

public interface EmployeeInterface {

	void addEmployee(int id,String name,int managerid) throws EmployeeNotFoundException;
	Employee getEmployee(int id) throws EmployeeNotFoundException;
	List<Employee> getHierarchy(int id) throws EmployeeNotFoundException;
}
