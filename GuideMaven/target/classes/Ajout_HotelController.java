/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
