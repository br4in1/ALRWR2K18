/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Sof
 */
public class GuidBoxController implements Initializable {

	@FXML
	private VBox navGuide;
	@FXML
	private JFXButton mainMenu;
	@FXML
	private JFXButton hotel;
 AnchorPane content;
    BorderPane parent;
	@FXML
	private JFXButton manage;
	@FXML
	private JFXButton DisplayStade;
	@FXML
	private JFXButton divertiss;
	
	  
    private void setContentNode(Node node) {
        content.getChildren().clear();
        content.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}	

	@FXML
	private void AfficherHotel(MouseEvent event) {
	}


	
}
