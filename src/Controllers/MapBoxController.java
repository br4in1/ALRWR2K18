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
 * @author Sof
 */
public class MapBoxController implements Initializable {

	@FXML
	private VBox nav;
	@FXML
	private Label VisualizeMap;
	@FXML
	private Label Hotels;
	@FXML
	private Label Stadiums;
	@FXML
	private Label Entertanment;
	@FXML
	private Label Translate;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}	

	@FXML
	private void unhighlightMyProfile(MouseEvent event) {
	}

	@FXML
	private void highlightMyProfile(MouseEvent event) {
	}

	@FXML
	private void unhighlightEdit(MouseEvent event) {
	}

	@FXML
	private void highlightEdit(MouseEvent event) {
	}

	@FXML
	private void getContent(MouseEvent event) {
	}

	@FXML
	private void unhighlightFavoris(MouseEvent event) {
	}

	@FXML
	private void highlightFavoris(MouseEvent event) {
	}

	@FXML
	private void unhighlightPhotos(MouseEvent event) {
	}

	@FXML
	private void highlightPhotos(MouseEvent event) {
	}
	
}
