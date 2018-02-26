/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.GestionArticles;

import Entities.Article;
import Services.ArticleCrud;
import com.jfoenix.controls.JFXButton;
import java.awt.Font;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import static javafx.scene.paint.Color.WHITE;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Article> a = ArticleCrud.getRepository().findAll();
        topBar.setStyle("-fx-padding: 10;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: #66ae2e;");
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
                    /*+ "-fx-padding: 2;"
                    + "-fx-background-color: #66ae2e;"*/
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
        for (Node n : vBig.getChildren()) {
            n.setOnMouseClicked((e)
                    -> {
                System.out.println(n.getUserData());
            });
        }
        vBig.setCursor(Cursor.HAND);
    }

}
