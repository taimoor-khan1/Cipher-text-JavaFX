package application;
	

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Main extends Application {
//	Stage stage;
//	Scene scene;
//	Main (){
//		stage=new Stage();
//    	stage.setTitle("Assignment no 1");
//		stage.setScene(scene);
//		stage.show();
//	}
	
	@Override
	public void start(Stage Stage) {
		try {
			Stage.setTitle("Assignment no 1");
			Image ima=new Image("images/MainBackground.jpeg");
			ImageView imview=new ImageView(ima);
			Label lb=new Label("Assignment 1");
			lb.setFont(new Font("Times new roman", 32));
			lb.setTextFill(Color.WHITE);
			
			VBox vb=new VBox();
			vb.setSpacing(20);
			vb.getChildren().add(lb);
			Button cipher1=new Button("Caesar Cipher");
			cipher1.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					Substitution_cipher c=new Substitution_cipher();
					Stage.hide();
					
					
				}
			});
			Button cipher2=new Button("Mono alphabetic Cipher");
			cipher2.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					Cipher c=new Cipher();
					Stage.hide();
				}
			});
			
			vb.getChildren().add(cipher1);
			vb.getChildren().add(cipher2);
			Label lbgroup=new Label("Group Members");
			lbgroup.setTextFill(Color.WHITE);
			Label mem1=new Label("Name : Muhammad Taimoor khan , Reg: 45163 ");
			Label mem2=new Label("Name : Midhat , Reg : 42452");
			Label mem3=new Label("Name : Shoaib Khan , Reg : 42334 ");
			mem1.setTextFill(Color.WHITE);
			mem2.setTextFill(Color.WHITE);
			mem3.setTextFill(Color.WHITE);
			vb.getChildren().addAll(lbgroup,mem1,mem2,mem3);
			Group root=new Group();
			root.getChildren().addAll(imview,vb);
			Scene scene = new Scene(root,900,430);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage.setScene(scene);
			Stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
