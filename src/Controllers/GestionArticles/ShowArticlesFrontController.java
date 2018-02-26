/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.GestionArticles;

import Entities.Article;
import Services.ArticleCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    public static int content2Display = 1;    /* 1-all , 2-Match, 3-Equipe, 4-Stade*/
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
        setContent();
        for (Node n : vBig.getChildren()) {
            n.setOnMouseClicked((e)
                    -> {
                try {
                    showOneArticlePane = FXMLLoader.load(getClass().getResource("/Views/GestionArticles/ArticleShowPopUpFront.fxml"));
                    //clearContent();
                } catch (IOException ex) {
                    Logger.getLogger(ShowArticlesFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println(n.getUserData());
            });
        }
        vBig.setCursor(Cursor.HAND);
        
    }

    public void clearContent() {
        vBig.getChildren().clear();
    }

    private void showDialog(String titre, String contenu) {
        Stage stage = (Stage) homeContent.getScene().getWindow();
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(titre));
        content.setBody(new Text(contenu));
        StackPane stackpane = new StackPane();
        JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("Close");
        button.setButtonType(JFXButton.ButtonType.RAISED);

        Scene scene = new Scene(stackpane, 300, 250);
        homeContent.getChildren().add(stackpane);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        content.setActions(button);
        dialog.show();
    }

    public void setContent() {
        vBig.getChildren().clear();
        List<Article> a = null;
        if (content2Display == 1) {
            a = ArticleCrud.getRepository().findAllOrderDate();
        } else if (content2Display == 2){
            a = ArticleCrud.getRepository().findAllByCategory("Match");
        }else if (content2Display == 3){
            a = ArticleCrud.getRepository().findAllByCategory("Equipe");
        }
        else if (content2Display == 4){
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
    }
}
