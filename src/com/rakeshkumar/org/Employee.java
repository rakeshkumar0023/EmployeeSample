package com.rakeshkumar.org;

public class Employee {

	private int Id;
	private String Name;
	private int managerId;
	
	Employee(){
		
	}
	
	Employee(int id,String name,int managerid){
		Id=id;
		Name=name;
		managerId=managerid;
	}

	public int getId() {
		return Id;
	}

	public String getName() {
		return Name;
	}

	public int getManagerId() {
		return managerId;
	}
	
}
