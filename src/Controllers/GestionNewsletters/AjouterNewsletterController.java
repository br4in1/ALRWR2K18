/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.GestionNewsletters;

import Entities.Article;
import Services.ArticleCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.web.HTMLEditor;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author raiiz
 */
public class AjouterNewsletterController implements Initializable {

    @FXML
    private AnchorPane ajouterNewsletterPane;
    @FXML
    private JFXButton btSendMail;
    @FXML
    private JFXTextField tfTitre;
    @FXML
    private JFXCheckBox checkPublier;
    @FXML
    private JFXButton btAjouterImage;
    @FXML
    private HTMLEditor contenu;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private JFXButton btAddEmail;
    private ObservableList<String> data;
    @FXML
    private TableView<String> tableEmails;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();
        TableColumn actionColumn = new TableColumn("Action");
        TableColumn emailColumn = new TableColumn("Email");
        actionColumn.setSortable(false);
        actionColumn.setCellFactory(param -> new TableCell<String, String>() {

            final JFXButton btn = new JFXButton("[X]");

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    btn.setButtonType(JFXButton.ButtonType.RAISED);
                    btn.setStyle("-fx-background-color:  #b12b2b; ");
                    btn.setTextFill(Paint.valueOf("WHITE"));

                    btn.setOnAction(event -> {
                        String email = (String) this.getTableView().getItems().get(this.getIndex());
                        data.remove(email);
                    });
                    setGraphic(btn);
                    setText(null);
                }
            }
        });
        tableEmails.getColumns().addAll(emailColumn, actionColumn);
    }

    @FXML
    private void addEmailClicked(MouseEvent event) {
        String email = tfEmail.getText();
        data.add(email);
       // tableEmails.setItems(data);

    }

    public void addButtonClicked() {
        System.out.println("close clicked");
    }

    // Define the button cell
    private class ButtonDelete extends TableCell {

        final JFXButton delButton = new JFXButton("Delete");

        ButtonDelete(final TableView tblView) {
            delButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    System.out.println("hi");
                }
            });
        }

        // Display button if the row is not empty
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(delButton);
            } else {
                setGraphic(null);
                setText("");
            }
        }
    }

}
