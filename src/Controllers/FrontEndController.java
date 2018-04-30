/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.GestionArticles.ShowArticlesFrontController;
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
import Controllers.TeamFrontController;
import Views.FrontTeamBoxController;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

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
    private Label tournementbtn;
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
    VBox userBox, gallerybox, articlesBox, teamBox, MapBox, gamesBox;
    Pane streaming,groups,games, add, gallery, profile, teams, editProfile, showArticles, interfaceMap, interfaceHotel, interfaceStade, interfaceResto, translate, livescore;
    @FXML
    private AnchorPane sidebar;
    @FXML
    private AnchorPane mainContent;
    private int users_loaded = 0;
    private int teams_loaded = 0;
    private int gallery_loaded = 0;
    private int guide_loaded = 0;
    private int tournement_loaded = 0;
    private int articles_loaded = 0;

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
            homeClicked(null);
        } catch (IOException ex) {
            Logger.getLogger(FrontEndController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void unhighlightTournementbtn(MouseEvent event) {
        if (!current_page.equals("games")) {
            tournementbtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
        }
    }

    @FXML
    private void highlightTournementbtn(MouseEvent event) {
        if (!current_page.equals("games")) {
            tournementbtn.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
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
        if (!current_page.equals("Guide")) {
            hotelsbtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
        }
    }

    @FXML
    private void highlighthotelsbtn(MouseEvent event) {
        if (!current_page.equals("Guide")) {
            hotelsbtn.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
        }
    }

    @FXML
    private void unhighlightstadesbtn(MouseEvent event) {
        if (!current_page.equals("Guide")) {
            stadesbtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
        }
    }

    @FXML
    private void highlightstadesbtn(MouseEvent event) {
        if (!current_page.equals("Guide")) {
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
        if (users_loaded == 0) {
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
            users_loaded = 1;
        }
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
        tournementbtn.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
    }

    @FXML
    private void GalerieCliked(MouseEvent event) throws IOException {
        gallerybtn.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
        if (gallery_loaded == 0) {
            add = FXMLLoader.load(getClass().getResource("/Views/AddImage.fxml"));
            gallery = FXMLLoader.load(getClass().getResource("/Views/Showall.fxml"));
            //mygallery = FXMLLoader.load(getClass().getResource("/Views/DisplayI.fxml"));
            gallerybox = FXMLLoader.load(getClass().getResource("/Views/FrontGalleryBox.fxml"));
            for (Node node : gallerybox.getChildren()) {
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, (k) -> {
                    switch (node.getId()) {
                        case "AddImage":
                            FrontGalleryBoxController.thisController.unhighlightAll();
                            FrontGalleryBoxController.thisController.highlightAdd(k);
                            FrontGalleryBoxController.selected = "addimage";
                            setContentNode(add);
                            break;
                        case "ShowGallery":
                            FrontGalleryBoxController.thisController.unhighlightAll();
                            FrontGalleryBoxController.thisController.highlightConsulter(k);
                            FrontGalleryBoxController.selected = "consulter";
                            setContentNode(gallery);
                            break;
                        //	case "ShowMyGallery":
                        //	FrontGalleryBoxController.thisController.unhighlightAll();
                        //	FrontGalleryBoxController.thisController.highlightConsulter2(k);
                        //	FrontGalleryBoxController.selected = "consulter2";
                        //setContentNode(players);
                        //	break;

                    }
                });
            }
            gallery_loaded = 1;
        }
        unhighlightAll();
        current_page = "gallery";
        setNavNode(gallerybox);
        setContentNode(add);
    }

    @FXML
    private void teamsClicked(MouseEvent event) throws IOException {
        teamsbtn.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
        if (teams_loaded == 0) {
            teams = FXMLLoader.load(getClass().getResource("/Views/TeamFront.fxml"));
            teamBox = FXMLLoader.load(getClass().getResource("/Views/FrontTeamBox.fxml"));
            for (Node node : FrontTeamBoxController.thisController.nav.getChildren()) {
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, (k) -> {
                    if (TeamFrontController.current_team_id != Integer.parseInt(node.getId())) {
                        TeamFrontController.current_team_id = Integer.parseInt(node.getId());
                        TeamFrontController.thisController.refreshData();
                    }
                });
            }
            teams_loaded = 1;
        }
        unhighlightAll();
        current_page = "teams";
        setNavNode(teamBox);
        setContentNode(teams);
    }

    @FXML
    private void homeClicked(MouseEvent event) throws IOException {
        unhighlightAll();
        homebutton.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
        current_page = "home";
        if (articles_loaded == 0) {
            showArticles = FXMLLoader.load(getClass().getResource("/Views/GestionArticles/showArticlesFront.fxml"));
            articlesBox = FXMLLoader.load(getClass().getResource("/Views/GestionArticles/articleBoxFront.fxml"));
            for (Node node : articlesBox.getChildren()) {
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, (k) -> {
                    switch (node.getId()) {
                        case "allLabel":
                            if (ShowArticlesFrontController.content2Display != 1) {
                                ShowArticlesFrontController.content2Display = 1;
                                ShowArticlesFrontController.thisController.setContent();
                            }
                            break;
                        case "gamesLabel":
                            if (ShowArticlesFrontController.content2Display != 2) {
                                ShowArticlesFrontController.content2Display = 2;
                                ShowArticlesFrontController.thisController.setContent();
                            }
                            break;
                        case "teamsLabel":
                            if (ShowArticlesFrontController.content2Display != 3) {
                                ShowArticlesFrontController.content2Display = 3;
                                ShowArticlesFrontController.thisController.setContent();
                            }
                            break;
                        case "stadiumLabel":
                            if (ShowArticlesFrontController.content2Display != 4) {
                                ShowArticlesFrontController.content2Display = 4;
                                ShowArticlesFrontController.thisController.setContent();
                            }
                            break;
                        default:
                            break;
                    }
                });

                HBox h = (HBox) articlesBox.getChildren().get(1);
                for (Node n : h.getChildren()) {
                    n.addEventHandler(MouseEvent.MOUSE_CLICKED, (z) -> {
                        switch (n.getId()) {
                            case "btSearch":
                                TextField tf = (TextField) h.getChildren().get(0);
                                if (tf.getText().equals("") || tf.getText() == null) {
                                    ShowArticlesFrontController.thisController.showDialog("Error", "Le champ search ne doit pas etre vide");
                                } else {
                                    ShowArticlesFrontController.content2Display = 5;
                                    ShowArticlesFrontController.keyword = tf.getText();
                                    ShowArticlesFrontController.thisController.setContent();
                                }
                                break;
                        }
                    });

                }

            }
            articles_loaded = 1;
        }

        setContentNode(showArticles);
        setNavNode(articlesBox);
    }

    @FXML
    private void GuideClicked(MouseEvent event) throws IOException {
        stadesbtn.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
        if (guide_loaded == 0) {
            MapBox = FXMLLoader.load(getClass().getResource("/Views/MapBox.fxml"));

            interfaceMap = FXMLLoader.load(getClass().getResource("/Views/GuideAffichageMap.fxml"));
            for (Node node : MapBox.getChildren()) {
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, (k) -> {
                    switch (node.getId()) {
                        case "VisualizeMap":
                            setContentNode(interfaceMap);
                            break;
                        case "Hotels": {
                            try {
                                interfaceHotel = FXMLLoader.load(getClass().getResource("/Views/ReceptionFront.fxml"));
                            } catch (IOException ex) {
                                Logger.getLogger(FrontEndController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
						MapBoxController.thisController.unhighlightAll();
						MapBoxController.selected = "hotels";
						MapBoxController.thisController.highlightEdit(k);
                        setContentNode(interfaceHotel);
                        break;

                        case "Stadiums": {
                            try {
                                interfaceStade = FXMLLoader.load(getClass().getResource("/Views/StadeFrontDisplay.fxml"));
                            } catch (IOException ex) {
                                Logger.getLogger(FrontEndController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
						MapBoxController.thisController.unhighlightAll();
						MapBoxController.selected = "stadiums";
						MapBoxController.thisController.highlightFavoris(k);
                        setContentNode(interfaceStade);
                        break;
                        //Translate
                        case "Entertanment": {
                            try {
                                interfaceResto = FXMLLoader.load(getClass().getResource("/Views/EntertanmentDisplayFront.fxml"));
                            } catch (IOException ex) {
                                Logger.getLogger(FrontEndController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
						MapBoxController.thisController.unhighlightAll();
						MapBoxController.selected = "entertainement";
						MapBoxController.thisController.highlightPhotos(k);
                        setContentNode(interfaceResto);
                        break;
                        case "Translate": {
                            try {
                                translate = FXMLLoader.load(getClass().getResource("/Views/TranslationDisplay.fxml"));
                            } catch (IOException ex) {
                                Logger.getLogger(FrontEndController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
						MapBoxController.thisController.unhighlightAll();
						MapBoxController.selected = "translate";
						MapBoxController.thisController.highlightTranslate(k);
                        setContentNode(translate);
                        break;

                    }
                });
            }
            guide_loaded = 1;
        }
        unhighlightAll();
        current_page = "Guide";
        setNavNode(MapBox);
        setContentNode(interfaceMap);
    }

    public static void ShowNotification(String text) {
        Notifications notificationBuilder
                = Notifications.create().title("New Notification !")
                        .text(text)
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.TOP_RIGHT)
                        .onAction((ActionEvent event1) -> {
                            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        });

        notificationBuilder.showInformation();
    }

    @FXML
    private void tournementClicked(MouseEvent event) throws IOException {
        tournementbtn.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 25px; -fx-text-fill: #fff;");
        if (tournement_loaded == 0) {
            gamesBox = FXMLLoader.load(getClass().getResource("/Views/FrontGamesBox.fxml"));
            games = FXMLLoader.load(getClass().getResource("/Views/Games.fxml"));
            livescore = FXMLLoader.load(getClass().getResource("/Views/LiveScore.fxml"));
			groups = FXMLLoader.load(getClass().getResource("/Views/Groups.fxml"));
			streaming= FXMLLoader.load(getClass().getResource("/Views/Streaming.fxml"));
            for (Node node : gamesBox.getChildren()) {
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, (k) -> {
                    switch (node.getId()) {
                        case "games":
                            FrontGamesBoxController.thisController.unhighlightAll();
                            FrontGamesBoxController.thisController.highlightGames(k);
                            FrontGamesBoxController.selected = "games";
                            setContentNode(games);
                            break;
                        case "liveScore":
                            FrontGamesBoxController.thisController.unhighlightAll();
                            FrontGamesBoxController.thisController.highlightLiveScores(k);
                            FrontGamesBoxController.selected = "liveScore";
                            setContentNode(livescore);
                            break;
                        case "streaming":
                            FrontGamesBoxController.thisController.unhighlightAll();
                            FrontGamesBoxController.thisController.highlightStreaming(k);
                            FrontGamesBoxController.selected = "streaming";
                            setContentNode(streaming);
                            break;
                        case "groups":
                            FrontGamesBoxController.thisController.unhighlightAll();
                            FrontGamesBoxController.thisController.highlightGroups(k);
                            FrontGamesBoxController.selected = "groups";
                            setContentNode(groups);
                            break;
                    }
                });
            }
            tournement_loaded = 1;
        }
        unhighlightAll();
        current_page = "games";
        setNavNode(gamesBox);
        setContentNode(games);
    }
}
