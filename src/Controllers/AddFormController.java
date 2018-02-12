/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Game;
import Services.GameCrud;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author simo
 */
public class AddFormController implements Initializable {

    @FXML
    private DatePicker GameDate;
    @FXML
    private JFXComboBox<?> HomeTeam;
    @FXML
    private JFXComboBox<?> AwayTeam;
    @FXML
    private JFXComboBox<?> Stadium;
    @FXML
    private JFXTextField Result;
    @FXML
    private JFXTextArea Summary;
    @FXML
    private JFXTextField SummaryPhoto;
    @FXML
    private JFXTextField Highlights;
    @FXML
    private JFXTextField Referee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submit(MouseEvent event) {
         GameCrud.InsertGame(new Game(Date.valueOf(GameDate.getValue()),1,2,Result.getText(),5,Summary.getText(),SummaryPhoto.getText(),Highlights.getText(),Referee.getText()));
    }
    
}
