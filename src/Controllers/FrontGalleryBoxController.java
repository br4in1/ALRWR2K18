/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class FrontGalleryBoxController implements Initializable {

	@FXML
	private VBox nav;
	@FXML
	private Label AddPhoto;
	@FXML
	private Label ShowImage;
	@FXML
	private Label myPhotos;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}	

	@FXML
	private void unhighlightAdd(MouseEvent event) {
	}

	@FXML
	private void highlightAdd(MouseEvent event) {
	}

	@FXML
	private void unhighlighConsulter(MouseEvent event) {
	}

	@FXML
	private void highlightConsulter(MouseEvent event) {
	}

	@FXML
	private void unhighlightConsulter2(MouseEvent event) {
	}

	@FXML
	private void highlightConsulter2(MouseEvent event) {
	}
	
}
