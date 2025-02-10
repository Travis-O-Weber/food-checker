/**
 * This controller take the user input and write the file into csv file.
 * @author Binary Brain
 * UTSA CS 3443 - Final Project
 * Fall 2022
 * */

package application.controller;

import java.io.File;
import java.io.FileNotFoundException;
import application.Main;
import application.model.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class RegisterController{

	//Button - submit, used to move to the main page
    @FXML
     Button submit;
    //Button - used to move to register page
    @FXML
     Button signIn;
    //field for full name
    @FXML
    TextField fname;
  //field for last name
    @FXML
    TextField lname;
  //field for user name
    @FXML
    TextField userName;
  //Label that show the error message
    @FXML
    Label error_message;
  //field for password
    @FXML
    PasswordField pass;
  //field for email
    @FXML
    TextField email;


    /**
     * In this handle, when the user click submit, it will take all the information from
     * user input and write into the login.csv file. Also, it will do the validation for 
     * the password, if the user password meet the requirement, it will go through.
     * */

    @FXML
    public void handle(ActionEvent event) throws IOException {
    	//no error-set error label to none
    	error_message.setText("");
    	boolean sucessfull_pass = true;
    	//Open the file
    	File newUserFile = new File("data/login.csv");
    	FileWriter fw;
		try {
			//Check if the user is exist or not
			if (newUserFile.exists())
			{
			     fw = new FileWriter(newUserFile,true);//if file exists append to file. Works fine.
			}
			//if not exist, start write the file based on user input 
			else
			{
			     fw = new FileWriter(newUserFile);// If file does not exist. Create it. This throws a FileNotFoundException. Why? 
			}
			StringBuilder sb=new StringBuilder();
			//Append all the information to the login.csv file
			sb.append(fname.getText());
			sb.append(",");
			sb.append(lname.getText());
			sb.append(",");
			sb.append(email.getText());
			sb.append(",");
			sb.append(userName.getText());
			sb.append(",");
			
			List<String> errorList = new ArrayList<String>();

			String password = pass.getText();
			// check for validation
			while (!isValid(password,errorList)) {
			       error_message.setText("The password entered here  is invalid");
			       error_message.setStyle("-fx-text-fill: red");
			       error_message.setVisible(true);
			       for (String error : errorList) {
			           System.out.println(error);
			       }
			       sucessfull_pass = false;
			        break;
			}
			//if everything is pass, append the password to the file
			   if (sucessfull_pass == true)
			   {
			       sb.append(pass.getText());  
			       // append the new line at the end to seperate every user.
				   sb.append(System.getProperty("line.separator"));
			       error_message.setText("Account created sucessfully!");
			       error_message.setStyle("-fx-text-fill: green");
			       fw.write(sb.toString());
			       // get the user information into the global arraylist
			       Main.mainAccount = new Account(fname.getText(),lname.getText(),email.getText(),userName.getText(),pass.getText());
			       fw.close();
					FXMLLoader loader = new FXMLLoader();
					//move to the main section
					loader.setLocation(getClass().getResource("../view/Main.fxml"));
													  
					Scene scene = new Scene(loader.load());
					
					Main.stage.setScene(scene);	       
					Main.stage.show();
			   }
			
			 
		}
		catch (FileNotFoundException e) {    
			// TODO Auto-generated catch block
			System.out.println("Something went wrong.");
			e.printStackTrace();
		}
	 
   }
    	
 //This event used to move to the Login page
@FXML
public void Click(ActionEvent event) {
	if(event.getSource().equals(signIn)) {
		try {
			File newUserFile = new File("data/login.csv");
	    	FileWriter fw;
	    	try {
	    		if (!newUserFile.exists()) {
	    			fw = new FileWriter(newUserFile);
	    			fw.close();
	    		}
	    	}
	    	catch(FileNotFoundException e){
	    		System.out.print("Something went wrong.");
				e.printStackTrace();
			} 
	    	

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../view/Login.fxml"));

			Scene scene = new Scene(loader.load());

			Main.stage.setScene(scene);
			Main.stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	if(event.getSource().equals(submit)) {
		try {
			if(Main.mainAccount != null) {
			
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
// Function used to validate the password
public static boolean isValid(String passwordhere, List<String> errorList) {
	//Using regex to check if the password contain the character we require
    Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
    Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
    Pattern lowerCasePatten = Pattern.compile("[a-z ]");
    Pattern digitCasePatten = Pattern.compile("[0-9 ]");
    errorList.clear();

    boolean flag=true;
   
    if (passwordhere.length() < 8) {
        errorList.add("Password lenght must have alleast 8 character !!");
        flag=false;
    }
    if (!specailCharPatten.matcher(passwordhere).find()) {
        errorList.add("Password must have atleast one specail character !!");
        flag=false;
    }
    if (!UpperCasePatten.matcher(passwordhere).find()) {
        errorList.add("Password must have atleast one uppercase character !!");
        flag=false;
    }
    if (!lowerCasePatten.matcher(passwordhere).find()) {
        errorList.add("Password must have atleast one lowercase character !!");
        flag=false;
    }
    if (!digitCasePatten.matcher(passwordhere).find()) {
        errorList.add("Password must have atleast one digit character !!");
        flag=false;
    }

    return flag;

}
}