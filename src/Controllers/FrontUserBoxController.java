/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author br4in
 */
public class FrontUserBoxController implements Initializable {

	@FXML
	private VBox nav;
	@FXML
	private Label myProfile;
	@FXML
	private Label editData;
	@FXML
	private Label favoris;
	@FXML
	private Label myPhotos;
	public static String selected;
	public static FrontUserBoxController thisController;
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		thisController = this;
		selected = "myprofile";
		myProfile.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");
	}

	public void unhighlightAll(){
		myProfile.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		editData.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		favoris.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		myPhotos.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
	}

	@FXML
	private void unhighlightMyProfile(MouseEvent event) {
		if(!selected.equals("myprofile"))
			myProfile.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
	}

	@FXML
	public void highlightMyProfile(MouseEvent event) {
		if(!selected.equals("myprofile"))
			myProfile.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");
	}

	@FXML
	private void unhighlightEdit(MouseEvent event) {
		if(!selected.equals("edit"))
			editData.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
	}

	@FXML
	public void highlightEdit(MouseEvent event) {
		if(!selected.equals("edit"))
			editData.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");
	}

	@FXML
	private void unhighlightFavoris(MouseEvent event) {
		if(!selected.equals("favoris"))
			favoris.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
	}

	@FXML
	private void highlightFavoris(MouseEvent event) {
		if(!selected.equals("favoris"))
			favoris.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");
	}

	@FXML
	private void unhighlightPhotos(MouseEvent event) {
		if(!selected.equals("photos"))
			myPhotos.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
	}

	@FXML
	private void highlightPhotos(MouseEvent event) {
		if(!selected.equals("photos"))
			myPhotos.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");
	}

	@FXML
	private void getContent(MouseEvent event) {
	}
	
}
