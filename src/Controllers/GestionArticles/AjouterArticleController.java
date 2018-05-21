/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.GestionArticles;

import Entities.Admin;
import Entities.Article;
import Entities.Game;
import Entities.Moderator;
import Entities.Player;
import Entities.Stade;
import Entities.Team;
import Services.ArticleCrud;
import Services.GameCrud;
import Services.PlayerCrud;
import Services.StadeCRUD;
import Services.TeamCrud;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.webkit.Accessor;
import com.sun.webkit.WebPage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author raiiz
 */
public class AjouterArticleController implements Initializable {

    @FXML
    private AnchorPane ajouterArticlePane;

    private HTMLEditor contenu;
    @FXML
    private JFXButton btAjouter;
    @FXML
    private JFXComboBox<String> typeEntite;
    @FXML
    private JFXComboBox idEntite;
    @FXML
    private JFXTextField tfTitre;
    @FXML
    private JFXCheckBox checkPublier;
    @FXML
    private JFXButton btSendMail;
    @FXML
    private JFXButton btAjouterImage;

    Cloudinary cloudinary;
    private File image;
    @FXML
    private JFXTextField tfImageArticle;
    @FXML
    private AnchorPane contenuPane;
    @FXML
    private ImageView ivImageArticle;
    @FXML
    private StackPane IvStackPane;
    private String noneChosen = null;
    @FXML
    private JFXCheckBox checkComments;
    @FXML
    private JFXCheckBox checkPrivate;
    private int is_commentable = 0;
    private int is_private = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contenu = new HTMLEditor();
        contenu.setId("contenu");
        contenuPane.getChildren().add(contenu);
        contenu.prefWidthProperty().bind(contenuPane.widthProperty());
        contenu.prefHeightProperty().bind(contenuPane.heightProperty());

        cloudinary = new Cloudinary("cloudinary://187685892358282:rL27N346tuXqVQyA5sR1oDLFJag@pidev");
        ObservableList<String> entitesList = FXCollections.observableArrayList("Match", "Joueur", "Equipe", "Stade");
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
        checkComments.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (checkComments.isSelected()) {
                    is_commentable = 1;
                } else {
                    is_commentable = 0;
                }
            }
        });
        checkPrivate.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (checkPrivate.isSelected()) {
                    is_private = 1;
                } else {
                    is_private = 0;
                }
            }
        });
    }

    @FXML
    private void choixEntite(ActionEvent event) throws SQLException {
        idEntite.setDisable(false);
        if (!idEntite.getItems().isEmpty()) {
            idEntite.getItems().clear();
        }
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
                idEntite.setConverter(new StringConverter<Player>() {
                    @Override
                    public String toString(Player object) {
                        return "Player [" + object.getId() + "] : " + object.getFullName();
                    }

                    @Override
                    public Player fromString(String string) {
                        return null;
                    }
                });
                PlayerCrud pc = new PlayerCrud();
                List<Player> players = pc.findAllPlayers();
                for (Player pl : players) {
                    idEntite.getItems().add(pl);
                }
                break;

            case "Equipe":
                idEntite.setConverter(new StringConverter<Team>() {
                    @Override
                    public String toString(Team object) {
                        return "Equipe [" + object.getId() + "] : " + object.getName();
                    }

                    @Override
                    public Team fromString(String string) {
                        return null;
                    }
                });
                TeamCrud tc = new TeamCrud();
                List<Team> teams = tc.findAllTeam();
                for (Team tm : teams) {
                    idEntite.getItems().add(tm);
                }
                break;
            case "Stade":
                idEntite.setConverter(new StringConverter<Stade>() {
                    @Override
                    public String toString(Stade object) {
                        return "Stade [" + object.getId() + "] : " + object.getNom();
                    }

                    @Override
                    public Stade fromString(String string) {
                        return null;
                    }
                });
                StadeCRUD sc = new StadeCRUD();
                List<Stade> stades = sc.AfficherTousLesStades();
                for (Stade st : stades) {
                    idEntite.getItems().add(st);
                }
                break;
         
        }
    }

    @FXML
    private void ajouterArticle(MouseEvent event) throws IOException {
        if (idEntite.getValue() == null || contenu.getHtmlText() == null || contenu.getHtmlText().equals("") || tfTitre.getText().equals("") || tfTitre.getText() == null || !checkPublier.isSelected() || tfImageArticle.getText() == null || tfImageArticle.getText().equals("") || ivImageArticle.getImage() == null) {
            showDialog("Error", "Veuillez verifier que vous avez remplis tous les champs");
        } else {
            Article a = null;
            image = new File(tfImageArticle.getText());
            Map uploadResult = cloudinary.uploader().upload(image, ObjectUtils.emptyMap());
            if (idEntite.getValue().getClass() == Game.class) {
                Game g = (Game) idEntite.getValue();
                a = new Article(1, tfTitre.getText(), contenu.getHtmlText(), g.getId(), typeEntite.getValue(), new Date(Calendar.getInstance().getTime().getTime()), null, 0, (String) uploadResult.get("url"));
            } else if (idEntite.getValue().getClass() == Stade.class) {
                Stade g = (Stade) idEntite.getValue();
                a = new Article(1, tfTitre.getText(), contenu.getHtmlText(), g.getId(), typeEntite.getValue(), new Date(Calendar.getInstance().getTime().getTime()), null, 0, (String) uploadResult.get("url"));
            } else if (idEntite.getValue().getClass() == Player.class) {
                Player g = (Player) idEntite.getValue();
                a = new Article(1, tfTitre.getText(), contenu.getHtmlText(), g.getId(), typeEntite.getValue(), new Date(Calendar.getInstance().getTime().getTime()), null, 0, (String) uploadResult.get("url"));
            } else if (idEntite.getValue().getClass() == Team.class) {
                Team g = (Team) idEntite.getValue();
                a = new Article(1, tfTitre.getText(), contenu.getHtmlText(), g.getId(), typeEntite.getValue(), new Date(Calendar.getInstance().getTime().getTime()), null, 0, (String) uploadResult.get("url"));
            } 

            if (Admin.current_user != null) {
                a.setAuteur(Admin.current_user.getId());
            } else {
                a.setAuteur(Moderator.current_user.getId());
            }
            a.setPermalink("http://localhost/russie2k18/web/app_dev.php/admin/articles/new");
            a.setNum_comments(0);
            a.setIs_commentable(is_commentable);
            
            if (is_private == 1){
                a.setTypeEntity("Private");
            }
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

    @FXML
    private void addImageClicked(MouseEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une photo");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        image = fileChooser.showOpenDialog(null);
        if (image != null) {
            Map uploadResult = cloudinary.uploader().upload(image, ObjectUtils.emptyMap());
            WebView webView = (WebView) contenu.lookup(".web-view");
            WebPage webPage = Accessor.getPageFor(webView.getEngine());
            webPage.executeCommand("insertHTML", "<img src=" + uploadResult.get("url") + " width=200 height=200 />");
        }
    }

    @FXML
    private void addImageArticle(MouseEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une photo");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        image = fileChooser.showOpenDialog(null);
        if (image != null) {
            tfImageArticle.setText(image.getPath());
            ivImageArticle.setImage(new Image(image.toURI().toURL().toExternalForm()));
        }
    }

}
