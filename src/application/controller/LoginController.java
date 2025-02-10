/**In this Controller, it will take the input of the user and check if their information
 * match with the information in the data or not, if it match. The user successfully login
 * 
 * @author Binary Brain
 * UTSA CS 3443 - Final Project
 * Fall 2022
 * */

package application.controller;



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


import java.io.*;

public class LoginController {
	
	// Button to take user to the main page 
    @FXML
    Button submit;
    // Button to take user to register 
    @FXML 	
    Button register; 
    									
    // Password field to put the user password in 
    @FXML
    PasswordField pass;
    // Field to put the username
    @FXML
    TextField userName;
    //Field to display the message
    @FXML
    Label message;
     
    
  /*
   * This event used to read the user information in order to
   * log them in the main page
   * */

    @FXML
    public void handle(ActionEvent event) throws IOException{
		 
		 // open and read the file
		BufferedReader csvReader = new BufferedReader(new FileReader("data/login.csv"));
		String row = "";
		//Read the file line by line
		while ((row  = csvReader.readLine()) != null) {
			String[] data = row.split(",");
		//check if it match with the user you looking for
		 if(userName.getText().equals(data[3]) && pass.getText().equals(data[4])) {
			 Main.mainAccount = new Account(data[0],data[1],data[2],data[3],data[4]);
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("../view/main.fxml"));

					Scene scene = new Scene(loader.load());

					Main.stage.setScene(scene);
					Main.stage.show();
					
				} catch(Exception e) {
					e.printStackTrace();
				}
		 }
		 else{
			 //if not correct user, display the error message
			 message.setText("ERROR!! Your userName or password is invalid");
			 message.setStyle("-fx-text-fill: red");
			 message.setVisible(true);
		 }
		}
		//Close the file
		csvReader.close();
    }
    // The event that take back to the register view 
    @FXML
    public void click(ActionEvent event) {
    	if(event.getSource().equals(register)) {
    		try {

    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(getClass().getResource("../view/Register.fxml"));

    			Scene scene = new Scene(loader.load());

    			Main.stage.setScene(scene);
    			Main.stage.show();
    			
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    		
    	}

    }


}

