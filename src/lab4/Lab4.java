/*
 * Name: Enrique Cahua
 * ID: 0523964
 * Date: 2/10/2017
 * Lab #: 4
 * Description: JavFX War Card Game
*/
package lab4;
import javafx.scene.text.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.image.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.geometry.*;
import javafx.scene.input.MouseEvent;

public class Lab4 extends Application {
    //variables declared in Lab4 in order to be accessible from all the methods
   public boolean rightsTurn = true;
    int leftCounter = 0;
    int rightCounter = 0;
    int rightVal = 0;
    int leftVal = 0;
    //creating Textfields
    TextField tf1 = new TextField();
    TextField tf2 = new TextField();
     //creating cards
    Label lblCardLeft = new Label();
    Label lblCardRight = new Label();
    Label lblCardDeck = new Label();
    
    Label testNum1 =  new Label("");
    Label testNum2 =  new Label("");
    
   // @Override
    public void start(Stage primaryStage) {
        
        //fonts and colors
        Font font = new Font("Courier", 25);
        Font font2 = new Font ("Courier New", 20);
        Color clr = Color.web("#0076a3");
        Color clr2 = Color.web("#FF0000");
         
        // label for scores 
        Label lblscore = new Label();
        lblscore.setFont(font);
        lblscore.setTextFill(clr);
        lblscore.setText("Score: ");
        
        //labels for textfields
        Label left = new Label("Left:" );
        Label right = new Label("Right:");
        
        //creating reset button
        Button btnreset =  new Button();
        btnreset.setText("reset");
        
        //Declaring variables used to assign image file name
        int num;
        String filename = null;
        int num2;
        String filename2 = null;
        
        //reset action event. Resets all variables of game
        btnreset.setOnAction (new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rightVal = 0;
                leftVal = 0;
                rightCounter = 0;
                leftCounter = 0;
                tf1.setText( "0");
                tf2.setText("0");
                rightsTurn = true;
//                testNum1.setText("");
//                testNum2.setText("");
                resetCardImages(lblCardLeft,lblCardDeck,lblCardRight);
            }
        });
        // Event Listener for when the center card is clicked
        lblCardDeck.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Random random = new Random();
                if (rightsTurn == true) {
                    int num = random.nextInt(154 - 101 + 1) + 101;
                    rightVal = num;
                    String filename = Integer.toString(num) + ".gif";
                    lblCardRight.setGraphic(new ImageView("file:img\\"+filename));
//                    testNum1.setText(filename);
//                    ^test variable used to display filename on the canvas
                    rightsTurn = false;
                }
                else  {
                    int num2 = random.nextInt(154 - 101 + 1) + 101;
                    String filename2 = Integer.toString(num2) + ".gif";
                    leftVal =  num2;
                    lblCardLeft.setGraphic(new ImageView("file:img\\"+filename2));
//                    testNum2.setText(filename2);
//                    ^test variable used to display filename on the canvas

                        if (rightVal > leftVal) {
                          rightCounter = rightCounter + 1; 
                          String rightScore = Integer.toString(rightCounter);
                          tf2.setText(rightScore);
                          
                        }
                        else if (leftVal > rightVal) {
                          leftCounter = leftCounter + 1;
                          String leftScore = Integer.toString(leftCounter);
                          tf1.setText(leftScore);
                          
                        }
                        else {
                            //nada
                        }
                        rightsTurn = !rightsTurn;
                }
            } 
        });
        //Creating an image item and adding to a Label
        Image imgCard = new Image("file:img\\"+filename);      
        Label lblCard = new Label("",new ImageView(imgCard));
        lblCard.setGraphic(new ImageView(imgCard));  
        // Creating BorderPane and GridPane layouts, setting GridPane to be top of BorderPane and middle
        BorderPane root = new BorderPane(); //root layout
        GridPane top = new GridPane(); //top layout for scores
        GridPane middle = new GridPane(); //center layout for cards
        root.setTop(top);
        root.setCenter(middle);
        root.setBottom(btnreset);
        top.setHgap(20);
        top.setVgap(10);
        top.add(lblscore, 0, 0);
        top.add(new Label("left:" ),0 ,2 );
        top.add(new Label("right:" ),2 ,2 );
        top.add(tf1,1, 2);
        top.add(tf2,3, 2);
        middle.add(lblCardLeft,0,0); //add card left
//        middle.add(testNum2,0,1);
        middle.add(lblCardDeck,1,0); //add card center
        middle.add(lblCardRight,2,0); // add card right
//        middle.add(testNum1,2,1);
        middle.setHgap(20); //create space between cards
        middle.setAlignment(Pos.CENTER); // determine where the cards will alighn
        Scene scene = new Scene(root, 600, 450);
        primaryStage.setTitle("Assignment 4");
        primaryStage.setScene(scene);
        primaryStage.show();
        this.resetCardImages(lblCardLeft,lblCardDeck,lblCardRight);
    }
     public void resetCardImages(Label x, Label y, Label z) {
        // reset the card images
        Image imgCardLeft = new Image("file:img\\155.gif");
        x.setGraphic(new ImageView(imgCardLeft));
        Image imgCardDeck = new Image("file:img\\155.gif");
        y.setGraphic(new ImageView(imgCardDeck));
        Image imgCardRight = new Image("file:img\\155.gif");
        z.setGraphic(new ImageView(imgCardRight));
    }
    public static void main(String[] args) {
        launch(args);
    }
}