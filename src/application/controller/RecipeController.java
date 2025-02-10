/** In this controller, it will fetch the api from Spoonacular
 * in order to fetch the recipe of the food. There will be 6
 * main food in there. And when they choose each food, it 
 * will show them the summary and the instruction to cook it.
 * 
 * @author Binary Brain
 * UTSA CS 3443 - Final Project
 * Fall 2022
 * */
package application.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.ResourceBundle;



import application.Main;
import application.model.Recipe;
import application.model.RecipeInfor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class RecipeController implements Initializable{

   // Header of the page
	@FXML
	Label header;
	/** The first information of the first pane contains
	 *  the title, carbs, protein, calories and fat.
	 * */
    @FXML
    Label title_0;
    
    @FXML
    Label carbs_0;

    @FXML
    Label protein_0;

    @FXML
    Label fat_0;

    @FXML
    Label calories_0; 
	/** The second information of the first pane contains
	 *  the title, carbs, protein, calories and fat.
	 * */
    @FXML
    Label title_1;
    
    @FXML
    Label carbs_1;

    @FXML
    Label protein_1;

    @FXML
    Label fat_1;

    @FXML
    Label calories_1;
    
	/** The third information of the first pane contains
	 *  the title, carbs, protein, calories and fat.
	 * */
    @FXML
    Label title_2;
    
    @FXML
    Label carbs_2;

    @FXML
    Label protein_2;

    @FXML
    Label fat_2;

    @FXML
    Label calories_2;
    
	/** The fourth information of the first pane contains
	 *  the title, carbs, protein, calories and fat.
	 * */
    @FXML
    Label title_3;
    
    @FXML
    Label carbs_3;

    @FXML
    Label protein_3;

    @FXML
    Label fat_3;

    @FXML
    Label calories_3;
    
	/** The fifth information of the first pane contains
	 *  the title, carbs, protein, calories and fat.
	 * */
    @FXML
    Label title_4;
    
    @FXML
    Label carbs_4;

    @FXML
    Label protein_4;

    @FXML
    Label fat_4;

    @FXML
    Label calories_4;
    
	/** The sixth information of the first pane contains
	 *  the title, carbs, protein, calories and fat.
	 * */
    @FXML
    Label title_5;
    
    @FXML
    Label carbs_5;

    @FXML
    Label protein_5;

    @FXML
    Label fat_5;

    @FXML
    Label calories_5;
    // The title array
    @FXML
	Label[] titleArray;
    // The protein array
    @FXML
	Label[] ProteinArray;
    // The fat array
    @FXML
   	Label[] fatArray;
    // The calories array
    @FXML
   	Label[] caloriesArray;
    // The carb array
    @FXML
   	Label[] carbArray;
   
   // Variable for all the pane. 
   @FXML
    AnchorPane pane_0;

    @FXML
    AnchorPane pane_2;

    @FXML
    AnchorPane pane_1;

    @FXML
    AnchorPane pane_4;

    @FXML
    AnchorPane pane_3;

    @FXML
    AnchorPane pane_5;
    // Pane array
    @FXML
    AnchorPane[] paneArray = new AnchorPane[]{pane_0,pane_1,pane_2,pane_3,pane_4,pane_5};

    // ImageView array
    @FXML
    ImageView img_0, img_1, img_2, img_3, img_4, img_5 ;
    ImageView[] imgArray;
    
    // Button array
    @FXML
    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5;
    Button[] btnArray;
    @FXML
    Button test;
    //The id arrayList
    ArrayList <String> ids = new ArrayList<String>();

    //Summary button
    @FXML
     Button summary;
    //Instruction button
    @FXML
    Button instruction;
    // Label for information and titles
    @FXML
   Label infor, titles;
    

    @FXML
    Button back;  
    //Scroll bar
    @FXML
   ScrollPane scroll;
    @FXML
    AnchorPane informationPane;
    @FXML
    Button backBtn;
    
    /*The initialize will call the method from the Recipe.java in order to fetch all the 
     * information.
     * */
    public void initialize(URL location, ResourceBundle resources) {
    	// Set the informationPane visible in the beginning
    	informationPane.setVisible(false);
    	// Create the new array for each element
        titleArray = new Label[] {title_0,title_1,title_2,title_3,title_4,title_5};
    	ProteinArray = new Label[] {protein_0,protein_1,protein_2,protein_3,protein_4,protein_5};
    	fatArray = new Label[] {fat_0,fat_1,fat_2,fat_3,fat_4,fat_5};
    	caloriesArray = new Label[] {calories_0,calories_1,calories_2,calories_3,calories_4,calories_5};
    	carbArray = new Label[] {carbs_0,carbs_1,carbs_2,carbs_3,carbs_4,carbs_5};
    	imgArray = new ImageView[] {img_0, img_1, img_2, img_3, img_4, img_5};
    	btnArray = new Button[] {btn_0,btn_1,btn_2,btn_3,btn_4,btn_5};
    	int count = 0;
    	
    	//newRecipe.fetchRecipe();
    	header.setText("The Recommedation Recipe For: "+Main.recipeIngredient);
    	 Recipe.fetchRecipe();
    	 System.out.println("main arr "+ Main.recipeArray.size());
    	 // Loop through the recipeArray to fetch the data
    	for(Recipe newRec : Main.recipeArray) {
    		// Set the data to what it correspond
    		titleArray[count].setText(newRec.getTitle());
    		caloriesArray[count].setText("Calories: "+ String.valueOf(newRec.getCalories()));
    		ProteinArray[count].setText("Protein: " +newRec.getProtetin());
    		fatArray[count].setText("Fat: "+ newRec.getFat());
    		carbArray[count].setText("Carb: " + newRec.getCarbs());	
    		// Fetch the image. 
    		Image newImg;
			try {
				// Create the image because url for this image is online URL
				newImg = createImage(newRec.getImgURL());
				//Check if is there any error with image or not.
				if(newImg.isError()) {
	    			System.out.println("Error loading image from "+ newRec.getImgURL());
	    			 newImg.getException().printStackTrace();
	    		}else {
	    			 System.out.println("Successfully loaded image from" + newRec.getImgURL());
	    		}
				//Set image
			imgArray[count].setImage(newImg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    		;
    		
    		
    		
    		ids.add(newRec.getId());
    		 System.out.println(newRec.getImgURL());
    		count++;
    	};    	
    }
    
    //Create the image from online URL.
    Image createImage(String url)
    		throws IOException {
    		    URLConnection conn = new URL(url).openConnection();
    		    conn.setRequestProperty("User-Agent", "Wget/1.13.4 (linux-gnu)");

    		    try (InputStream stream = conn.getInputStream()) {
    		        return new Image(stream);
    		    }
    		}
 
    /* In this event, when the user click, it will open the information's recipe page.
     * And the page for bunch of recipe name will be viible
     *  
     * */
	@FXML
    public void handle(MouseEvent event) {
    	if(event.getSource() == btn_0 || event.getSource() == btn_1 || event.getSource() == btn_2 || event.getSource() == btn_3
    			|| event.getSource() == btn_4 || event.getSource() == btn_5) {
    		// Get the id of the pane that clicked by user. This is used to locate which food the user choose 
    		String id_btn = ((Button) event.getSource()).getId(); 
    				
    			if(id_btn.equals("btn_0")) {
    				Main.idQuery = ids.get(0);
    			}
    			else if(id_btn.equals("btn_1")) {
    				Main.idQuery = ids.get(1);
    			}
    			else if(id_btn.equals("btn_2")) {
    				Main.idQuery = ids.get(2);
    			}
    			else if(id_btn.equals("btn_3")) {
    				Main.idQuery = ids.get(3);
    			}
    			else if(id_btn.equals("btn_4")) {
    				Main.idQuery = ids.get(4);
    			}
    			else if(id_btn.equals("btn_5")) {
    				Main.idQuery = ids.get(5);
    			}
    			
    			System.out.println("id_btn: "+ id_btn);
    			System.out.println("id from controller: " +Main.idQuery);
    			
    			// The old seciton is visible
    			backBtn.setVisible(false);
    			header.setVisible(false);
    			pane_0.setVisible(false);
    			pane_1.setVisible(false);
    			pane_2.setVisible(false);
    			pane_3.setVisible(false);
    			pane_4.setVisible(false);
    			pane_5.setVisible(false);
    			informationPane.setVisible(true);
    			
    }
    

    }
	// When click this, it will close the information view and open the recipe list view
    @FXML
  public void click(ActionEvent event) {

    	backBtn.setVisible(true);
    	header.setVisible(true);
		pane_0.setVisible(true);
		pane_1.setVisible(true);
		pane_2.setVisible(true);
		pane_3.setVisible(true);
		pane_4.setVisible(true);
		pane_5.setVisible(true);
		
		informationPane.setVisible(false);
		scroll.setContent(null);
   }
  
  
  /* In this event, it will fetch the information from the RecipeInfor that show the recipe information
   * */  
    @FXML
    public void select(ActionEvent event) {
    	// Fetch the data
    	RecipeInfor.fetchRecipeInfor();
    	RecipeInfor  recipeInformation = new RecipeInfor();
    	// if user click on summary button, show summary text
    	if(event.getSource().equals(summary)) {
    		
    		
    		Label summaryText = new Label(RemoveHTMLTags(recipeInformation.getSummary()));
    		summaryText.setPrefWidth(559);
    		summaryText.setWrapText(true);
    		summaryText.setFont(Font.font("regular",18));
    		summaryText.setLineSpacing(2.0);
    		scroll.setContent(summaryText);
    		
    		scroll.setFitToWidth(true);
    	
    	}
    	// if user click on instruction button, show instruction text
    	else if (event.getSource().equals(instruction)) {
    		
    		Label instructionText = new Label(removeExtraSpace(recipeInformation.getInstruction()));
    		instructionText.setPrefWidth(559);
    		instructionText.setWrapText(true);
    		instructionText.setFont(Font.font("regular",18));
    		instructionText.setLineSpacing(2.0);
    		scroll.setContent(instructionText);
    		
    		scroll.setFitToWidth(true);
    	}
    }
    //Back button will take the user to the main page
    @FXML
    public void backClick(ActionEvent event) {
    	Main.recipeArray.clear();
    	Main.recipeInforArray.clear();
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
    //This will modified the string which come with html tag. because
    //when I fetch the api, the text come with html tag.
    public  String RemoveHTMLTags(String str)
    { 
        str = str.replaceAll("<[^>]*>", ""); 
        return str;
    }
    //This will remove the extra space in the string.
    public String removeExtraSpace(String s) {
    	String str = s.replaceAll("\\s+", " ");
    	return str;
    }
}
