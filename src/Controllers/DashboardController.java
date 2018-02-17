/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author simo
 */
public class DashboardController implements Initializable {

	@FXML
	private Pane content;
	@FXML
	private VBox nav;

	@FXML
	private Pane main;
	AnchorPane games, teams, teamStatics, players,users,userStatistics,squads,moderators;
	VBox tournementBox, teamBox, UsersBox;

	
	/**
	 * Initializes the controller class.
	 */
	private void setNavNode(Node node) {
		main.getChildren().clear();
		main.getChildren().add((Node) node);

		FadeTransition ft = new FadeTransition(Duration.millis(1500));
		ft.setNode(node);
		ft.setFromValue(0.1);
		ft.setToValue(1);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);
		ft.play();
	}

	private void setContentNode(Node node) {
		content.getChildren().clear();
		if(!node.getId().equals("content")) content.getChildren().add((Node) node);

		FadeTransition ft = new FadeTransition(Duration.millis(1500));
		ft.setNode(node);
		ft.setFromValue(0.1);
		ft.setToValue(1);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);
		ft.play();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			//Load all fxmls in a cache

			tournementBox = FXMLLoader.load(getClass().getResource("/Views/tournementBox.fxml"));
			teamBox = FXMLLoader.load(getClass().getResource("/Views/teamBox.fxml"));
			games = FXMLLoader.load(getClass().getResource("/Views/GamesCrud.fxml"));
			teams = FXMLLoader.load(getClass().getResource("/Views/teamsCrud.fxml"));
			teamStatics = FXMLLoader.load(getClass().getResource("/Views/teamStatics.fxml"));
			players = FXMLLoader.load(getClass().getResource("/Views/playersCrud.fxml"));
			UsersBox = FXMLLoader.load(getClass().getResource("/Views/UsersBox.fxml"));
			users = FXMLLoader.load(getClass().getResource("/Views/UsersCrud.fxml"));
			squads = FXMLLoader.load(getClass().getResource("/Views/squads.fxml"));
			userStatistics = FXMLLoader.load(getClass().getResource("/Views/UsersStatistics.fxml"));
			moderators = FXMLLoader.load(getClass().getResource("/Views/ModeratorsCrud.fxml"));

			for (Node node : teamBox.getChildren()) {
				node.addEventHandler(MouseEvent.MOUSE_CLICKED, (k) -> {
					switch (node.getId()) {
						case "mainMenu":
							setNavNode(nav);
							setContentNode(content);
							break;
						case "Teams":
							setContentNode(teams);
							break;
						case "TeamStatics":
							setContentNode(teamStatics);
							break;
						case "Players":
							setContentNode(players);
							break;

					}
				});
			}

			for (Node node : tournementBox.getChildren()) {
				node.addEventHandler(MouseEvent.MOUSE_CLICKED, (k) -> {
					switch (node.getId()) {
						case "mainMenu":
							setNavNode(nav);

							setContentNode(content);
							break;
						case "games":
							setContentNode(games);
							break;
						case "squads":
							setContentNode(squads);
							break;

					}
				});
			}
			
			for(Node node : UsersBox.getChildren()){
				node.addEventHandler(MouseEvent.MOUSE_CLICKED, (k) -> {
					switch (node.getId()) {
						case "mainMenu":
							setNavNode(nav);
							setContentNode(content);
							break;
						case "users":
							setContentNode(users);
							break;
						case "usersStatistics":
							System.out.println("test");
							setContentNode(userStatistics);
							break;
						case "moderators":
							setContentNode(moderators);
							break;
					}
				});
			}

		} catch (IOException ex) {
			Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@FXML
	private void usersNavbar(MouseEvent event) {
		setNavNode(UsersBox);
	}

	@FXML
	private void newsNavbar(MouseEvent event) {
	}

	@FXML
	private void guideNavbar(MouseEvent event) {
	}

	@FXML
	private void GalleryNavbar(MouseEvent event) {

	}

	@FXML
	private void teamsNavbar(MouseEvent event) {
		setNavNode(teamBox);
	}

	@FXML
	private void tournementNavbar(MouseEvent event) {
		setNavNode(tournementBox);
	}
}
