package com.rakeshkumar.org;

import java.util.List;

public class EmployeeService{

	EmployeeOperation e=null;
	
	EmployeeService() {

	}

	EmployeeService(String name) throws EmployeeNotFoundException{
		
		switch(name) {
		
		case "inmemory": e=new EmployeeOperation();
						break;
						
		case "file": 	e=new EmployeeFileOperation();
						break;
						
		case "hdfs":		e=new EmployeeHdfsOperation();
						break;
						
		default: 		throw new EmployeeNotFoundException("Incorrect Input!");
		
		}
		
	}
	
	
	void addEmployee(int id,String name,int managerId) throws EmployeeNotFoundException{
		
		e.addEmployee(id, name, managerId);
	}
	
	Employee getEmployee(int id) throws EmployeeNotFoundException{
		
		return e.getEmployee(id);
	}
	
	List<Employee> getHierarchy(int id) throws EmployeeNotFoundException{
		
		List<Employee> l=null;
		l=e.getHierarchy(id);
		return l;
	}
}
