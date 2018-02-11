/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author raiiz
 */
public class AjouterArticleController implements Initializable {

    @FXML
    private JFXTextField tfTitre;
    @FXML
    private HTMLEditor contenu;
    @FXML
    private AnchorPane ajouterArticleForm;
    @FXML
    private JFXTextField tfAuteurId;
    @FXML
    private JFXTextField tfIdEntite;
    @FXML
    private JFXTextField tfTypeEntite;
    @FXML
    private JFXButton btAjouter;
    @FXML
    private JFXComboBox<String> typeEntite;
    @FXML
    private JFXComboBox<?> idEntite;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> entitesList = FXCollections.observableArrayList("Match","Joueur","Equipe","Evenement","Stade","None") ;
        typeEntite.setItems(entitesList);
    }    

    @FXML
    private void ajouterArticle(MouseEvent event) {
        System.out.println("titre:"+tfTitre.getText());
        System.out.println("contenu:"+contenu.getHtmlText());
        System.out.println("Auteur id"+tfAuteurId.getText());
        System.out.println("Id Entite"+tfIdEntite.getText());
        System.out.println("Type Entite"+tfTypeEntite.getText());
        System.out.println("button ajouter clicked");
    }

    @FXML
    private void ChoixEntite(ActionEvent event) {
        idEntite.setDisable(false);
        switch (typeEntite.getValue()){
            case "Match":
                break;
        }
    }
    
}
