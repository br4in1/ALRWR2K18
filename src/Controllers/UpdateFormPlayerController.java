/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class UpdateFormPlayerController implements Initializable {

    @FXML
    private JFXComboBox<?> id;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField coach;
    @FXML
    private JFXTextField president;
    @FXML
    private JFXTextField area;
    @FXML
    private JFXTextField participation;
    @FXML
    private JFXTextField gamesPlayed;
    @FXML
    private DatePicker date;
    @FXML
    private JFXTextField wcgroupe;
    @FXML
    private JFXTextField fifarank;
    @FXML
    private JFXTextField flagphoto;
    @FXML
    private JFXTextField logophoto;
    @FXML
    private JFXTextField goalScored;
    @FXML
    private JFXTextField goalAgainst;
    @FXML
    private JFXTextField points;
    @FXML
    private JFXTextField squadphoto;
    @FXML
    private JFXTextField descriptionphoto;
    @FXML
    private JFXTextField website;
    @FXML
    private JFXTextField video;
    @FXML
    private JFXTextField win;
    @FXML
    private JFXTextField loose;
    @FXML
    private JFXTextField draw;
    @FXML
    private JFXTextArea description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ChoixId(ActionEvent event) {
    }

    @FXML
    private void photo(MouseEvent event) {
    }

    @FXML
    private void photologo(MouseEvent event) {
    }

    @FXML
    private void photosquad(MouseEvent event) {
    }

    @FXML
    private void photodescription(MouseEvent event) {
    }

    @FXML
    private void submit(MouseEvent event) {
    }
    
}
