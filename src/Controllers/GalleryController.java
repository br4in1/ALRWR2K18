/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class GalleryController implements Initializable {

    @FXML
    private VBox nav;
    @FXML
    private JFXButton mainMenu;
    @FXML
    private JFXButton managegallery;
	@FXML
	private JFXButton Showw3;
	@FXML
	private JFXButton stat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gamesContent(MouseEvent event) {
    }
    
}
