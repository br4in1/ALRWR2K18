/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.SimpleUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author br4in
 */
public class FrontEndController implements Initializable {

	@FXML
	private Label homebutton;
	@FXML
	private Label fixturesbtn;
	@FXML
	private Label teamsbtn;
	@FXML
	private Label hotelsbtn;
	@FXML
	private Label stadesbtn;
	@FXML
	private Label gallerybtn;
	@FXML
	private Label contactbtn;
	@FXML
	private Label firstlastname;
	@FXML
	private Circle profilepic;
	private String current_page;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		current_page = "home";
		homebutton.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
		firstlastname.setText(SimpleUser.current_user.getUsername());
		profilepic.setFill(new ImagePattern(new Image(SimpleUser.current_user.getProfilepicture())));
	}

	@FXML
	private void highlightFixturesbtn(MouseEvent event) {
		if(!current_page.equals("fixtures"))
			fixturesbtn.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
	}

	@FXML
	private void unhighlightFixturesbtn(MouseEvent event) {
		if(!current_page.equals("fixtures"))
			fixturesbtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
	}

	@FXML
	private void unhighlighthomebtn(MouseEvent event) {
		if(!current_page.equals("home"))
			fixturesbtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		
	}

	@FXML
	private void highlighthomebtn(MouseEvent event) {
		if(!current_page.equals("home"))
			fixturesbtn.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
	}

	@FXML
	private void unhighlightteamsbtn(MouseEvent event) {
		if(!current_page.equals("teams"))
			teamsbtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
	}

	@FXML
	private void highlightteamsbtn(MouseEvent event) {
		if(!current_page.equals("teams"))
			teamsbtn.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
	}

	@FXML
	private void unhighlighthotelsbtn(MouseEvent event) {
		if(!current_page.equals("hotels"))
			hotelsbtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
	}

	@FXML
	private void highlighthotelsbtn(MouseEvent event) {
		if(!current_page.equals("hotels"))
			hotelsbtn.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
	}

	@FXML
	private void unhighlightstadesbtn(MouseEvent event) {
		if(!current_page.equals("stades"))
			stadesbtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
	}

	@FXML
	private void highlightstadesbtn(MouseEvent event) {
		if(!current_page.equals("stades"))
			stadesbtn.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
	}

	@FXML
	private void unhighlightgallerybtn(MouseEvent event) {
		if(!current_page.equals("gallery"))
			gallerybtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
	}

	@FXML
	private void highlightgallerybtn(MouseEvent event) {
		if(!current_page.equals("gallery"))
			gallerybtn.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
	}

	@FXML
	private void highlightcontactbtn(MouseEvent event) {
		if(!current_page.equals("contact"))
			contactbtn.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
	}

	@FXML
	private void unhighlightcontactbtn(MouseEvent event) {
		if(!current_page.equals("contact"))
			contactbtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
	}
}
