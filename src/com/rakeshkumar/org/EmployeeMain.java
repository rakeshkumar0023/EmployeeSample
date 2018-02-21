package com.rakeshkumar.org;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;

public class EmployeeMain {

	static Logger log=Logger.getLogger(EmployeeMain.class);
	
	public static void main(String[] args) {
		
		Properties prop=new Properties();
		InputStream input=null;
		
		try {
			
			input=new FileInputStream("config.properties");
			prop.load(input);
			
			EmployeeService e = new EmployeeService(prop.getProperty("typeOfStorage"));
			
			e.addEmployee(1, "rakesh kumar", -1);
			e.addEmployee(2, "hemant chaudhary", 1);
			e.addEmployee(3, "sparsh jauhari", 2);
			e.addEmployee(4, "vicky kumar", 2);
			//e.addEmployee(3, "sparsh jauhari3", 2);

			Employee e1 = e.getEmployee(1);
			log.info(e1.getId()+" "+e1.getName()+" "+e1.getManagerId()+"\n");
			List<Employee> l = e.getHierarchy(4);
			
			for(int i=0;i<l.size();i++)
				log.info(l.get(i).getId()+" "+l.get(i).getName()+" "+l.get(i).getManagerId());
			
		} 
		
		catch (EmployeeNotFoundException e1) {
		}
		
		catch(IOException e) {
			log.fatal("file Not found");
		}
	}
}
