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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author raiiz
 */
public class ModifierContenuController implements Initializable {

    @FXML
    private HTMLEditor contenu;
    @FXML
    private JFXButton btModifier;
    @FXML
    private JFXButton btClear;

    private String oldContenu;
    private Article article;
    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        oldContenu = contenu.getHtmlText();

    }

    @FXML
    private void modiferClicked(MouseEvent event) {
        if (!contenu.getHtmlText().equals("")) {
            if (ArticleCrud.update(article.getId(), "contenu", contenu.getHtmlText()) != null) {
                showDialog("Success", "le contenu de l'article a ete modifie avec success");
            } else {
                showDialog("Error !!", "La Maj du contenu de l'article a echoe, veuillez essayer plus tard");
            }
        } else {
            showDialog("Error !!", "Veuillez ne pas laisser l'article vide");
        }
    }

    @FXML
    private void ClearClicked(MouseEvent event) {
        contenu.setHtmlText("");
    }

    public void setContenu(String contenu) {
        this.contenu.setHtmlText(contenu);
    }

    public void setArticle(Article a) {
        article = a;
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
    private void contenuTyped(KeyEvent event) {
        btModifier.setDisable(false);
    }
}
