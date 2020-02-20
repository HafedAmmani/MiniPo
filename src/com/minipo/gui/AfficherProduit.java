package com.minipo.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class AfficherProduit extends Application {
	
	private FXMLLoader loader;
	
	@Override
	public void start(Stage primaryStage) {
		try {
            Parent root = FXMLLoader
        .load(getClass().getResource("AfficherProduit.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("AfficherProduit.css").toExternalForm());
            primaryStage.setTitle("Espace Produit");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}