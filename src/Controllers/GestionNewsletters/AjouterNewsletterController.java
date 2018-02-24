/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.GestionNewsletters;

import Entities.Newsletter;
import Services.NewsletterCrud;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.webkit.Accessor;
import com.sun.webkit.WebPage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
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
    Cloudinary cloudinary;
    private File image;
    @FXML
    private Pane contentPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cloudinary = new Cloudinary("cloudinary://187685892358282:rL27N346tuXqVQyA5sR1oDLFJag@pidev");
        data = FXCollections.observableArrayList();
        TableColumn actionColumn = new TableColumn("Action");
        TableColumn emailColumn = new TableColumn<String, String>("Email");
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

        emailColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String, String> p) {
                String x = p.getValue();
                if (x != null) {
                    return new SimpleStringProperty(x);
                } else {
                    return new SimpleStringProperty("<no name>");
                }
            }
        });
        actionColumn.setStyle("-fx-alignment: CENTER;");
        emailColumn.setStyle("-fx-alignment: CENTER;");
        actionColumn.prefWidthProperty().bind(tableEmails.widthProperty().multiply(0.2));
        emailColumn.prefWidthProperty().bind(tableEmails.widthProperty().multiply(0.8));

        tableEmails.getColumns().addAll(emailColumn, actionColumn);

        checkPublier.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (checkPublier.isSelected()) {
                    btSendMail.setDisable(false);
                } else {
                    btSendMail.setDisable(true);
                }
            }

        });
    }

    @FXML
    private void addEmailClicked(MouseEvent event) {
        String email = tfEmail.getText();
        if (email.equals("") || email == null || !email.contains("@")) {
            showDialog("Error", "Veuillez saisir une adresse mail valide");
            return;
        }
        if (!data.contains(email)) {
            data.add(email);
            tableEmails.setItems(data);
        } else {
            showDialog("Error", "Veuillez ne pas ajouter la meme addresse mail");
            return;
        }

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

    private void showDialog(String titre, String contenu) {
        Stage stage = (Stage) ajouterNewsletterPane.getScene().getWindow();
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(titre));
        content.setBody(new Text(contenu));
        StackPane stackpane = new StackPane();
        JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("Close");
        button.setButtonType(JFXButton.ButtonType.RAISED);

        Scene scene = new Scene(stackpane, 300, 250);
        ajouterNewsletterPane.getChildren().add(stackpane);

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
    private void sendMailClicked(MouseEvent event) {
        if (tfTitre.getText() == null || tfTitre.equals("") || contenu.getHtmlText().equals("") || contenu.getHtmlText() == null || data.isEmpty() || !checkPublier.isSelected()) {
            showDialog("Error", "Veuillez verifier que vous avez remplis tout les champs");
            return;
        }
        String USER_NAME = "raiizowqw";  // GMail user name (just the part before "@gmail.com")
        String PASSWORD = "kaisbahloul199659"; // GMail password
        ArrayList<String> a = new ArrayList<>();
        a.addAll(data);
        String from = USER_NAME;
        String pass = PASSWORD;
        String subject = tfTitre.getText();
        String body = contenu.getHtmlText();
        if (sendFromGMail(from, pass, a, subject, body)) {
            a.forEach(e -> {
                if (!NewsletterCrud.add(new Newsletter(1, e, body, 2))) {
                    showDialog("Error", "La newsletter ne peut pas etre sauvegarder dans la base de données");
                } else {
                    showDialog("Success", "La newsletter a ete sauvegarder dans la base de données");
                }
            });
        }
    }

    private boolean sendFromGMail(String from, String pass, ArrayList<String> to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.size()];

            // To get the array of addresses
            for (int i = 0; i < to.size(); i++) {
                toAddress[i] = new InternetAddress(to.get(i));
            }

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setContent(body, "text/html");
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (AddressException ae) {
            showDialog("Error", "Un error est subis lors de l'envoie de mail");
            ae.printStackTrace();
            return false;
        } catch (MessagingException me) {
            showDialog("Error", "Un error est subis lors de l'envoie de mail");
            me.printStackTrace();
            return false;
        }
    }
}
