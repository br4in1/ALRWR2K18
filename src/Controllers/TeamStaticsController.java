/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;
 
/**
 * FXML Controller class
 *
 * @author Moez
 */
public class TeamStaticsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private PieChart pieChart ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList <PieChart.Data> pieChartDatas = FXCollections.observableArrayList(
        new PieChart.Data("wins",12) ,
                new PieChart.Data("looses",23) ,
                new PieChart.Data("draws",50) 
        );
        pieChart.setData(pieChartDatas);    
    }    
    
    
}
