/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.HTMLEditor;

/**
 * FXML Controller class
 *
 * @author raiiz
 */
public class AjouterArticleController implements Initializable {

    @FXML
    private ScrollPane ajouterArticlePane;
    @FXML
    private JFXTextField tfTitre;
    @FXML
    private HTMLEditor contenu;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
