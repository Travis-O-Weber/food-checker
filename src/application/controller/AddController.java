/***
 * The Add controller controll the Add view
 * @author Binary Brain
 * UTSA CS 3443 - Final Project
 * Fall 2022 
 */

package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.Food;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddController implements Initializable{
	
	// Declare all the variable
	@FXML Button addBtn, backBtn;
	@FXML TextField nameField, qtyField, expField;
	@FXML Label addLabel;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
	// This event will control of adding food in the table view 
	@FXML
    public void handle(ActionEvent event) throws IOException {
		if(!(nameField.getText().isEmpty()||qtyField.getText().isEmpty()||expField.getText().isEmpty())) {
			
			try {
				Main.mainAccount.getFoodList().addFood(new Food(nameField.getText(),qtyField.getText(),expField.getText()));
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../view/main.fxml"));

				Scene scene = new Scene(loader.load());

				Main.stage.setScene(scene);
				Main.stage.show();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//this event take the user to the main view
	@FXML
    public void click(ActionEvent event) {
    	if(event.getSource().equals(backBtn)) {
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
	}

}
