/**
 * The class that store User information
 * @author Binary Brain
 * UTSA CS 3443 - Final Project
 * Fall 2022
 * */

package application.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import application.Main;

public class Account{
	// Declare the variable
	private String fName;
	private String lName;
	private String email;
	private String password;
	private String userName;
	private FoodList foodList;
	
	//Create the constructor for the class. 
	public Account(String fName, String lName, String email, String userName, String password) {
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.password = password;
		this.userName = userName;
		setupFoodList();
	}

	

	/**
	 * @return
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * @param fName
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}

	/**
	 * @return
	 */
	public String getlName() {
		return lName;
	}

	/**
	 * @param lName
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * @return
	 */
	public FoodList getFoodList() {
		return foodList;
	}

	
	/**
	 * @param foodList
	 */
	public void setFoodList(FoodList foodList) {
		this.foodList = foodList;
	}
	// toString method
	public String toString() {
		String str = "";
		str += this.fName ;
		str += "bonjour" + this.lName ;
		str += "bonjour" + this.email ;
		str += "bonjour" + this.userName ;
		str += "bonjour" + this.password;
		return str;
	}
	
	/* This method read the file and set the value of it to specific variable
	 * */
	public Account(String fileName) {
		try {
        	
			File inputFile = new File(fileName);
			Scanner scan = new Scanner(inputFile);  
			//scan line by line
			while(scan.hasNext() ){
	
					String line = scan.nextLine();
					//split the word by ","
					String [] splitWord = line.split(","); 
			if(!splitWord[0].equals("First Name")) {
						
				 // set the value to each variable
					this.fName = splitWord[0];
					this.lName  = splitWord[1];
					this.email  = splitWord[2];
					this.userName  = splitWord[3];
					this.password  = splitWord[4];
					break;
					}
			 
	
     	 }
			scan.close();						   
		} catch (FileNotFoundException e) {    
			// TODO Auto-generated catch block
			System.out.print("Something wend wrong.");
			e.printStackTrace();
		} 
    }
	// Create the object to pass in the controller
	   public static Account loadData(String fileName) {
			Account newUser = new Account(fileName);
			return newUser;
		}
	   // Read the file, and hold the data of food for each user
	   public void setupFoodList() {
		   foodList = new FoodList("user Food List");
		   String filePath = "data/"+userName+".csv";
		   
	    	try {
	    		File newUserListFile = new File(filePath);
	    		if (!newUserListFile.exists()) {
	    			FileWriter fWrite;
	    			fWrite = new FileWriter(newUserListFile);
	    			fWrite.close();
	    		}
	    	}
	    	catch(FileNotFoundException e){
	    		System.out.print("Something went wrong.");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	foodList.loadFood(filePath);
	   }
	  
}
	
