package com.library.start;

import com.library.business.Library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    	Library.read();
        Parent root = FXMLLoader.load(getClass().getResource("../login/login.fxml"));
        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
