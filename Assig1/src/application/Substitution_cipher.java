package application;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import static java.time.temporal.ChronoUnit.MICROS;
import java.io.*;
import java.util.Scanner;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Substitution_cipher {
	Stage stage;
	Scene scene;
	long stencrypt,etencrypt,stdecrypt,etdecrypt;
    public static final String str="abcdefghijklmnopqrstuvwxyz";
    
     public String encrypt(String plaint,int key)
    {  
    stencrypt= System.nanoTime();
    
        plaint=plaint.toLowerCase();
        String ciphert="";
        for(int i=0;i<plaint.length();i++)
        {
            int charpos=str.indexOf(plaint.charAt(i));
            int keyval=(charpos+key)%26;
            char replaceval=str.charAt(keyval);
            ciphert=ciphert+replaceval;
        }
        etencrypt= System.nanoTime();
      
        return ciphert;
    }


   public String decrypt(String ciphert,int key)
    {  
	  
    	stdecrypt = System.nanoTime();
        ciphert=ciphert.toLowerCase();
        String plaint="";
        for(int i=0;i<ciphert.length();i++)
        {
            int charpos=str.indexOf(ciphert.charAt(i));
            int keyval=(charpos-key)%26;
            if(keyval<0)
            {
                keyval=str.length()+keyval;
            }
            char replaceval=str.charAt(keyval);
            plaint=plaint+replaceval;
        }
 
         etdecrypt = System.nanoTime();
        return plaint;
    }

    public Substitution_cipher() {
		// TODO Auto-generated constructor stub
    	stage=new Stage();
    	stage();
    	stage.setTitle("Substitution Cipher");
		stage.setScene(scene);
		stage.show();
    }

    void stage() {
    	
    	
    	
    	HBox hb=new HBox();
    	Label lb=new Label("Substitution ciper");
    	hb.getChildren().add(lb);
    	lb.setFont(new Font("Times new roman", 32));
    	hb.setAlignment(Pos.CENTER);
    	Label lbltext=new Label("Enter plain text");
    	TextField text=new TextField();
    	Label lblkey=new Label("Enter Key");
    	TextField Key=new TextField();
    	Label encryptext=new Label("Encrypted text");
    	Label encryp=new Label();
    	Label dencryptext=new Label("Decrypted text");
    	Label dencryp=new Label();
    	Button btnencryp=new Button("Encrypt");
    	
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
        
        
        
       
    	
    	Label label1=new Label("Encryption");
    	Label sten=new Label("Execution time");
    	Label sten1=new Label();
    	
    	 
    	Label label2=new Label("Decryption");
    	Label stde=new Label("Execution time");
    	Label stde1=new Label();
   
    	
    	btnencryp.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					if(text.getText().isBlank()){	
					Alert msg=new Alert(AlertType.WARNING);
					msg.setContentText("Enter Plain text");
					msg.show();
					}
					else if(Key.getText().isBlank()){	
						Alert msg=new Alert(AlertType.WARNING);
						msg.setContentText("Enter key");
						msg.show();
						}
					else
					{
						int space=Integer.parseInt(Key.getText()); 
						encryp.setText(encrypt(text.getText(), space));
						text.setText(null);
						long timeElapsed = etencrypt - stencrypt;
						series1.getData().add(new XYChart.Data("Encryption",timeElapsed));
						 sten1.setText(Long.toString(timeElapsed)); 
					}
					
							
				 
			        
			      
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
    	
    	Button btndencryp=new Button("Decrypt");
    	btndencryp.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				
				try {
					if(dencryp.getText().isBlank()) {
						Alert msg=new Alert(AlertType.WARNING);
						msg.setContentText("First encrypt the plain text");
						msg.show();
					}
					
					
					else if(Key.getText().isBlank()) {
						Alert msg=new Alert(AlertType.WARNING);
						msg.setContentText("key is missing");
						msg.show();
						
					}
					else {
					
					int space=Integer.parseInt(Key.getText()); 
				dencryp.setText(decrypt(encryp.getText(), space));
				encryp.setText(null);
				long timeElapsed = etdecrypt - stdecrypt;
				series2.getData().add(new XYChart.Data("Decryption", timeElapsed));
				stde1.setText(Long.toString(timeElapsed));}
								}
				catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
    	
    	
    	
    	HBox hb1=new HBox();
    	hb1.setSpacing(20);
    	hb1.getChildren().addAll(lbltext,text);
    	HBox hb2=new HBox();
    	hb2.setSpacing(20);
    	hb2.getChildren().addAll(lblkey,Key);
    	HBox hb3=new HBox();
      	hb3.setSpacing(20);
    	hb3.getChildren().addAll(btnencryp,btndencryp);
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
    	
    	 
         
         
         //Scene scene  = new Scene(lineChart);
         
    	VBox vb=new VBox();
    	bc.getData().addAll(series1,series2);
    	vb.setSpacing(20);
    	vb.setAlignment(Pos.CENTER);
    	vb.getChildren().addAll(lb,hb1,hb2,hb3,hb4,hb5,hb6,hb7,bc);
    	scene=new Scene(vb,800,800);
    	
    	
    	
    }
}
