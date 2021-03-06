package application;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.crypto.EncryptedPrivateKeyInfo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
class Cipher
{
	Stage stage;
	Scene scene;
	String key,plaintext,ciphertext;
	long stencrypt,etencrypt,stdecrypt,etdecrypt;
    String alphabet="abcdefghijklmnopqrstuvwxyz";
    String Generatekey() {
    	
    	ArrayList<Integer> permutation = new ArrayList<>();
        for (int i = 0; i < alphabet.length(); i++)
            permutation.add(i);
        Collections.shuffle(permutation);
        
        String key = "", KEY = "";
        for (int j = 0; j < alphabet.length(); j++) {
            key += alphabet.charAt(permutation.get(j));
           
        }
    	return key;
    }
    String encrypt(String key) {
    	 stencrypt= System.nanoTime();
    	    
    	 int i, j;
         for (i = 0; i < plaintext.length(); i++) {
             for (j = 0; j < alphabet.length(); j++) {
                 if (plaintext.charAt(i) == alphabet.charAt(j)) {
                     ciphertext += key.charAt(j);
                     break;
                 }
                 
             }
             if (j == alphabet.length())
                 ciphertext += plaintext.charAt(i);
         }
         etencrypt= System.nanoTime();
         return ciphertext;
    }
    
    String decrypt(String key, String ciphertetxt) {
    	stdecrypt = System.nanoTime();
        int i, j;
        for (i = 0; i < ciphertext.length(); i++) {
            for (j = 0; j < key.length(); j++) {
                if (ciphertext.charAt(i) == key.charAt(j)) {
                    plaintext += alphabet.charAt(j);
                    break;
                }
               
            }
            if (j == key.length())
                plaintext += ciphertext.charAt(i);
        }
        etdecrypt = System.nanoTime();
    	return plaintext;
    }

    Cipher (){
    	stage=new Stage();
    	stage();
    	stage.setTitle("Mono alphabetic Cipher");
		stage.setScene(scene);
		stage.show();
		
    }
   
    void stage() {
    	
    	HBox hb=new HBox(); 
    	Label lb=new Label(" Mono alphabetic Cipher");
    	lb.setFont(new Font("Times new roman", 32));
    	
    	
    	hb.getChildren().add(lb);
    	hb.setAlignment(Pos.CENTER);
    	Label lbltext=new Label("Enter plain text");
    	TextField text=new TextField();
    	Label lblkey=new Label("KeyWord");
    	Label key=new Label();
    	
    	Label encryptext=new Label("Encrypted text");
    	Label encryp=new Label();
    	Label dencryptext=new Label("Decrypted text");
    	Label dencryp=new Label();
    	Button btngeneratekey=new Button("Generate Key");
    	
    	
    	 final CategoryAxis xAxis = new CategoryAxis();
         final NumberAxis yAxis = new NumberAxis();
         final BarChart<String,Number> bc = 
             new BarChart<String,Number>(xAxis,yAxis);
         bc.setTitle("Time complexity");
         xAxis.setLabel("Execution in Milli second ");       
         yAxis.setLabel("Value");
         XYChart.Series series1 = new XYChart.Series();
         XYChart.Series series2 = new XYChart.Series();
         series1.setName("Encryption Execution time");  
         series2.setName("Decryption Execution time");
    	

    	Label label1=new Label("Encryption ");
    	Label sten=new Label("Execution time in milli second");
    	Label sten1=new Label();
    	
    	 
    	Label label2=new Label("Decryption ");
    	Label stde=new Label("Execution time in milli second");
    	Label stde1=new Label();
    	
    	
    	
    	
    	btngeneratekey.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				key.setText(Generatekey());
				
			}
		});
    	
    	
    	Button btnencryp=new Button("Encrypt");
    	btnencryp.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
					if(text.getText().isBlank()){	
					Alert msg=new Alert(AlertType.WARNING);
					msg.setContentText("Enter Plain text");
					msg.show();
					}
					else if(key.getText().isBlank()){	
						Alert msg=new Alert(AlertType.WARNING);
						msg.setContentText("Enter key");
						msg.show();
						}
					else {
				ciphertext=" ";
				encryp.setText(null);
				plaintext=text.getText();
				
				encryp.setText(encrypt(key.getText()));
				long timeElapsed = etencrypt - stencrypt;
				 series1.getData().add(new XYChart.Data("Encryption",timeElapsed));
				 sten1.setText(Long.toString(timeElapsed)); 
					}
			}
		});
    	
    	Button btndencryp=new Button("Decrypt");
    	btndencryp.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(encryp.getText().isBlank()) {
					Alert msg=new Alert(AlertType.WARNING);
					msg.setContentText("First encrypt the plain text");
					msg.show();
				}
				
				
				else if(key.getText().isBlank()) {
					Alert msg=new Alert(AlertType.WARNING);
					msg.setContentText("key is missing");
					msg.show();
					
				}
				else {
				dencryp.setText(null);
				plaintext="";
				dencryp.setText(decrypt(key.getText(), encrypt(key.getText())));
				long timeElapsed = etdecrypt - stdecrypt;
				series2.getData().add(new XYChart.Data("Decryption", timeElapsed));
				stde1.setText(Long.toString(timeElapsed));
			}}
		});
 
    	HBox hb1=new HBox();
    	hb1.setSpacing(20);
    	hb1.getChildren().addAll(lbltext,text);
    	HBox hb2=new HBox();
    	hb2.setSpacing(20);
    	hb2.getChildren().addAll(lblkey,key);
    	HBox hb3=new HBox();
      	hb3.setSpacing(20);
    	hb3.getChildren().addAll(btngeneratekey,btnencryp,btndencryp);
    	HBox hb4=new HBox();
      	hb4.setSpacing(20);
    	hb4.getChildren().addAll(encryptext,encryp);
    	HBox hb5=new HBox();
      	hb5.setSpacing(20);
    	hb5.getChildren().addAll(dencryptext,dencryp);
    	
    	HBox hb6=new HBox();
      	hb6.setSpacing(20);
    	hb6.getChildren().addAll(label1,sten,sten1);
    	HBox hb7=new HBox();
      	hb7.setSpacing(20);
    	hb7.getChildren().addAll(label2,stde,stde1);
    	
    	bc.getData().addAll(series1,series2);
    	
    	VBox vb=new VBox();
    	vb.setSpacing(20);
    	vb.setAlignment(Pos.CENTER);
    	vb.getChildren().addAll(lb,hb1,hb2,hb3,hb4,hb5,hb6,hb7,bc);

    	scene=new Scene(vb,800,800);
    	
    	
    	
    }
}
