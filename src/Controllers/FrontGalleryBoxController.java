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
	private Label AddImage;
	@FXML
	private Label ShowGallery;
	@FXML
	private Label ShowMyGallery;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@FXML
	private void unhighlightAdd(MouseEvent event) {
		AddImage.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");

	}

	@FXML
	private void highlightAdd(MouseEvent event) {

		AddImage.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");

	}

	@FXML
	private void unhighlighConsulter(MouseEvent event) {

		ShowGallery.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");

	}

	@FXML
	private void highlightConsulter(MouseEvent event) {
		ShowGallery.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");

	}

	@FXML
	private void unhighlightConsulter2(MouseEvent event) {
		ShowMyGallery.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");

	}

	@FXML
	private void highlightConsulter2(MouseEvent event) {
		ShowMyGallery.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");
	}

}
