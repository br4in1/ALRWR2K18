package com.mycompany.sofienepidev;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sof
 */
public class Ajout_HotelController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField nb_etoile;
    @FXML
    private TextField city;
    @FXML
    private TextField latitude;
    @FXML
    private TextField longtitude;
    @FXML
    private TextField image;
    @FXML
    private Button anuler;
    @FXML
    private Button enregistrer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Enregistrement(ActionEvent event) {
    }

    @FXML
    private void RetourALAMap(ActionEvent event) throws IOException {
         Parent Hotel_Page = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene = new Scene(Hotel_Page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add("/styles/Styles.css");
        app_stage.setTitle("GMaps Coupe Du Monde 2018");
        app_stage.setScene(scene);
        app_stage.show();
       
    }
    
}
