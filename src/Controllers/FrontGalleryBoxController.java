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

/**
 * FXML Controller class
 *
 * @author dell
 */
public class FrontGalleryBoxController implements Initializable {

	@FXML
	private Label myPhotos;
	@FXML
	private Label AddPhoto;
	@FXML
	private Label ShowImage;


	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}	

	@FXML
	private void unhighlightAdd(MouseEvent event) {
		AddPhoto.setStyle("-fx-background-color: #none; -fx-background-radius : 3px; -fx-text-fill: #000;");
	}

	@FXML
	private void highlightAdd(MouseEvent event) {
		AddPhoto.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");

	}

	@FXML
	private void unhighlighConsulter(MouseEvent event) {
		ShowImage.setStyle("-fx-background-color: #none; -fx-background-radius : 3px; -fx-text-fill: #000;");
		
	}

	@FXML
	private void highlightConsulter(MouseEvent event) {
		ShowImage.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");
	}


	@FXML
	private void unhighlightConsulter2(MouseEvent event) {
		myPhotos.setStyle("-fx-background-color: #none; -fx-background-radius : 3px; -fx-text-fill: #000;");
	}

	@FXML
	private void highlightConsulter2(MouseEvent event) {
		myPhotos.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");
	}
	
}
