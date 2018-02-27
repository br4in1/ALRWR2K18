/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.GestionArticles;

import Entities.Article;
import Entities.User;
import Services.ArticleCrud;
import Services.UserCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.jsoup.Jsoup;

/**
 * FXML Controller class
 *
 * @author raiiz
 */
public class ShowArticlesFrontController implements Initializable {

    @FXML
    private FlowPane homeContent;
    @FXML
    private ScrollPane contentPane;
    private AnchorPane showOneArticlePane;
    public static int content2Display = 1;
    /* 1-all , 2-Match, 3-Equipe, 4-Stade*/
    public static ShowArticlesFrontController thisController;

    /**
     * Initializes the controller class.
     */
    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }
    @FXML
    private VBox vBig;
    @FXML
    private HBox topBar;
    @FXML
    private Label lbRecentNews;
    public String category;
    @FXML
    private StackPane contentStackPane;
    @FXML
    private Label lbArticleTitle;
    @FXML
    private ImageView imageViewArticle;
    @FXML
    private Label lbDateArticle;
    @FXML
    private Label lbArticleAuteur;
    @FXML
    private WebView wvArticleContenu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        thisController = this;
        category = "all";
        topBar.setStyle("-fx-padding: 10;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: #66ae2e;");
        contentPane.setMaxHeight(530);
		
        setContent();
        for (Node n : vBig.getChildren()) {
            n.setOnMouseClicked((e)
                    -> {
                AbstractMap.SimpleEntry<Article, User> m = ArticleCrud.getRepository().findArticleUserById((int) n.getUserData());
                System.out.println(m);
                lbArticleAuteur.setText("By: " + m.getValue().getLastname() + " " + m.getValue().getFirstname());
                lbArticleTitle.setText(" "+m.getKey().getTitre());
                lbDateArticle.setText("Written : " + m.getKey().getDatePublication().toString());
                WebEngine we = wvArticleContenu.getEngine();
                we.loadContent(m.getKey().getContenu());
                imageViewArticle.setImage(new Image(m.getKey().getArticleImage()));
               

            });
        }
        vBig.setCursor(Cursor.HAND);

    }

    public void clearContent() {
        vBig.getChildren().clear();
    }

    public void setContent() {
        vBig.getChildren().clear();
        List<Article> a = null;
        if (content2Display == 1) {
            a = ArticleCrud.getRepository().findAllOrderDate();
        } else if (content2Display == 2) {
            a = ArticleCrud.getRepository().findAllByCategory("Match");
        } else if (content2Display == 3) {
            a = ArticleCrud.getRepository().findAllByCategory("Equipe");
        } else if (content2Display == 4) {
            a = ArticleCrud.getRepository().findAllByCategory("Stade");
        }
        if (a.isEmpty()) {
            Label l = new Label("No News Yet");
            l.setFont(Font.font(30));
            vBig.getChildren().add(l);
            return;
        }

        for (int i = 0; i < a.size(); i++) {
            HBox hb = new HBox(8);
            hb.setUserData(a.get(i).getId());
            hb.setStyle("-fx-padding: 10;"
                    + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;"
                    + "-fx-border-radius: 5;"
                    + "-fx-border-color: #66ae2e;");
            VBox vb = new VBox(8);
            ImageView iv = new ImageView(a.get(i).getArticleImage());
            iv.setFitWidth(200);
            iv.setFitHeight(200);
            StackPane sp = new StackPane(iv);
            sp.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 7, 0, 0, 0);"
                    + "-fx-background-radius: 5;");

            hb.getChildren().add(sp);
            Label titre = new Label(a.get(i).getTitre());
            titre.setFont(javafx.scene.text.Font.font(29));

            Label contenu = new Label(html2text(a.get(i).getContenu()));
            contenu.setFont(javafx.scene.text.Font.font(19));
            Label date = new Label(a.get(i).getDatePublication().toString());
            vb.getChildren().add(titre);
            vb.getChildren().add(date);
            vb.getChildren().add(contenu);
            hb.getChildren().add(vb);
            vBig.getChildren().add(hb);
        }
    }

   /* private void showDialog(String titre, String contenu) {
        ScrollPane sp = new ScrollPane();
        sp.setMaxWidth(400);
        sp.setMaxHeight(650);
        WebView wv = new WebView();
        wv.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 7, 0, 0, 0);"
                + "-fx-background-radius: 5;");
        wv.setDisable(true);
        wv.setCursor(Cursor.TEXT);
        sp.setContent(wv);
        WebEngine we = wv.getEngine();
        we.loadContent(contenu);
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(titre));
        content.setBody(wv);
        content.setMaxHeight(350);
        content.setMaxWidth(500);
        StackPane stackpane = new StackPane();
        JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("Close");
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        content.setActions(button);
        dialog.show(contentStackPane);

    }*/
}
