package com.rakeshkumar.org;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public class EmployeeOperation implements EmployeeInterface{
	
	protected static List<Employee> list = new ArrayList<>();
	List<Integer> list_id = new ArrayList<>();
	static Logger logger=Logger.getLogger(EmployeeOperation.class);
	
	public void addEmployee(int id, String name, int managerId) throws EmployeeNotFoundException {

		if (managerId == -1) {
			list.add(new Employee(id, name, managerId));
			list_id.add(id);
		}

		else if (list_id.contains(id))
			throw new EmployeeNotFoundException("Employee id exists");

		else if (list_id.contains(managerId)) {
			list.add(new Employee(id, name, managerId));
			list_id.add(id);
		}

		else
			throw new EmployeeNotFoundException("Manager not found");
	}

	public Employee getEmployee(int id) throws EmployeeNotFoundException {
		
		Iterator<Employee> it = list.iterator();
		Employee e = null;
		while (it.hasNext()) {
			e = (Employee) it.next();
			if (e.getId() == id) {
				return e;
			}
		}
		throw new EmployeeNotFoundException("Employee not found");
	}

	public List<Employee> getHierarchy(int id) throws EmployeeNotFoundException {

		List<Employee> l = new LinkedList<>();
		Employee e = getEmployee(id);
		int managerid = e.getManagerId();
		while (e.getManagerId() != -1) {
			l.add(e);
			e = getEmployee(managerid);
			managerid = e.getManagerId();
		}
		if (e.getManagerId() == -1)
			l.add(e);
		return l;
	}
}
