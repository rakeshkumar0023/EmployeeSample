package com.rakeshkumar.org;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class EmployeeFileOperation extends EmployeeOperation implements storageOperation{
	
	public void addEmployee(int id,String name,int managerid) throws EmployeeNotFoundException {
		
		super.addEmployee(id, name, managerid);
		write();
	}
	
	public void write(){
		
		FileOutputStream out=null;
		try {
			out=new FileOutputStream("Employee.txt");
			Iterator<Employee> it=list.iterator();
			Employee e=null;
			String s=null;
			while(it.hasNext()) {
				e=(Employee)it.next();
				s=e.getId()+" "+e.getName()+" "+e.getManagerId()+"\n";
				out.write(s.getBytes());
			}
			out.close();
		}
		catch(IOException e) {
			logger.fatal("IOException got");
		}
		
	}
	
	public Employee getEmployee(int id) throws EmployeeNotFoundException {
		
		read();
		return super.getEmployee(id);
	}
	
	public List<Employee> getHierarchy(int id) throws EmployeeNotFoundException {
		
		read();
		return super.getHierarchy(id);
	}
	
	public void read() {
		File f=null;
		int id,managerid=0;
		String name;
		String []s=null;
		
		try {
			f=new File("Employee.txt");
			Scanner sc=new Scanner(f);
			
			while(sc.hasNextLine()) {
				
				s=sc.nextLine().split(" ");
				id=Integer.parseInt(s[0]);
				name=" ";
				
				for(int i=1;i<s.length;i++)
				{
					if(isInteger(s[i])) {
						managerid=Integer.parseInt(s[i]);
					}
					else
						name+=s[i]+" ";
				}
				list.add(new Employee(id,name,managerid));
			}
			sc.close();
		}
		catch(IOException e) {
			logger.fatal("File corrupted");
		}
	}
	
	boolean isInteger(String s) {
		
		try {
			if(new Integer(Integer.parseInt(s)) instanceof Integer)
				return true;
			else
				return false;
		}
		catch(Exception e) {
			return false;
		}
	}

}
