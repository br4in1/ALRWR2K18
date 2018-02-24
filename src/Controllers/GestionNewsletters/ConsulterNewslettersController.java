/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.GestionNewsletters;

import Entities.Newsletter;
import Services.NewsletterCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXScrollPane;
import facebook4j.Account;
import facebook4j.Comment;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.PagableList;
import facebook4j.Post;
import facebook4j.PostUpdate;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author raiiz
 */
public class ConsulterNewslettersController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXButton btVisualiser;
    @FXML
    private JFXScrollPane scrollPaneWeb;
    @FXML
    private WebView webView;
    @FXML
    private JFXButton btSupprimer;
    @FXML
    private JFXCheckBox checkSupprimer;
    @FXML
    private TableView<Newsletter> newsletterList;
    private ObservableList<Newsletter> data;
    Newsletter toVisualize;

    private WebEngine webEngine;
    private Facebook facebook;
    @FXML
    private JFXButton btRefresh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        facebook = new FacebookFactory().getInstance();
        //      facebook.setOAuthAppId("191604434666665", "a002b0a24ae998e350f9d9e8ecaeb511");
        //    facebook.setOAuthPermissions("email,public_profile,publish_pages,publish_actions,manage_pages,pages_messaging,publish_actions");
        //facebook.setOAuthAccessToken(new AccessToken("EAACuQ2JEBKkBAMOk5zkxQlMzcaZCPaTNZA1UbaPulEn16V3B6wLd1OcUHD6rvg863wL1yyYArfkl78ZBpVzhyKv9EBZAjTCZCBV4rIDUQSUEWU8pCQ4aVLsCK3ImikalSBxQZC80aTxIac3bsTk7cLRqiflU3CMobbrA5qMEHxnUKmQTEmZAo3TdeesQeGg41IZD", null));
//facebook.setOAuthAccessToken(new AccessToken("191604434666665|UgyDHBd3HK8SnWqetkBZi-eWoXg", null));
        newsletterList.refresh();
        webEngine = webView.getEngine();
        data = FXCollections.observableArrayList();
        NewsletterCrud.findAll().forEach((e) -> data.add(e));

        TableColumn prefColumn = new TableColumn("Preferences");
        prefColumn.setCellValueFactory(new PropertyValueFactory<Newsletter, String>("Preferences"));

        TableColumn<Newsletter, String> emailColumn = new TableColumn("Email");

        emailColumn.setCellValueFactory(new PropertyValueFactory<Newsletter, String>("Email"));
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setOnEditCommit((TableColumn.CellEditEvent<Newsletter, String> t) -> {

            if (NewsletterCrud.update(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId(), "email", t.getNewValue()) != null) {
                showDialog("Success", "L'article a ete MAJ");
            } else {
                showDialog("Error !!", "Un erreur est subis lors de la MAJ de l'article");
            }
        });
        newsletterList.setEditable(true);
        emailColumn.prefWidthProperty().bind(newsletterList.widthProperty().multiply(0.7));
        prefColumn.prefWidthProperty().bind(newsletterList.widthProperty().multiply(0.3));

        emailColumn.setResizable(false);
        prefColumn.setResizable(false);
        newsletterList.getColumns().clear();
        newsletterList.getColumns().addAll(emailColumn, prefColumn);
        newsletterList.setItems(data);
        newsletterList.setRowFactory(tv -> {
            TableRow<Newsletter> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {
                    Newsletter clickedRow = row.getItem();
                    toVisualize = row.getItem();
                }
            });
            return row;
        });
        checkSupprimer.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (checkSupprimer.isSelected()) {
                    btSupprimer.setDisable(false);
                } else {
                    btSupprimer.setDisable(true);
                }
            }
        });

    }

    @FXML
    private void clickVisualiser(MouseEvent event) {
        webEngine.loadContent(toVisualize.getContenu());

    }

    @FXML
    private void clickTable(MouseEvent event) {
        btVisualiser.setDisable(false);
    }

    @FXML
    private void clickSupprimer(MouseEvent event) {
        if (checkSupprimer.isSelected()) {
            if (toVisualize == null) {
                showDialog("Errorr !!!", "Veuillez choisir un article a supprimer");
            } else {
                if (NewsletterCrud.remove(toVisualize.getId())) {
                    showDialog("Successs", "L'article a ete supprimer avec success");
                    data.remove(toVisualize);
                    newsletterList.setItems(data);
                    newsletterList.refresh();
                } else {
                    showDialog("Erreur !!!", "L'article n'a pas pu etre supprimer, veuillez ressayer plus tard");
                }
            }
        }
    }

    private void showDialog(String titre, String contenu) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(titre));
        content.setBody(new Text(contenu));
        StackPane stackpane = new StackPane();
        JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("Close");

        button.setButtonType(JFXButton.ButtonType.RAISED);
        Scene scene = new Scene(stackpane, 300, 250);
        anchorPane.getChildren().add(stackpane);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        content.setActions(button);
        dialog.show();
    }

    @FXML
    private void RefreshClicked(MouseEvent event) {
        newsletterList.getItems().removeAll(data);
        NewsletterCrud.findAll().forEach((e) -> data.add(e));
        newsletterList.setItems(data);
    }
}
