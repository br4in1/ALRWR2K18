/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Team;
import Services.TeamCrud;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.naming.NamingException;
import org.controlsfx.control.Notifications;
import java.util.Properties;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class AddFormTeamController implements Initializable {

	@FXML
	private JFXTextField name;
	@FXML
	private JFXTextField coach;
	@FXML
	private JFXTextField president;
	@FXML
	private JFXTextField area;
	@FXML
	private JFXTextField participation;
	@FXML
	private DatePicker date;
	@FXML
	private JFXTextField wcgroupe;
	@FXML
	private JFXTextField fifarank;
	@FXML
	private JFXTextField flagphoto;//
	@FXML
	private JFXTextField logophoto;//
	@FXML
	private JFXTextField squadphoto;//
	@FXML
	private JFXTextField descriptionphoto;//
	@FXML
	private JFXTextField website;
	@FXML
	private JFXTextField video;
	@FXML
	private JFXTextArea description;
	public static int x = 0;

	Cloudinary cloudinary;
	private File image; //flagphoto
	private File image2; //squadphoto
	private File image3; //logophoto
	private File image4;//descriptionphoto
	@FXML
	private StackPane TeamSP;
	@FXML
	private ImageView TeamImageView;

	private boolean flagP = false;
	private boolean squadP = false;
	private boolean logoP = false;
	private boolean descriptionP = false;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		cloudinary = new Cloudinary("cloudinary://212894137142756:7Coi2BsCet7rXqPmDAuBi08ONfQ@dbs7hg9cy");

	}

	@FXML
	private void submit(MouseEvent event) throws IOException, NamingException {

		// Notifications.create().title("kkk").text("success").show(); 
		//(String) uploadResult.get("url")           
		//,`FlagPhoto`, `LogoPhoto`, `SquadPhoto`, `DescriptionPhoto`,
		//condition on the existance of the team's name 
		// if(name.getText().trim()==null && coach.getText()==null && president.getText()==null && area.getText()==null && participation.getText()==null && date.getValue()== null && wcgroupe.getText()==null && fifarank.getText()==null && uploadResult.get("url")==null && uploadResult2.get("url")==null && uploadResult1.get("url")==null && uploadResult3.get("url")==null && description.getText()==null && website.getText()==null&& video.getText()==null)
		if (name.getText().trim().isEmpty() || coach.getText().trim().isEmpty() || president.getText().trim().isEmpty() || area.getText().trim().isEmpty() || participation.getText().trim().isEmpty() || date.getValue() == null || wcgroupe.getText().trim().isEmpty() || fifarank.getText().trim().isEmpty() || flagphoto.getText().trim().isEmpty() || logophoto.getText().trim().isEmpty() || squadphoto.getText().trim().isEmpty() || descriptionphoto.getText().trim().isEmpty() || description.getText().trim().isEmpty() || video.getText().trim().isEmpty()) {
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text("Error !"));
			content.setBody(new Text("Please fill all the fields !"));
			JFXDialog check_team = new JFXDialog(TeamSP, content, JFXDialog.DialogTransition.CENTER);
			check_team.show();
		} else {
			if ((participation.getText().matches("[0-9]*") && fifarank.getText().matches("[0-9]*")) == false) {//f || t
				JFXDialogLayout content = new JFXDialogLayout();
				content.setHeading(new Text("Error !"));
				content.setBody(new Text("Participations and fifa rank have to be numbers !"));
				JFXDialog check_team = new JFXDialog(TeamSP, content, JFXDialog.DialogTransition.CENTER);
				check_team.show();
			} else {
				if (flagP && squadP && logoP && descriptionP) {
					Boolean ok = TeamCrud.findTeamByName(name.getText());
					if (ok) {
						JFXDialogLayout content = new JFXDialogLayout();
						content.setHeading(new Text("Error !"));
						content.setBody(new Text("Sorry, this team's name already exist !"));
						JFXDialog check_team = new JFXDialog(TeamSP, content, JFXDialog.DialogTransition.CENTER);
						check_team.show();
					} else {

						Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
						alert.setTitle("Add confirmation");
						alert.setHeaderText("Are you sure about adding this team ?");
						Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == ButtonType.OK) {
							Map uploadResult = cloudinary.uploader().upload(image, ObjectUtils.emptyMap()); //flagphoto
							Map uploadResult1 = cloudinary.uploader().upload(image2, ObjectUtils.emptyMap()); //squadphoto
							Map uploadResult2 = cloudinary.uploader().upload(image3, ObjectUtils.emptyMap());//logophoto
							Map uploadResult3 = cloudinary.uploader().upload(image4, ObjectUtils.emptyMap());//descriptionphoto
							TeamCrud.addTeam(new Team(name.getText(), coach.getText(), president.getText(), area.getText(), Integer.parseInt(participation.getText()), Date.valueOf(date.getValue()), wcgroupe.getText(), Integer.parseInt(fifarank.getText()), (String) uploadResult.get("url"), (String) uploadResult2.get("url"), (String) uploadResult1.get("url"), (String) uploadResult3.get("url"), description.getText(), website.getText(), video.getText()));
							Properties props = new Properties();
							props.put("mail.smtp.host", "smtp.gmail.com");
							props.put("mail.smtp.socketFactory.port", "465");
							props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
							props.put("mail.smtp.auth", "true");
							props.put("mail.smtp.prot", "465");

							Session session = Session.getDefaultInstance(props,
									new javax.mail.Authenticator() {
								protected PasswordAuthentication getPasswordAuthentication() {

									return new PasswordAuthentication("moez.haddad@esprit.tn", "moezmoezhaddad71126429118");
								}
							}
							);
							try {
								Message message = new MimeMessage(session);
								message.setFrom(new InternetAddress("moez.haddad@esprit.tn"));
								message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("moezhdd@gmail.com"));
								message.setSubject("New add !");
								message.setText("You have added new team in your desktop application !");
								Transport.send(message);

							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, e);
							}

// *TeamCrud.addTeam(new Team(name.getText(),coach.getText(),president.getText(),area.getText(),Integer.parseInt(participation.getText()),Date.valueOf(date.getValue()),wcgroupe.getText(),Integer.parseInt(fifarank.getText()),flagphoto.getText(),logophoto.getText(),squadphoto.getText(),descriptionphoto.getText(),description.getText(),website.getText(),video.getText()));
							Notifications notificationBuilder
									= Notifications.create().title("Avertissment")
											.text("the team has been added ! ")
											.hideAfter(Duration.seconds(3))
											.position(Pos.TOP_RIGHT)
											.onAction((ActionEvent event1) -> {
												// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
											});

							notificationBuilder.showInformation();
						}

					}
				} else {
					JFXDialogLayout content = new JFXDialogLayout();
					content.setHeading(new Text("Error !"));
					content.setBody(new Text("Please insert all photos properly !"));
					JFXDialog check_team = new JFXDialog(TeamSP, content, JFXDialog.DialogTransition.CENTER);
					check_team.show();
				}

			}

		}

	}
	//String name, String coach, String president, String area,int participations, Date fifaDate, String wcGroup,                           int fifaRank, String flagPhoto, String logoPhoto, String squadPhoto, String descriptionPhoto, String description, String website, String video

	@FXML
	private void photo(MouseEvent event) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image = fileChooser.showOpenDialog(null);

		flagphoto.setText(image.getPath());
		Image image = new Image(new File(flagphoto.getText()).toURI().toString());
		TeamImageView.setImage(image);
		flagP = true;

	}

	@FXML
	private void photosquad(MouseEvent event) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image2 = fileChooser.showOpenDialog(null);

		squadphoto.setText(image2.getPath());
		Image image = new Image(new File(squadphoto.getText()).toURI().toString());
		TeamImageView.setImage(image);
		squadP = true;

	}

	@FXML
	private void photologo(MouseEvent event) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image3 = fileChooser.showOpenDialog(null);

		logophoto.setText(image3.getPath());

		Image image = new Image(new File(logophoto.getText()).toURI().toString());
		TeamImageView.setImage(image);
		logoP = true;

	}

	@FXML
	private void photodescription(MouseEvent event) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image4 = fileChooser.showOpenDialog(null);

		descriptionphoto.setText(image4.getPath());
		Image image = new Image(new File(descriptionphoto.getText()).toURI().toString());
		TeamImageView.setImage(image);
		descriptionP = true;

	}

}
