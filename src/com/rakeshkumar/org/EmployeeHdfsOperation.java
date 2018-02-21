package com.rakeshkumar.org;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class EmployeeHdfsOperation extends EmployeeOperation implements storageOperation{

	String uri="hdfs://localhost:8020";

	String path="/temp";
	String fileName="input.txt";
	String fileContent="hello;world";
	FileSystem fs=null;
	Path newFolderPath=null;
	Path workingDir=null;
	
	public void addEmployee(int id,String name,int managerid) throws EmployeeNotFoundException {
		super.addEmployee(id, name, managerid);
		init();
		write();
	}
	
	public Employee getEmployee(int id) throws EmployeeNotFoundException {
		init();
		read();
		return super.getEmployee(id);
		
	}
	
	public List<Employee> getHierarchy(int id) throws EmployeeNotFoundException {
		read();
		return super.getHierarchy(id);
	}
	
	 void init() {
		// ====== Init HDFS File System Object
		Configuration conf = new Configuration();
		// Set FileSystem URI
		conf.set("fs.defaultFS", uri);
		// Because of Maven
		try {
		conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
		conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
		// Set HADOOP user
		System.setProperty("HADOOP_USER_NAME", "hdfs");
		System.setProperty("hadoop.home.dir", "/");
		//Get the filesystem - HDFS
			fs = FileSystem.get(URI.create(uri), conf);

		//==== Create folder if not exists
		workingDir=fs.getWorkingDirectory();
		newFolderPath= new Path(path);
		if(!fs.exists(newFolderPath)) {
		   // Create new Directory
		   fs.mkdirs(newFolderPath);
		   logger.info("Path "+path+" created.");
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void write() {
		
		//==== Write file
		logger.info("Begin Write file into hdfs");
		//Create a path
		Path hdfswritepath = new Path(newFolderPath + "/" + fileName);
		try {
		//Init output stream
		FSDataOutputStream outputStream=fs.create(hdfswritepath);
		//Cassical output stream usage
		outputStream.writeBytes(fileContent);
		outputStream.close();
		logger.info("End Write file into hdfs");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void read() {
		
		//==== Read file
		try {
		logger.info("Read file into hdfs");
		//Create a path
		Path hdfsreadpath = new Path(newFolderPath + "/" + fileName);
		//Init input stream
		FSDataInputStream inputStream = fs.open(hdfsreadpath);
		//Classical input stream usage
		String out= IOUtils.toString(inputStream, "UTF-8");
		logger.info(out);
		inputStream.close();
		fs.close();
		}
		catch(IOException e) {
			
		}
	}
}

