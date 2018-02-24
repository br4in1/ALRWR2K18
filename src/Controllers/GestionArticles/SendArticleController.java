/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.GestionArticles;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
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
public class SendArticleController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXTextField tfArticleTitle;
    @FXML
    private JFXCheckBox chechEditArticle;
    @FXML
    private HTMLEditor contenu;
    @FXML
    private JFXTextField tfRecipient;
    @FXML
    private JFXButton btMoreRecipient;
    @FXML
    private JFXButton btSend;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chechEditArticle.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (chechEditArticle.isSelected()) {
                    tfArticleTitle.setDisable(false);
                } else {
                    tfArticleTitle.setDisable(true);
                }
            }

        });
    }

    private void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
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
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
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
            showDialog("Success", "l'article a été envoye avec success");
        } catch (AddressException ae) {
            showDialog("Error", "Un error est subis lors de l'envoie de mail");
            ae.printStackTrace();
        } catch (MessagingException me) {
            showDialog("Error", "Un error est subis lors de l'envoie de mail");
            me.printStackTrace();
        }
    }

    @FXML
    private void sendMailClicked(MouseEvent event) {
        String USER_NAME = "raiizowqw";  // GMail user name (just the part before "@gmail.com")
        String PASSWORD = "kaisbaghloul199659"; // GMail password
        String RECIPIENT = tfRecipient.getText();

        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = {RECIPIENT}; // list of recipient email addresses
        String subject = tfArticleTitle.getText();
        String body = contenu.getHtmlText();
        sendFromGMail(from, pass, to, subject, body);
    }

    public void setTitle(String title) {
        tfArticleTitle.setText(title);
    }

    public void setContenu(String contenu) {
        this.contenu.setHtmlText(contenu);
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
}
