package com.tylerhanavan.gui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Submission {
	
	private List<Field> fields;
	private Main main;

	public Submission(List<Field> fields, Main main) {
		this.fields = fields;
		this.main = main;
	}
    
	/**
	 * Check for null data and submit the data, generating a new file and writing to it, if no errors occur
	 */
    public void submit() {
    	Field idField = getFieldByLabel("ID");
		if(idField == null) {
			error("ID field not found");
			return;
		}
		Field numberField = getFieldByLabel("NUMBER");
		if(numberField == null) {
			error("NUMBER field not found");
			return;
		}
		
		String fileName = getFileName(idField, numberField);
		
		if(!createDirectory())
			error("Permissions error in generating directory /tmp/gui.");
		
		if(!createFile(fileName))
			error("Permissions error in generating file.");
		
		if(writeFile(fileName, getData())) {
			success("Generated file: " + fileName);
		}
		
    }
    
    /**
     * Get the data to be written to the file, from the fields
     * @return
     */
    private String getData() {
    	String data = "";
    	for(Field field : fields) {
    		if(data.length() != 0)
    			data += "|";
    		data += field.getData();
    	}
    	return data;
    }
    
    /**
     * Get the file name based on various fields and the system date
     * @param idField The ID field
     * @param numberField The NUMBER field
     * @return
     */
    private String getFileName(Field idField, Field numberField) {
		int id = Integer.parseInt(idField.getData());
		int number = Integer.parseInt(numberField.getData());
    	String date = new SimpleDateFormat("yyyy-MM-dd--HH-mm-ss").format(new Date());
    	String filename = "new_file_" + id + "_" + number + "_" + date + ".txt";
    	return filename;
    }
    
    /**
     * Create the file
     * @param fileName The file's name excluding path
     * @return True if created
     */
	private boolean createFile(String fileName) {
		try {
			File file = new File("/tmp/gui/" + fileName);
			if(file.createNewFile()) {
				System.out.println("File created: " + file.getName());
			} else {
				System.out.println("File already exists.");
				return false;
			}
			
		} catch (IOException e) {
			System.out.println("An error occurred creating the file.");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Write to the file
	 * @param fileName The file's name excluding path
	 * @param data The data to write
	 * @return True if written 
	 */
	private boolean writeFile(String fileName, String data) {
		try {
			FileWriter writer = new FileWriter("/tmp/gui/" + fileName);
			writer.write(data);
			writer.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Create the directory
	 * @return True if created
	 */
	private boolean createDirectory() {
		File file = new File("/tmp/gui/");
		if(file.mkdir()) {
			System.out.println("Directory created created: " + file.getName());
		} else {
			System.out.println("Directory already exists.");
			return false;
		}
		return true;
	}
    
	/**
	 * Render an error message
	 * @param msg Text to render
	 */
    private void error(String msg) {
    	main.setErrorStatus(msg);
    }
    
    /**
     * Render a success message
     * @param msg Text to render
     */
    private void success(String msg) {
    	main.setSuccessStatus(msg);
    }
    
    /**
     * Get a field based on its label name
     * @param label The field's name/label
     * @return The field
     */
    private Field getFieldByLabel(String label) {
    	for(Field field : fields) {
    		if(field.getLabel().equals(label)) 
    			return field;
    	}
    	return null;
    }
	
}
