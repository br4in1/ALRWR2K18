package Views;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author simo
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
      //  Parent root = FXMLLoader.load(getClass().getResource("login_form.fxml"));
      Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        primaryStage.setTitle("World Cup 2018");
        primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
        primaryStage.show();
        //
    }


    public static void main(String[] args) {
        launch(args);
    }
}
