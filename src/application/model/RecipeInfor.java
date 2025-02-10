/** In this model, it will handle the api in fethching the data and 
 * parse it under Json format that control the recipe information. 
 * 
 * 
 * @author Binary Brain
 * UTSA CS 3443 - Final Project
 * Fall 2022
 * */

package application.model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import application.Main;

public class RecipeInfor{
	//Create all the variable.
	private String instruction;
	private String summary;
	
	// Create the constructor
	public RecipeInfor(String instruction, String summary) {
	
		this.instruction = instruction;
		this.summary = summary;
	}

	// **************************************************
    // Getters and Setters for private variables
    // **************************************************
	
	/**
	 * @return
	 */
	public String getInstruction() {
		return instruction;
	}


	/**
	 * @param instruction
	 */
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	/**
	 * @return
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	/** In this method, this fetch the api and parse it int the Json format, which store food information.
	 * 
	 * */
	public RecipeInfor(){
		// Host url
	      //String host = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com";
	      //String charset = "UTF-8";
	      // Headers for a request
	      String x_rapidapi_host = "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com";
	      String x_rapidapi_key = "d5e529c26cmshc5a77b8cc4f62a2p12a602jsne7dc75366267";//Type here your key
	 
	  
	      String res = Main.idQuery;
	      
	      HttpResponse<JsonNode> response2;
	      System.out.println("id from model " +res);
		try {
			//fetch the api
			response2 = Unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/"+res+"/information")
						.header("X-RapidAPI-Key", x_rapidapi_key)
						.header("X-RapidAPI-Host", x_rapidapi_host)
						.asJson();
		
				// Parse the api into Json format and set it to variable
	    	      
	    	      Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
	    	      JsonParser jp2 = new JsonParser();
	    	      JsonElement je2 = jp2.parse(response2.getBody().toString());
	    	      //System.out.print(je2);
	    	  
	    	      JsonObject jo2 = je2.getAsJsonObject();
	    	      System.out.println(jo2);
	    	      //System.out.println(jo2.get("id"));
	    	      System.out.println(jo2.get("instructions"));
	    	      this.instruction = jo2.get("instructions").getAsString();
	    	      this.summary = jo2.get("summary").getAsString();
	    	      Main.recipeInforArray.add(new RecipeInfor(this.instruction, this.summary));
	    	      
	  // Format query for preventing encoding problems
	     
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	
	}
	// Create Object to pass in controller
	public static RecipeInfor fetchRecipeInfor() {
		RecipeInfor newRecipeInfor = new RecipeInfor();
		return newRecipeInfor;
	}










}