/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Gallery;
import Services.GalleryCrud;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AjouterImageController implements Initializable {

    List<String> type;

    @FXML
    private Text emp;
    @FXML
    private JFXTextField Emplacement;
    @FXML
    private JFXTextArea Description;
    @FXML
    private Text Des;
    @FXML
    private Button choix;
    @FXML
    private Button Enregister;
    @FXML
    private Text Url;
    @FXML
    private StackPane Validation;

    @FXML
    private void Filechooser(ActionEvent event) {
        FileChooser F = new FileChooser();
        F.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", type));
        File Fc = F.showOpenDialog(null);
        if (Fc != null) {
            Url.setText(Fc.getAbsolutePath());
        }

    }

    @FXML
    private void ajout(ActionEvent event) {
        GalleryCrud g = new GalleryCrud();
        Gallery pr = new Gallery(0, 0, Emplacement.getText(), Description.getText(), Url.getText());

        g.AjouterImage(pr);
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(""));
        content.setBody(new Text("Ajout fait avec succés"));
        JFXDialog check_username = new JFXDialog(Validation, content, JFXDialog.DialogTransition.CENTER);
        check_username.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        type = new ArrayList<>();
        type.add("*.png");
        type.add("*.jpg");
    }

}
