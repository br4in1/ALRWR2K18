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
    AnchorPane games;
    VBox tournementBox;

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
        content.getChildren().add((Node) node);

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
            games = FXMLLoader.load(getClass().getResource("/Views/GamesCrud.fxml"));

            articlesBox = FXMLLoader.load(getClass().getResource("/Views/GestionArticles/articleBox.fxml"));
            newslettersBox = FXMLLoader.load(getClass().getResource("/Views/GestionNewsletters/newslettersBox.fxml"));
            ajouterArticle = FXMLLoader.load(getClass().getResource("/Views/GestionArticles/AjouterArticle.fxml"));
            consulterArticles = FXMLLoader.load(getClass().getResource("/Views/GestionArticles/ConsulterArticles.fxml"));
            ajouterNewsletter = FXMLLoader.load(getClass().getResource("/Views/GestionNewsletters/ajouterNewsletter.fxml"));
            consulterNewsletter = FXMLLoader.load(getClass().getResource("/Views/GestionNewsletters/consulterNewsletters.fxml"));

            for (Node node : tournementBox.getChildren()) {
                node.addEventHandler(MouseEvent.MOUSE_PRESSED, (k) -> {
                    switch (node.getId()) {
                        case "mainMenu":
                            setNavNode(nav);
                            break;
                        case "games":
                            setContentNode(games);
                    }
                });
            }

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

        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void usersNavbar(MouseEvent event) {
    }

    @FXML
    private void teamsNavbar(MouseEvent event) {
    }

    @FXML
    private void newsNavbar(MouseEvent event) {
        setNavNode(articlesBox);
    }

    @FXML
    private void guideNavbar(MouseEvent event) {
    }

    @FXML
    private void tournementNavbar(MouseEvent event) {
        setNavNode(tournementBox);
    }

    @FXML
    private void GalleryNavbar(MouseEvent event) {
    }

    @FXML
    private void NewsLettersNavbar(MouseEvent event) {
        setNavNode(newslettersBox);

    }
}
