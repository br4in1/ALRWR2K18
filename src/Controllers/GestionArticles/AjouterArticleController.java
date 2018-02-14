/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.GestionArticles;

import Entities.Article;
import Entities.Game;
import Services.ArticleCrud;
import Services.GameCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author raiiz
 */
public class AjouterArticleController implements Initializable {

    @FXML
    private AnchorPane ajouterArticlePane;
    @FXML
    private HTMLEditor contenu;
    @FXML
    private JFXTextField tfAuteurId;
    @FXML
    private JFXButton btAjouter;
    @FXML
    private JFXComboBox<String> typeEntite;
    @FXML
    private JFXComboBox<Game> idEntite;
    @FXML
    private JFXTextField tfTitre;
    @FXML
    private JFXCheckBox checkPublier;
    @FXML
    private JFXButton btSendMail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> entitesList = FXCollections.observableArrayList("Match", "Joueur", "Equipe", "Evenement", "Stade", "None");
        typeEntite.setItems(entitesList);

        checkPublier.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (checkPublier.isSelected()) {
                    btAjouter.setDisable(false);
                } else {
                    btAjouter.setDisable(true);
                }
            }

        });
    }

    @FXML
    private void choixEntite(ActionEvent event) {
        idEntite.setDisable(false);
        if (typeEntite.getValue() != null) {
            switch (typeEntite.getValue()) {
                case "Match":
                    idEntite.setConverter(new StringConverter<Game>() {
                        @Override
                        public String toString(Game object) {
                            return "Game " + object.getId() + " : [" + object.getHomeTeam() + "] VS [" + object.getAwayTeam() + "]";
                        }

                        @Override
                        public Game fromString(String string) {
                            return null;
                        }
                    });
                    GameCrud gc = new GameCrud();
                    List<Game> games = gc.findAllGames();
                    for (Game game : games) {
                        idEntite.getItems().add(game);
                    }
                    break;
                case "Joueur":
                    System.out.println("choix Joueur");
                    break;
                case "Equipe":
                    System.out.println("Choix equipe");
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
        } else {
            System.out.println("ne pas laisser vide");
        }
    }

    @FXML
    private void ajouterArticle(MouseEvent event) {
        if (idEntite.getValue() == null || contenu.getHtmlText() == null || contenu.getHtmlText().equals("") || tfAuteurId.getText().equals("") || tfAuteurId.getText() == null || tfTitre.getText().equals("") || tfTitre.getText() == null || !checkPublier.isSelected()) {
            showDialog("Error", "Veuillez verifier que vous avez remplis tous les champs");
        } else {
            Article a = new Article(1, tfTitre.getText(), contenu.getHtmlText(), idEntite.getValue().getId(), typeEntite.getValue(), new Date(Calendar.getInstance().getTime().getTime()), null, Integer.parseInt(tfAuteurId.getText()));
            if (ArticleCrud.getRepository().add(a)) {
                showDialog("Success", "Votre articles a ete ajouter avec success");

            } else {
                showDialog("Erreur", "Un erreur est subis lors de l'ajoiut de l'article ,veuillez essayer plus tard");
            }
        }
    }

    private void showDialog(String titre, String contenu) {
        Stage stage = (Stage) ajouterArticlePane.getScene().getWindow();
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(titre));
        content.setBody(new Text(contenu));
        StackPane stackpane = new StackPane();
        JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("Close");
        button.setButtonType(JFXButton.ButtonType.RAISED);

        Scene scene = new Scene(stackpane, 300, 250);
        ajouterArticlePane.getChildren().add(stackpane);

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
    private void sendMailClicked(MouseEvent event) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/GestionArticles/sendArticle.fxml"));
        Parent root = loader.load();
        SendArticleController controller = loader.getController();

        primaryStage.setTitle("Send Article");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        controller.setContenu(contenu.getHtmlText());
        controller.setTitle(tfTitre.getText());
        primaryStage.show();

    }

}
