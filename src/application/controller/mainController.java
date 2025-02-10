/**This controller handle the main.fxml. Which display the all the food that the 
 * user put in. Specifically, there is a tableview, where display the food name, 
 * quantity, expiration day, the box selection, and the recipe button. 
 * 
 * @author Binary Brain
 * UTSA CS 3443 - Final Project
 * Fall 2022
 * */
package application.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import application.Main;
import application.model.Food;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class mainController implements Initializable{

	@FXML Label welcomeLabel;
	@FXML Button addBtn, deleteBtn, logOutBtn;
	// The tableview
	@FXML
	TableView<Food> tableView;
	//Name column
	@FXML
    TableColumn<Food,String> nameCol;
	//Quantity column
    @FXML
    TableColumn <Food, String> qtyCol;
    //Expiration column
    @FXML
    TableColumn<Food, String> expCol;
    //Select box column
    @FXML
    TableColumn<Food, String> selectCol;
    //recipe column
    @FXML
    TableColumn<Food, String> recepieCol;
 // Observable view to store data for table view
    ObservableList<Food> list = FXCollections.observableArrayList();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Create the for loop to get the data and create the new object
   		for(int i=0; i< Main.mainAccount.getFoodList().getArrayList().size(); i++){
   			Food arr= (Food) Main.mainAccount.getFoodList().getArrayList().get(i);
   			list.add(new Food( arr.getIngredientName(), arr.getQuantity(), arr.getExpDate()));
   			// Check on the expiration day
   		if(checkExpiration(arr.getExpDate())){
   				System.out.println("on expire date");
   				/*
   				 * In here, the sendgrid api is being fetch. which send the 
   				 * user notification when their food is expired
   				 * */
   				// The email send from
   		       Email from = new Email("knguyenn72@gmail.com");
   		       Email to = new Email(Main.mainAccount.getEmail()); // use your own email address here
   		       
   		       // The subject and the content
   		       String subject = "Your food expiration day reminder";
   		       Content content = new Content("text/html", " Hi, This email is the reminder that your "+ arr.getIngredientName() +" expired today. So be careful when you try to use it ");

   		       Mail mail = new Mail(from, subject, to, content);
   		       // Create the new SendGrid by the API key
   		       SendGrid sg = new SendGrid("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");
   		       Request request = new Request();
   		       //Send the request to the api
   		    try {
   		       request.setMethod(Method.POST);
   		       request.setEndpoint("mail/send");
   		       request.setBody(mail.build());

   		       Response response;
			
				response = sg.api(request);
			
				// TODO Auto-generated catch block
				
			

   		       System.out.println(response.getStatusCode());
   		       System.out.println(response.getHeaders());
   		       System.out.println(response.getBody());
   		 } catch (IOException e1) {
   			e1.printStackTrace();
   		 }
   	
   		}else {
   				System.out.println("good to use");
   			}
   		}
   		welcomeLabel.setText("Welcome! "+Main.mainAccount.getUserName());
   		// create the cell value for every column
	   	nameCol.setCellValueFactory(new PropertyValueFactory<Food, String>("ingredientName"));
	   	qtyCol.setCellValueFactory(new PropertyValueFactory<Food, String>("quantity"));
	   	expCol.setCellValueFactory(new PropertyValueFactory<Food, String>("expDate"));
	   	selectCol.setCellValueFactory( new PropertyValueFactory<>( "select" ));
	   	selectCol.setCellFactory( tc -> new CheckBoxTableCell<>());
	   	
	   	recepieCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
	   	
	   	// Integrate the action for the delete button to delete the item
	   	deleteBtn.setOnAction( e -> {
	         final ArrayList<Food> del = new ArrayList<Food>();
	         //loop through the tableview item
	         for( final Food deletedFood : tableView.getItems()) {
	            if( deletedFood.selectProperty().get()) {
	            	
	            	del.add( deletedFood );
	            }
	         }
	         // Update the mainAccount Arraylist  
	         Main.mainAccount.getFoodList().removeFood(del);
	         tableView.getItems().removeAll( del );
	      });
	   	
	   	
	   	//Set up button and call them
        Callback<TableColumn<Food, String>, TableCell<Food, String>> cellFactory
                = //
                new Callback<TableColumn<Food, String>, TableCell<Food, String>>() {
        	
            @Override
            public TableCell call(final TableColumn<Food, String> param) {
                final TableCell<Food, String> cell = new TableCell<Food, String>() {

                    final Button btn = new Button("Go to Recepie");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                            	Food food = getTableView().getItems().get(getIndex());
                                Main.recipeIngredient = food.getIngredientName();
                                try {
                        			FXMLLoader loader = new FXMLLoader();
                        			loader.setLocation(getClass().getResource("../view/Recipe.fxml"));

                        			Scene scene = new Scene(loader.load());

                        			Main.stage.setScene(scene);
                        			Main.stage.show();
                        			
                        		} catch(Exception e) {
                        			e.printStackTrace();
                        		}
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

        recepieCol.setCellFactory(cellFactory);
	   	
	   	tableView.setItems(list);
	   	tableView.setEditable( true );
	}
	
	//Take you to the add food page
	@FXML
    public void handle(ActionEvent event) throws IOException {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../view/AddFood.fxml"));

			Scene scene = new Scene(loader.load());

			Main.stage.setScene(scene);
			Main.stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//Logout button that take you register part.
	@FXML
    public void logOutClick(ActionEvent event) throws IOException {
		Main.recipeArray.clear();
		Main.recipeInforArray.clear();
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
	/*In this method, it will check if the day is today or not.
	 * This method will use in the main part in order to check if the 
	 * food is expire or not.
	 * */
	public boolean checkExpiration(String date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
		   LocalDateTime now = LocalDateTime.now(); 
		   System.out.println(dtf.format(now));
		   if(dtf.format(now).equals(date)) {
			   return true;
		   }
		   else {
			   return false;
		   }
		   
		
	}
	

}
