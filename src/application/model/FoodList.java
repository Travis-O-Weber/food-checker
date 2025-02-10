/**In this model, it takes in charge of holding food information, also, there are some 
 * method that will update when the user add or delete food.
 * 
 * @author Binary Brain
 * UTSA CS 3443 - Final Project
 * Fall 2022
 * */
package application.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class FoodList {

	
	// **************************************************
    // Fields
    // **************************************************
	private String name;
	private ArrayList<Food> foodList;
	
	// **************************************************
    // Constructors
    // **************************************************
    /**
    * Parameterized constructor

    * assigns the name and creates an array list of for the contacts contained inside
    */
	public FoodList(String name){
		
		this.setName(name);
		foodList = new ArrayList<Food>();
		
	}
	
	// **************************************************
    // Public methods
    // **************************************************
    /**
     * loadContacts: loads multiple contacts into the address book through file
     * @param string with path to file
     * 
     */
	
	public void loadFood (String filePath) {
		try {
			File fileName = new File(filePath);
			Scanner scan = new Scanner(fileName);
			while(scan.hasNextLine()) {
				String foodLine = scan.nextLine();
				String[] arrofFood = foodLine.split(",");
				Food tempFood = new Food(arrofFood[0],arrofFood[1], arrofFood[2]);
				foodList.add(tempFood);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found");
			e.printStackTrace();
		}	
	}
	
	
	
	// **************************************************
    // Getters and Setters for private variables
    // **************************************************
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param String containing name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the foodList ArrayList
	 */
	public ArrayList<Food> getArrayList(){
		return foodList;
	}
	/**
	 * @param set the contact
	 */
	public void setContacts(ArrayList<Food> tempFoodList) {
		this.foodList = tempFoodList;
	}
	/*
	 * Read the file and add the new food into the file
	 * */
	public void addFood(Food food) {
		foodList.add(food);
		// read the file
		File filePath = new File("data/"+Main.mainAccount.getUserName()+".csv");
    	FileWriter fWrite;
    	try {
    		
    		fWrite = new FileWriter(filePath,true);
    		fWrite.write(food.toString());
    		fWrite.close();
    		FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../view/Main.fxml"));

			Scene scene = new Scene(loader.load());

			Main.stage.setScene(scene);
			Main.stage.show();
    	}
    	catch(FileNotFoundException e){
    		System.out.print("Something went wrong.");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}
	
	//Read the file and remove the item from the file
	public void removeFood(ArrayList<Food> deletedFoods) {
		File filePath = new File("data/"+Main.mainAccount.getUserName()+".csv");
		//Loop the deletdFoods array list and remove the item we watn
		for(Food food: deletedFoods) {
			for(int i = 0; i<foodList.size();i++) {
				if(food.getIngredientName().equals(foodList.get(i).getIngredientName())) {
					foodList.remove(i);
				}
			}
		}
		try {
			FileWriter writer = new FileWriter(filePath);
			//Loop through foodList and add the information in it.
			for(Food newFood: foodList){
				writer.append(newFood.toString());
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

}
