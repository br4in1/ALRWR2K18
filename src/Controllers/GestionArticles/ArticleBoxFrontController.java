/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.GestionArticles;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author raiiz
 */
public class ArticleBoxFrontController implements Initializable {

    @FXML
    private VBox nav;
    @FXML
    private JFXTextField tfSearch;
    @FXML
    private JFXButton btGoSearch;
    @FXML
    private Label allLabel;
    @FXML
    private Label gamesLabel;
    @FXML
    private Label teamsLabel;
    @FXML
    private Label stadiumLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        allLabel.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");
    }

    @FXML
    private void allUnhighlight(MouseEvent event) {
        allLabel.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
    }

    @FXML
    private void allHighlight(MouseEvent event) {
        allLabel.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");

    }

    @FXML
    private void gamesUnhighlight(MouseEvent event) {
        gamesLabel.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
    }

    @FXML
    private void gamesHighlight(MouseEvent event) {
        gamesLabel.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");

    }

    @FXML
    private void teamsUnhightlight(MouseEvent event) {
        teamsLabel.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
    }

    @FXML
    private void teamsHighlight(MouseEvent event) {
        teamsLabel.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");

    }

    @FXML
    private void stadiumsUnhighlight(MouseEvent event) {
        stadiumLabel.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
    }

    @FXML
    private void stadiumsHighlight(MouseEvent event) {
        stadiumLabel.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");
    }

    @FXML
    private void AllClicked(MouseEvent event) throws IOException {
        ShowArticlesFrontController.content2Display = 1;
    }

    @FXML
    private void GamesClicked(MouseEvent event) throws IOException {
        ShowArticlesFrontController.content2Display = 2;
    }

    @FXML
    private void TeamsClicked(MouseEvent event) throws IOException {
        ShowArticlesFrontController.content2Display = 3;

    }

    @FXML
    private void StadiumsClicked(MouseEvent event) {
        ShowArticlesFrontController.content2Display = 4;
    }

}
