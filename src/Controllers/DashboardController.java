/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
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
	AnchorPane games, teams, teamStatics, players, users, userStatistics, squads, moderators;
	VBox tournementBox, teamBox, UsersBox;
	AnchorPane add;
	AnchorPane manageGallery;
	AnchorPane show1;
	AnchorPane show2;
	AnchorPane show3;
	VBox GalleryBox;
	AnchorPane hotels;
	VBox guideBox;
	AnchorPane Managing;
	AnchorPane StaduimManage;
	AnchorPane ManagingDivertissement;
	VBox articlesBox;
    VBox newslettersBox;
    AnchorPane ajouterArticle;
    AnchorPane consulterArticles;
    
    AnchorPane ajouterNewsletter;
    AnchorPane consulterNewsletter;
    @FXML
    private JFXButton Articles;

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
		if (!node.getId().equals("content")) {
			content.getChildren().add((Node) node);
		}

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
		TextToSpeech tts = new TextToSpeech();
		tts.setVoice("dfki-poppy-hsmm");
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
			add = FXMLLoader.load(getClass().getResource("/Views/AddImage.fxml"));
			manageGallery = FXMLLoader.load(getClass().getResource("/Views/DisplayI.fxml"));
			show1 = FXMLLoader.load(getClass().getResource("/Views/Showall.fxml"));
			show2 = FXMLLoader.load(getClass().getResource("/Views/DisplayO.fxml"));
			//		show3 = FXMLLoader.load(getClass().getResource("/Views/chartP.fxml"));
			GalleryBox = FXMLLoader.load(getClass().getResource("/Views/GalleryBox.fxml"));
			guideBox = FXMLLoader.load(getClass().getResource("/Views/GuidBox.fxml"));
			hotels = FXMLLoader.load(getClass().getResource("/Views/GuideHotelCrud.fxml"));
			Managing = FXMLLoader.load(getClass().getResource("/Views/HotelDisplay.fxml"));
			articlesBox = FXMLLoader.load(getClass().getResource("/Views/GestionArticles/articleBox.fxml"));
            newslettersBox = FXMLLoader.load(getClass().getResource("/Views/GestionNewsletters/newslettersBox.fxml"));
            ajouterArticle = FXMLLoader.load(getClass().getResource("/Views/GestionArticles/AjouterArticle.fxml"));
            consulterArticles = FXMLLoader.load(getClass().getResource("/Views/GestionArticles/ConsulterArticles.fxml"));
            ajouterNewsletter = FXMLLoader.load(getClass().getResource("/Views/GestionNewsletters/ajouterNewsletter.fxml"));
            consulterNewsletter = FXMLLoader.load(getClass().getResource("/Views/GestionNewsletters/consulterNewsletters.fxml"));
			
			for (Node node : articlesBox.getChildren()) {
                node.addEventHandler(MouseEvent.MOUSE_PRESSED, (k) -> {
                    switch (node.getId()) {
                        case "mainMenu":
                            setNavNode(nav);
                            break;
                        case "addArticle":
                            setContentNode(ajouterArticle);
                            break;
                        case "showArticles":
                            setContentNode(consulterArticles);
                            break;
                    }
                });
            }

            for (Node node : newslettersBox.getChildren()) {
                node.addEventHandler(MouseEvent.MOUSE_PRESSED, (k) -> {
                    switch (node.getId()) {
                        case "mainMenu":
                            setNavNode(nav);
                            break;
                        case "addNewsLetter":
                            setContentNode(ajouterNewsletter);
                            break;
                        case "showNewsLetters":
                            setContentNode(consulterNewsletter);
                            break;
                    }
                });
            }

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

			for (Node node : guideBox.getChildren()) {
				node.addEventHandler(MouseEvent.MOUSE_PRESSED, (k) -> {
					switch (node.getId()) {
						case "mainMenu":

							tts.speak("main Menu", 2.0f, false, false);
							setNavNode(nav);
							break;
						case "hotel":
							tts.speak("adding with Map", 2.0f, false, false);
							setContentNode(hotels);
							break;
						case "manage":
							tts.speak("Managing hotels", 2.0f, false, false);
							setContentNode(Managing);
							break;
						case "divertiss":
							tts.speak("Managing divertissments", 2.0f, false, false);
							 {
								try {
									ManagingDivertissement = FXMLLoader.load(getClass().getResource("/Views/DivertissementDisplay2.fxml"));
								} catch (IOException ex) {
									Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
								}
								setContentNode(ManagingDivertissement);
							}
							break;
						case "DisplayStade":
							tts.speak("Managing Staduims", 2.0f, false, false);
							 {
								try {
									StaduimManage = FXMLLoader.load(getClass().getResource("/Views/StadeDisplay.fxml"));
								} catch (IOException ex) {
									Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
								}

								setContentNode(StaduimManage);
							}
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

			for (Node node : GalleryBox.getChildren()) {
				node.addEventHandler(MouseEvent.MOUSE_CLICKED, (k) -> {
					switch (node.getId()) {
						case "mainMenu":
							setNavNode(nav);
							break;
						case "Add":
							setContentNode(add);
							break;
						case "managegallery":
							setContentNode(manageGallery);
							break;

						case "Showw":
							setContentNode(show1);
							break;
						case "Showw3":
							setContentNode(show2);
							break;
						case "stat":
							setContentNode(show3);
							break;

					}
				});
			}

			for (Node node : UsersBox.getChildren()) {
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
	private void guideNavbar(MouseEvent event) {
		setNavNode(guideBox);
	}

	@FXML
	private void GalleryNavbar(MouseEvent event) {
		setNavNode(GalleryBox);
	}

	@FXML
	private void teamsNavbar(MouseEvent event) {
		setNavNode(teamBox);
	}

	@FXML
	private void tournementNavbar(MouseEvent event) {
		setNavNode(tournementBox);
	}
	
	@FXML
    private void NewsLettersNavbar(MouseEvent event) {
        setNavNode(newslettersBox);
    }
	
	@FXML
    private void newsNavbar(MouseEvent event) {
        setNavNode(articlesBox);
    }
}
