package com.rakeshkumar.org;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends Exception {
	
	static Logger log=Logger.getLogger(EmployeeNotFoundException.class);
	EmployeeNotFoundException(){
		
	}

	EmployeeNotFoundException(String message){
		
		log.error(message);
	}
}
