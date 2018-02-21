/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.GestionArticles;

import Entities.Article;
import Services.ArticleCrud;
import Services.UserCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTreeView;
import facebook4j.Account;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.PostUpdate;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author raiiz
 */
public class ConsulterArticlesController implements Initializable {

    @FXML
    private TableView<Article> articlesList;

    private ObservableList<Article> data;

    @FXML
    private JFXButton btVisualiser;
    @FXML
    private WebView webView;
    @FXML
    private JFXScrollPane scrollPaneWeb;

    Article toVisualize;
    private WebEngine webEngine;
    @FXML
    private JFXButton btModifier;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXButton btSupprimer;
    @FXML
    private JFXCheckBox checkSupprimer;
    @FXML
    private JFXButton btShare;
    private Facebook facebook;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webEngine = webView.getEngine();

        data = FXCollections.observableArrayList();
        ArticleCrud.findAll().forEach((e) -> data.add(e));
        TableColumn dateColumn = new TableColumn("Date de publication");
        dateColumn.setCellValueFactory(new PropertyValueFactory<Article, Date>("datePublication"));

        TableColumn<Article, String> titreColumn = new TableColumn("Titre de l'article");
        titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));

        titreColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        titreColumn.setOnEditCommit((TableColumn.CellEditEvent<Article, String> t) -> {
            //Article a = new Article(t.getTableView().getItems().get(t.getTablePosition().getRow()));
            //a.setTitre(t.getNewValue());
            //System.out.println(t.getTableView().getItems().get(t.getTablePosition().getRow()));
            //System.out.println(t.getNewValue());
            // System.out.println(a);
            if (ArticleCrud.update(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId(), "titre", t.getNewValue()) != null) {
                showDialog("Success", "L'article a ete MAJ");
            } else {
                showDialog("Error !!", "Un erreur est subis lors de la MAJ de l'article");
            }
        });
        articlesList.setEditable(true);

        articlesList.getColumns().clear();
        articlesList.getColumns().addAll(dateColumn, titreColumn);

        titreColumn.prefWidthProperty().bind(articlesList.widthProperty().multiply(0.7));
        dateColumn.prefWidthProperty().bind(articlesList.widthProperty().multiply(0.3));

        dateColumn.setSortType(TableColumn.SortType.DESCENDING);

        titreColumn.setResizable(false);
        dateColumn.setResizable(false);

        articlesList.setItems(data);

        articlesList.setRowFactory(tv -> {
            TableRow<Article> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {
                    Article clickedRow = row.getItem();
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
        btModifier.setDisable(false);
        /*TableRow<Article> row = new TableRow<>();
           row.setOnMouseClicked(eventt -> {
                if (!row.isEmpty() && eventt.getButton() == MouseButton.PRIMARY
                        && eventt.getClickCount() == 1) {
                    Article clickedRow = row.getItem();
                    System.out.println(clickedRow);
                }
            });*/

    }

    @FXML
    private void clickModifier(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/GestionArticles/modifierContenu.fxml"));
        Parent root = loader.load();
        ModifierContenuController controller = loader.getController();
        controller.setContenu(toVisualize.getContenu());
        controller.setArticle(toVisualize);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
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
    private void clickSupprimer(MouseEvent event) {
        if (checkSupprimer.isSelected()) {
            if (toVisualize == null) {
                showDialog("Errorr !!!", "Veuillez choisir un article a supprimer");
            } else {
                if (ArticleCrud.remove(toVisualize.getId())) {
                    showDialog("Successs", "L'article a ete supprimer avec success");
                } else {
                    showDialog("Erreur !!!", "L'article n'a pas pu etre supprimer, veuillez ressayer plus tard");
                }
            }
        }
    }

    @FXML
    private void shareClicked(MouseEvent event) throws FacebookException, MalformedURLException {
        // Generate facebook instance.
        facebook = new FacebookFactory().getInstance();
        // Use default values for oauth app id.
        facebook.setOAuthAppId("", "");
        // Get an access token from: 
        // https://developers.facebook.com/tools/explorer
        // Copy and paste it below.
        String accessTokenString = "EAACEdEose0cBALc2dX7YmPhZCMylXh6C36SKskJtgc1g8zT1F8QROtwOE6ooJTzp3YbkTINmRcOuzxKRpTXuYHfpTragFewFhjex6N2ZBmssxqm60YnQEEdQmKJTG5paYR6kE93GVDCc0ilIdsT9GpqVMKXXRFX4Q7lgInAOhcxdwwnF8OO6pJQVUg8asEtIysuQzbTKvTbrbUzx3Y";
        AccessToken at = new AccessToken(accessTokenString);
        // Set access token.
        facebook.setOAuthAccessToken(at);

        // We're done.
        // Access group feeds.
        // You can get the group ID from:
        // https://developers.facebook.com/tools/explorer
        ResponseList<Account> accounts = facebook.getAccounts();
        Account yourPageAccount = accounts.get(0);  // if index 0 is your page account.
        String pageAccessToken = yourPageAccount.getAccessToken();

        PostUpdate post = new PostUpdate(new URL("http://facebook4j.org"))
                .picture(new URL("http://facebook4j.org/images/hero.png"))
                .name(toVisualize.getTitre()+"-Par "+toVisualize.getAuteur())
                .caption(toVisualize.getTitre())
                .description(toVisualize.getContenu());
        facebook.postFeed(post);
        
    }
}
