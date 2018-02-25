/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.SimpleUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author br4in
 */
public class FrontEndController implements Initializable {

	public static FrontEndController thisController;
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
	VBox userBox, gallerybox;
	Pane add, gallery, profile,teams,editProfile;
	@FXML
	private AnchorPane sidebar;
	@FXML
	private AnchorPane mainContent;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		thisController = this;
		current_page = "home";
		homebutton.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
		firstlastname.setText(SimpleUser.current_user.getUsername());
		profilepic.setFill(new ImagePattern(new Image(SimpleUser.current_user.getProfilepicture())));

		try {
			userBox = FXMLLoader.load(getClass().getResource("/Views/FrontUserBox.fxml"));
			profile = FXMLLoader.load(getClass().getResource("/Views/myProfile.fxml"));
			editProfile = FXMLLoader.load(getClass().getResource("/Views/editProfile.fxml"));
			for (Node node : userBox.getChildren()) {
				node.addEventHandler(MouseEvent.MOUSE_CLICKED, (k) -> {
					switch (node.getId()) {
						case "myProfile":
							MyProfileController.current_username = SimpleUser.current_user.getUsername();
							MyProfileController.thisController.show();
							FrontUserBoxController.thisController.unhighlightAll();
							FrontUserBoxController.thisController.highlightMyProfile(k);
							FrontUserBoxController.selected = "myprofile";
							setContentNode(profile);
							break;
						case "editData":
							FrontUserBoxController.thisController.unhighlightAll();
							FrontUserBoxController.thisController.highlightEdit(k);
							FrontUserBoxController.selected = "edit";
							setContentNode(editProfile);
							break;
						case "favoris":
							//setContentNode(teamStatics);
							break;
						case "myPhotos":
							//setContentNode(players);
							break;

					}
				});
			}
			add = FXMLLoader.load(getClass().getResource("/Views/AddImage.fxml"));
			gallery = FXMLLoader.load(getClass().getResource("/Views/Showall.fxml"));
			//mygallery = FXMLLoader.load(getClass().getResource("/Views/DisplayI.fxml"));
			gallerybox = FXMLLoader.load(getClass().getResource("/Views/FrontGalleryBox.fxml"));
			for (Node node : gallerybox.getChildren()) {
				node.addEventHandler(MouseEvent.MOUSE_CLICKED, (k) -> {
					switch (node.getId()) {
						case "AddImage":
							setContentNode(add);
							break;
						case "ShowGallery":
							setContentNode(gallery);
							break;
					//	case "ShowMyGallery":
							//setContentNode(players);
						//	break;

					}
				});
			}
		} catch (IOException ex) {
			Logger.getLogger(FrontEndController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void highlightFixturesbtn(MouseEvent event) {
		if (!current_page.equals("fixtures")) {
			fixturesbtn.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
		}
	}

	@FXML
	private void unhighlightFixturesbtn(MouseEvent event) {
		if (!current_page.equals("fixtures")) {
			fixturesbtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		}
	}

	@FXML
	private void unhighlighthomebtn(MouseEvent event) {
		if (!current_page.equals("home")) {
			homebutton.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		}

	}

	@FXML
	private void highlighthomebtn(MouseEvent event) {
		if (!current_page.equals("home")) {
			homebutton.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
		}
	}

	@FXML
	private void unhighlightteamsbtn(MouseEvent event) {
		if (!current_page.equals("teams")) {
			teamsbtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		}
	}

	@FXML
	private void highlightteamsbtn(MouseEvent event) {
		if (!current_page.equals("teams")) {
			teamsbtn.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
		}
	}

	@FXML
	private void unhighlighthotelsbtn(MouseEvent event) {
		if (!current_page.equals("hotels")) {
			hotelsbtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		}
	}

	@FXML
	private void highlighthotelsbtn(MouseEvent event) {
		if (!current_page.equals("hotels")) {
			hotelsbtn.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
		}
	}

	@FXML
	private void unhighlightstadesbtn(MouseEvent event) {
		if (!current_page.equals("stades")) {
			stadesbtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		}
	}

	@FXML
	private void highlightstadesbtn(MouseEvent event) {
		if (!current_page.equals("stades")) {
			stadesbtn.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
		}
	}

	@FXML
	private void unhighlightgallerybtn(MouseEvent event) {
		if (!current_page.equals("gallery")) {
			gallerybtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		}
	}

	@FXML
	private void highlightgallerybtn(MouseEvent event) {
		if (!current_page.equals("gallery")) {
			gallerybtn.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
		}
	}

	@FXML
	private void highlightcontactbtn(MouseEvent event) {
		if (!current_page.equals("contact")) {
			contactbtn.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
		}
	}

	@FXML
	private void unhighlightcontactbtn(MouseEvent event) {
		if (!current_page.equals("contact")) {
			contactbtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		}
	}

	private void setNavNode(Node node) {
		sidebar.getChildren().clear();
		sidebar.getChildren().add((Node) node);
	}

	private void setContentNode(Node node) {
		mainContent.getChildren().clear();
		if (!node.getId().equals("mainContent")) {
			mainContent.getChildren().add((Node) node);
		}
	}

	@FXML
	private void myProfileClicked(MouseEvent event) throws IOException {
		unhighlightAll();
		current_page = "profile";
		setNavNode(userBox);
		MyProfileController.current_username = SimpleUser.current_user.getUsername();
		MyProfileController.thisController.show();
		setContentNode(profile);
	}
	
	public void myProfileClicked() throws IOException {
		unhighlightAll();
		current_page = "profile";
		setNavNode(userBox);
		MyProfileController.current_username = SimpleUser.current_user.getUsername();
		MyProfileController.thisController.show();
		setContentNode(profile);
	}

	private void unhighlightAll() {
		contactbtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		gallerybtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		stadesbtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		hotelsbtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		teamsbtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		homebutton.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		fixturesbtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
	}

	@FXML
	private void GalerieCliked(MouseEvent event) throws IOException {
		unhighlightAll();
		current_page = "gallery";
		setNavNode(gallerybox);
	}
		@FXML
	private void teamsClicked(MouseEvent event) throws IOException {
		userBox.setVisible(false);
		gallerybox.setVisible(false);
		mainContent.getChildren().clear();
		teams = FXMLLoader.load(getClass().getResource("/Views/teamFrontEnd.fxml"));
		setContentNode(teams);
	}
}
