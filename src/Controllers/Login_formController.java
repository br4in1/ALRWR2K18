/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.SimpleUser;
import java.io.File;
import java.util.Map;
import com.cloudinary.*;
import Entities.User;
import Services.UserCrud;
import com.cloudinary.utils.ObjectUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author br4in
 */
public class Login_formController implements Initializable {

	Cloudinary cloudinary;
	@FXML
	private Pane loginform;
	@FXML
	private JFXTextField username;
	@FXML
	private JFXTextField username_login;
	@FXML
	private JFXTextField email;
	@FXML
	private JFXTextField lastname;
	@FXML
	private JFXTextField firstname;
	@FXML
	private JFXPasswordField password;
	@FXML
	private JFXPasswordField password_login;
	@FXML
	private JFXPasswordField passwordConfirm;
	@FXML
	private Pane signupform;
	@FXML
	private JFXRadioButton firstradio;
	@FXML
	private JFXRadioButton secondradio;
	@FXML
	private Pane photopick;
	@FXML
	private JFXComboBox<String> nationality;
	@FXML
	private ImageView countryavatar;
	@FXML
	private ImageView uploadphoto;
	@FXML
	private JFXDatePicker birthdate;
	@FXML
	private JFXButton signupbtn;
	@FXML
	private JFXButton uploadbtn;
	@FXML
	private StackPane welcomeSP;
	private static String current_username = null;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		cloudinary = new Cloudinary("cloudinary://212894137142756:7Coi2BsCet7rXqPmDAuBi08ONfQ@dbs7hg9cy");
		username.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					Boolean exist = UserCrud.findUserByUsername(username.getText());
					if (exist) {
						username.setUnFocusColor(Color.rgb(255, 0, 0, 1));
					} else {
						username.setUnFocusColor(Color.rgb(0, 0, 0, 1));
					}
				}
			}
		});

		email.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					Boolean exist = UserCrud.findUserByEmail(email.getText());
					if (exist) {
						email.setUnFocusColor(Color.rgb(255, 0, 0, 1));
					} else {
						email.setUnFocusColor(Color.rgb(0, 0, 0, 1));
					}
				}
			}
		});

		password.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					if (password.getText().length() < 7) {
						JFXDialogLayout content = new JFXDialogLayout();
						content.setHeading(new Text(""));
						content.setBody(new Text("Le mot de passe doit contenir au moins 7 caractères"));
						JFXDialog check_username = new JFXDialog(welcomeSP, content, JFXDialog.DialogTransition.CENTER);
						check_username.show();
					}
				}
			}
		});

		passwordConfirm.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					if (!passwordConfirm.getText().equals(password.getText())) {
						passwordConfirm.setUnFocusColor(Color.rgb(255, 0, 0, 1));
					} else {
						passwordConfirm.setUnFocusColor(Color.rgb(0, 0, 0, 1));
					}
				}
			}
		});
	}

	public void showSignUpForm() {
		loginform.setVisible(false);
		signupform.setVisible(true);
		ObservableList<String> cities = FXCollections.observableArrayList();
		String[] locales1 = Locale.getISOCountries();
		for (String countrylist : locales1) {
			Locale obj = new Locale("", countrylist);
			String[] city = {obj.getDisplayCountry()};
			for (int x = 0; x < city.length; x++) {
				cities.add(obj.getDisplayCountry());
			}
		}
		nationality.setItems(cities);
		nationality.setPromptText("Nationalité");
		Locale.setDefault(Locale.FRANCE);
	}

	public void hideSignUpForm() {
		loginform.setVisible(true);
		signupform.setVisible(false);
	}

	public Boolean CheckUsername() {
		Boolean exist = UserCrud.findUserByUsername(username.getText());
		if (exist) {
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text("Oops !"));
			content.setBody(new Text("Ce nom d'utilisateur existe déjà"));
			JFXDialog check_username = new JFXDialog(welcomeSP, content, JFXDialog.DialogTransition.CENTER);
			check_username.show();
			return exist;
		}
		return false;
	}

	public Boolean CheckEmail() {
		Boolean exist = UserCrud.findUserByUsername(email.getText());
		if (exist) {
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text("Oops !"));
			content.setBody(new Text("Cette adresse email existe déjà"));
			JFXDialog check_username = new JFXDialog(welcomeSP, content, JFXDialog.DialogTransition.CENTER);
			check_username.show();
			return exist;
		}
		return false;
	}

	public Boolean CheckPassword() {
		if (password.getText().length() < 7 || !passwordConfirm.getText().equals(password.getText())) {
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text("Oops !"));
			content.setBody(new Text("Les deux mots de passes doivent être exactes et contenant 7 caractères ou plus."));
			JFXDialog check_username = new JFXDialog(welcomeSP, content, JFXDialog.DialogTransition.CENTER);
			check_username.show();
			return false;
		}
		return true;
	}

	public void SignUpUserStepOne() {
		Boolean B1 = CheckUsername();
		Boolean B2 = CheckEmail();
		Boolean B3 = CheckPassword();
		if (!B1 && !B2 && B3) {
			Date today = Date.valueOf(LocalDate.now());
			SimpleUser u = new SimpleUser(Date.valueOf(birthdate.getValue()), today, nationality.getSelectionModel().getSelectedItem(), false, 0, null, username.getText(), email.getText(), false, null, password.getText(), null, "ROLE_USER", firstname.getText(), lastname.getText());
			UserCrud.AddUserToDataBaseStepOne(u);
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text("Tout est prêt ... ou presque !"));
			content.setBody(new Text("Consultez votre boite email pour activer votre compte."));
			JFXDialog check_username = new JFXDialog(welcomeSP, content, JFXDialog.DialogTransition.CENTER);
			check_username.show();
			check_username.setOnDialogClosed(new EventHandler<JFXDialogEvent>() {
				@Override
				public void handle(JFXDialogEvent event) {
					signupform.setVisible(false);
					loginform.setVisible(true);
				}

			});
		}
	}

	public void loginUser() {
		User u = UserCrud.AuthenticateUser(username_login.getText(), password_login.getText());
		if (u == null) {
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text(""));
			content.setBody(new Text("Veuillez vérifier les informations saisies."));
			JFXDialog check_data = new JFXDialog(welcomeSP, content, JFXDialog.DialogTransition.CENTER);
			check_data.show();
		} else if (u != null && !u.getEnabled()) {
			if (u.getRoles().equals("ROLE_ADMIN")) {
				//TODO : redirect to admin dashboard + session handling
			} else if (u.getRoles().equals("ROLE_MODERATOR")) {
				if (u.getEnabled()) {
					//TODO : redirect to moderator dashboard + session handling
				} else {
					JFXDialogLayout content = new JFXDialogLayout();
					content.setHeading(new Text("Veuillez contacter un administrateur."));
					content.setBody(new Text("Votre compte est désactivé."));
					JFXDialog activateAccount = new JFXDialog(welcomeSP, content, JFXDialog.DialogTransition.CENTER);
					activateAccount.show();
				}
			} else {
				if (u.getEnabled()) {
					//TODO : redirect to user screen + session handling
				} else {
					JFXDialogLayout content = new JFXDialogLayout();
					content.setHeading(new Text("Veuillez saisir le code que vous avez reçu sur votre boite."));
					JFXTextField confirmation_token = new JFXTextField();
					content.setBody(confirmation_token);
					JFXDialog activateAccount = new JFXDialog(welcomeSP, content, JFXDialog.DialogTransition.CENTER);
					activateAccount.show();
					confirmation_token.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							if (confirmation_token.getText().equals(UserCrud.getConfirmationToken(u.getUsername()))) {
								UserCrud.enableSimpleUser(u.getUsername());
								loginform.setVisible(false);
								activateAccount.setVisible(false);
								photopick.setVisible(true);
								String filename = ((SimpleUser) (u)).getNationality();
								filename = filename.replace(" ", "_").toLowerCase();
								countryavatar.setImage(new Image("assets/flags/" + filename + ".png"));
								current_username = u.getUsername();
							}
						}
					});
				}
			}
		}
	}

	public void FirstIsSelected() {
		firstradio.setSelected(true);
		secondradio.setSelected(false);
	}

	public void SecondIsSelected() {
		firstradio.setSelected(false);
		secondradio.setSelected(true);
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		File f = fileChooser.showOpenDialog(null);
		if (f != null) {
			Image img = new Image(f.toURI().toString());
			uploadphoto.setImage(img);
		}
	}

	public void UploadPhoto() throws Exception {
		if (!firstradio.isSelected() && !secondradio.isSelected()) {

		} else {
			File toUpload = new File("toUpload.png");
			if (firstradio.isSelected()) {
				BufferedImage bf = SwingFXUtils.fromFXImage(countryavatar.getImage(), null);
				ImageIO.write(bf, "png", toUpload);
			} else if (secondradio.isSelected() && uploadphoto.getImage().impl_getUrl() != "assets/update.png") {
				BufferedImage bf = SwingFXUtils.fromFXImage(uploadphoto.getImage(), null);
				ImageIO.write(bf, "png", toUpload);
			}
			System.out.println(toUpload.getPath());
			Map uploadResult = cloudinary.uploader().upload(toUpload, ObjectUtils.emptyMap());
			toUpload.delete();
			UserCrud.UpdateUserPhoto((String) uploadResult.get("url"), current_username);
		}
	}
}
