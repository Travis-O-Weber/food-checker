/** The class that stores the food properties.
 * 
 * 
 * @author Binary Brain
 * UTSA CS 3443 - Final Project
 * Fall 2022
 * */

package application.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Food {
	// Declare all the variable
	private String ingredientName;
	private String quantity;
	private String expDate;
	private final BooleanProperty select = new SimpleBooleanProperty();
	
	// Create the constructor for class
	public Food(String Name, String qty, String exp_date) {
		setIngredientName(Name);
		setQuantity(qty);
		setExpDate(exp_date);
		this.select.setValue(false);
	}

	/**
	 * @return the ingredientName
	 */


	public String getIngredientName() {
		return ingredientName;
	}

	
	/**
	 * @param ingredientName
	 */
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	/**
	 * @return the quantity
	 */
	
	public String getQuantity() {
		return quantity;
	}

	
	/**
	 * @param quantity
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the expDate
	 */
	
	public String getExpDate() {
		return expDate;
	}

	
	/**
	 * @param expDate
	 */
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public BooleanProperty selectProperty() { return select; }
	/**
     * will Override the toString function to follow the specific lab output
     */
	@Override
	public String toString(){
		String finalOutput = ingredientName+","+quantity+","+expDate+"\n";
		
		return finalOutput;
	}
	
}