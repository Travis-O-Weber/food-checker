/**In this model, it will handle the api in fethching the data and 
 * parse it under Json format that controll recipe list
 * 
 * @author Binary Brain
 * UTSA CS 3443 - Final Project
 * Fall 2022
 * */
package application.model;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import application.Main;

public class Recipe{
	//Create all the variable.
	private String title;
	private int calories;
	private String protetin;
	private String carbs;
	private String id;
	private String fat;
	private String imgURL;
	
	// Create the constructor. 
	public Recipe(String title, int calories, String protetin, String carbs, String fat, String id, String imgURL) {
		super();
		this.title = title;
		this.calories = calories;
		this.protetin = protetin;
		this.carbs = carbs;
		this.fat = fat;
		this.id = id;
		this.imgURL= imgURL;
	}


	/**
	 * @return calories
	 */
	public int getCalories() {
		return calories;
	}
	/**
	 * @param calories
	 */
	public void setCalories(int calories) {
		this.calories = calories;
	}
	/**
	 * @return protein
	 */
	public String getProtetin() {
		return protetin;
	}
	/**
	 * @param protetin
	 */
	public void setProtetin(String protetin) {
		this.protetin = protetin;
	}
	/**
	 * @return carbs
	 */
	public String getCarbs() {
		return carbs;
	}
	/**
	 * @param carbs
	 */
	public void setCarbs(String carbs) {
		this.carbs = carbs;
	}
	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return fat
	 */
	public String getFat() {
		return fat;
	}
	/**
	 * @param fat
	 */
	public void setFat(String fat) {
		this.fat = fat;
	}
	
	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	// This will parse and create the query with mutiple word in order to pass to the api as the endpoint
	public String createQuery(String str) {
		String[] query = str.split(" ");
		 String res = "";
		 if(query.length == 1) {
			 return str;
		 }
		 else {
			 
				for(int i=0; i< query.length-1; i++){
					   
				    res = query[i] +"%20";
				    	
				}
				return res + query[query.length-1];
		 }
		
	}
	
	/*This will fetch the api and parse it int the Json format
	 * 
	 * */
	public  Recipe() {
		 
	      // Headers for a request
	      String x_rapidapi_host = "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com";
	      String x_rapidapi_key = "d5e529c26cmshc5a77b8cc4f62a2p12a602jsne7dc75366267";//Type here your key
	      // Params
	      String s = createQuery(Main.recipeIngredient);
	      
	      
	     
	     
		try {
			// Fetch the api
			 HttpResponse<JsonNode> response;
			response = Unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/searchComplex?limitLicense=false&offset=0&number=6&minCalories=150&maxProtein=100&minCarbs=5&query="+s+"&maxCalories=1500&maxCarbs=100&maxFat=100&minCopper=0")
						.header("X-RapidAPI-Key", x_rapidapi_key)
						.header("X-RapidAPI-Host", x_rapidapi_host)
						.asJson();
			  System.out.println(response.getStatus());
    	      System.out.println(response.getHeaders().get("Content-Type"));
    	      
    	      //Parse the json and set the value to each variable
    	      Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	      JsonParser jp = new JsonParser();
    	      JsonElement je = jp.parse(response.getBody().toString());
    	      JsonObject jo = je.getAsJsonObject();
    	      JsonArray arr = jo.getAsJsonArray("results");
    	    System.out.println(arr.size());
    	      for( int i=0; i< arr.size(); i++) {
 
    	      jo = arr.get(i).getAsJsonObject();
    	      this.title= jo.get("title").getAsString();
    	      this.fat  =jo.get("fat").getAsString();
    	      this.calories= Integer.parseInt(jo.get("calories").getAsString());
    	      this.carbs = jo.get("carbs").getAsString();
    	      this.id = jo.get("id").getAsString();
    	      this.protetin = jo.get("protein").getAsString();
    	      this.imgURL = jo.get("image").getAsString()
;    	      Main.recipeArray.add(new Recipe(this.title, this.calories, this.protetin, this.fat, this.carbs, this.id,this.imgURL));
    	     
    	      }
    	      System.out.println(arr);
    	     
  	        
    	     
		}
		catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Object to pass to controller
	public static Recipe fetchRecipe() {
		Recipe newRecipe = new Recipe();
		return newRecipe;
	}

	/**
	 * @return imgURL
	 */
	public String getImgURL() {
		return imgURL;
	}

	/**
	 * @param imgURL
	 */
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	
	
	
}