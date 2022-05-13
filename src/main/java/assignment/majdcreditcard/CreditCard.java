package assignment.majdcreditcard;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Formatter;
import java.util.concurrent.atomic.AtomicBoolean;

public class CreditCard extends Application {
    boolean isValid=false;

    @Override
    public void start(Stage PrimaryStage) throws IOException {

        //Creating the group and Scene
        Group root = new Group();
        Scene scene = new Scene(root, 300, 160);
        PrimaryStage.setTitle("Credit Card Input");

        //Creating Labels
        Label cardNum = new Label("Card Number:");
        Label expDate = new Label("Expiration Date");
        Label cvv = new Label("CVV");

        //Setting Label properties
        cardNum.setFont(Font.font("Areal",FontWeight.BOLD,15));
        cardNum.setLayoutX(20);
        cardNum.setLayoutY(20);

        expDate.setFont(Font.font("Areal",FontWeight.BOLD,15));
        expDate.setLayoutX(20);
        expDate.setLayoutY(100);

        cvv.setFont(Font.font("Areal",FontWeight.BOLD,15));
        cvv.setLayoutX(190);
        cvv.setLayoutY(100);

        //Creating TextFields
        TextField txtCardNum= new TextField();
        TextField txtExpDate = new TextField();
        TextField txtCvv = new TextField();

        //Setting the textfield properties
        txtCardNum.setPromptText("XXXX XXXX XXXX XXXX");
        txtCardNum.setLayoutX(55);
        txtCardNum.setLayoutY(50);
        txtCardNum.setPrefWidth(210);
        txtCardNum.setPrefHeight(35);

        txtExpDate.setPromptText("mm/yyyy");
        txtExpDate.setLayoutX(20);
        txtExpDate.setLayoutY(120);

        txtCvv.setPromptText("XXX");
        txtCvv.setLayoutX(190);
        txtCvv.setLayoutY(120);
        txtCvv.setMaxWidth(70);

        //Importing Images:
        InputStream VisaStream = new FileInputStream("/Users/Majd/Desktop/UCLA Winter 22/Java II/Module 6 /MajdCreditCard/src/main/java/assignment/majdcreditcard/Visa.png");
        InputStream MasterStream = new FileInputStream("/Users/Majd/Desktop/UCLA Winter 22/Java II/Module 6 /MajdCreditCard/src/main/java/assignment/majdcreditcard/Mastercard.png");
        InputStream AmexStream = new FileInputStream("/Users/Majd/Desktop/UCLA Winter 22/Java II/Module 6 /MajdCreditCard/src/main/java/assignment/majdcreditcard/Amex.png");
        InputStream JcbStream = new FileInputStream("/Users/Majd/Desktop/UCLA Winter 22/Java II/Module 6 /MajdCreditCard/src/main/java/assignment/majdcreditcard/JCB.png");
        InputStream CheckStream = new FileInputStream("/Users/Majd/Desktop/UCLA Winter 22/Java II/Module 6 /MajdCreditCard/src/main/java/assignment/majdcreditcard/Check.png");
        Image Visa= new Image(VisaStream);
        Image Master= new Image(MasterStream);
        Image Amex= new Image(AmexStream);
        Image JCB=new Image(JcbStream);
        Image empty=new Image(InputStream.nullInputStream());
        Image checkMark= new Image(CheckStream);

        //Creating ImageView Object
        ImageView Img= new ImageView();
        ImageView validated=new ImageView();

        //Setting Image Properties
        Img.setFitHeight(20);
        Img.setFitWidth(30);
        Img.setLayoutX(20);
        Img.setLayoutY(55);

        validated.setFitHeight(15);
        validated.setFitWidth(15);
        validated.setLayoutX(240);
        validated.setLayoutY(60);

        //Adding all the elements to the root
        root.getChildren().add(cardNum);
        root.getChildren().add(expDate);
        root.getChildren().add(cvv);
        root.getChildren().add(txtCardNum);
        root.getChildren().add(txtExpDate);
        root.getChildren().add(txtCvv);
        root.getChildren().add(Img);
        root.getChildren().add(validated);


        //event handling the Card Number for validation
        txtCardNum.textProperty().addListener((observable, oldValue, newValue) -> {
            String formatted = "";
            if(newValue.startsWith("4")){
                Img.setImage(Visa);
                if (isSixteenCheck(txtCardNum)){
                    validated.setImage(checkMark);
                    isValid=true;
                    System.out.println(isValid);
                }
                else validated.setImage(empty);
            }
            else if(newValue.startsWith("5")){
                Img.setImage(Master);
                if (isSixteenCheck(txtCardNum)){validated.setImage(checkMark);}
                else validated.setImage(empty);
            }
            else if(newValue.startsWith("34")||newValue.startsWith("37")){
                Img.setImage(Amex);
                if (isSixteenCheck(txtCardNum)){validated.setImage(checkMark);}
                else validated.setImage(empty);
            }
            else if(newValue.startsWith("35")){
                Img.setImage(JCB);
                if (isSixteenCheck(txtCardNum)){validated.setImage(checkMark);}
                else validated.setImage(empty);
            }
            else{
                Img.setImage(empty);
            }
//            This Code below works a bit different. It only shows the card logo
//            when the full 16 digit number is typed in. I like the above method
//            since it starts showing you the type of card as you are inputting it
//            which I think is a better UI experience
//            *******************************************************************
//            if (isSixteenCheck(txtCardNum)) {
//                validated.setImage(checkMark);
//                if (newValue.startsWith("4")) {
//                    Img.setImage(Visa);
//                } else if (newValue.startsWith("5")) {
//                    Img.setImage(Master);
//                } else if (newValue.startsWith("34") || newValue.startsWith("37")) {
//                    Img.setImage(Amex);
//                } else if (newValue.startsWith("35")) {
//                    Img.setImage(JCB);
//                } else {
//                    Img.setImage(empty);
//                }
//            }else{
//                validated.setImage(empty);
//            }
        });

        System.out.println(isSixteenCheck(txtCardNum));
        PrimaryStage.setScene(scene);
        PrimaryStage.show();
    }

    //A function to check if the card number is 16 digits (Referenced in the txtCardnum Event handling)
    private boolean isSixteenCheck (TextField value){
        int length = value.getLength();
        boolean isSixteen;
        if(length==16){isSixteen=true;}
        else isSixteen=false;
        return isSixteen;
    }

    public static void main(String[] args) {
        launch();
    }
}