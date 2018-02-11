/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Article;
import Entities.Game;
import Services.ArticleCrud;
import Services.GameCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.HTMLEditor;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author raiiz
 */
public class AjouterArticleController implements Initializable {

    @FXML
    private JFXTextField tfTitre;
    @FXML
    private AnchorPane ajouterArticleForm;
    @FXML
    private JFXTextField tfAuteurId;
    @FXML
    private JFXButton btAjouter;
    @FXML
    private JFXComboBox<String> typeEntite;
    @FXML
    private JFXComboBox<Game> idEntite;
    @FXML
    private HTMLEditor contenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> entitesList = FXCollections.observableArrayList("Match", "Joueur", "Equipe", "Evenement", "Stade", "None");
        typeEntite.setItems(entitesList);
        idEntite.setConverter(new StringConverter<Game>() {
            @Override
            public String toString(Game object) {
                return "Game " + object.getId() + " : [" + object.getHomeTeam().getName() + "] VS [" + object.getAwayTeam().getName() + "]";
            }

            @Override
            public Game fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    private void ajouterArticle(MouseEvent event) {
        System.out.println("contenu:" + contenu.getHtmlText());
        System.out.println("Auteur id" + tfAuteurId.getText());
        System.out.println("type Entite" + typeEntite.getValue());
        if (idEntite.getValue() != null)
        System.out.println("id entite" + idEntite.getValue().getId());
        else
            System.out.println("ne pas laisser vide le champs id entite");
        System.out.println("button ajouter clicked");
        Article a = new Article(1, tfTitre.getText(), contenu.getHtmlText(), idEntite.getValue().getId(),typeEntite.getValue(), new Date(Calendar.getInstance().getTime().getTime()),null, Integer.parseInt(tfAuteurId.getText()));
        System.out.println(ArticleCrud.getRepository().add(a));
    }

    @FXML
    private void ChoixEntite(ActionEvent event) {
        idEntite.setDisable(false);
        if (typeEntite.getValue() != null) {
            switch (typeEntite.getValue()) {
                case "Match":
                    GameCrud gc = new GameCrud();
                    List<Game> games = gc.findGamesForArticles();
                    for (Game game : games) {
                        String s = "Game " + game.getId() + ": " + game.getHomeTeam() + " VS " + game.getAwayTeam() + "";
                        idEntite.getItems().add(game);
                    }
                    break;
                case "Joueur":
                    System.out.println("choix Joueur");
                    break;
                case "Equipe":
                    System.out.println("CHoix equipe");
                    break;
                case "Evenement":
                    System.out.println("Choix evenement");
                    break;
                case "Stade":
                    System.out.println("Choix stade");
                    break;
                case "None":
                    System.out.println("None");
                    break;
            }
        }else{
            System.out.println("ne pas laisser vide");
        }

    }
    /*
    private void getIdEntite(ActionEvent event) {
        event.getEventType().getName();
        idEntite.valueProperty().addListener((obs,oldVal,newVal) -> {
            System.out.println("Game choisit: "+newVal.getId()+" contre "+newVal.getHomeTeam());
        });
    }
     */
}
