/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Moderator;
import Services.UserCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author br4in
 */
public class AddModeratorController implements Initializable {
	
	@FXML
	private JFXTextField username;
	@FXML
	private JFXTextField firstname;
	@FXML
	private JFXTextField email;
	@FXML
	private JFXTextField lastname;
	@FXML
	private JFXPasswordField password;
	@FXML
	private JFXPasswordField confirmpassword;
	@FXML
	private JFXTextField phonenumber;
	@FXML
	private JFXButton addbutton;
	@FXML
	private JFXButton resetbutton;
	@FXML
	private StackPane errorDialog;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
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
						JFXDialog check_username = new JFXDialog(errorDialog, content, JFXDialog.DialogTransition.CENTER);
						check_username.show();
					}
				}
			}
		});
		
		confirmpassword.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					if (!confirmpassword.getText().equals(password.getText())) {
						confirmpassword.setUnFocusColor(Color.rgb(255, 0, 0, 1));
					} else {
						confirmpassword.setUnFocusColor(Color.rgb(0, 0, 0, 1));
					}
				}
			}
		});
	}
	
	@FXML
	private void addAction(ActionEvent event) {
		if (UserCrud.findUserByUsername(username.getText())) {
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text(""));
			content.setBody(new Text("This username already exists."));
			JFXDialog check_username = new JFXDialog(errorDialog, content, JFXDialog.DialogTransition.CENTER);
			check_username.show();
		} else if (UserCrud.findUserByEmail(email.getText())) {
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text(""));
			content.setBody(new Text("This email address already exists."));
			JFXDialog check_username = new JFXDialog(errorDialog, content, JFXDialog.DialogTransition.CENTER);
			check_username.show();
		} else if (firstname.getText().equals("") || lastname.getText().equals("")) {
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text(""));
			content.setBody(new Text("Firstname and lastname are required."));
			JFXDialog check_username = new JFXDialog(errorDialog, content, JFXDialog.DialogTransition.CENTER);
			check_username.show();
		} else if (password.getText().length() < 7) {
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text(""));
			content.setBody(new Text("The password must contains at least 7 characters."));
			JFXDialog check_username = new JFXDialog(errorDialog, content, JFXDialog.DialogTransition.CENTER);
			check_username.show();
		} else if (!confirmpassword.getText().equals(password.getText())) {
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text(""));
			content.setBody(new Text("Please check your password confirmation."));
			JFXDialog check_username = new JFXDialog(errorDialog, content, JFXDialog.DialogTransition.CENTER);
			check_username.show();
		} else if (phonenumber.getText().equals("")) {
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text(""));
			content.setBody(new Text("Phone number is required."));
			JFXDialog check_username = new JFXDialog(errorDialog, content, JFXDialog.DialogTransition.CENTER);
			check_username.show();
		} else {
			Moderator m = new Moderator(phonenumber.getText(), username.getText(), email.getText(), true, "", password.getText(), null, "ROLE_MODERATOR", firstname.getText(), lastname.getText());
			if (UserCrud.AddModeratorToDb(m)) {
				JFXDialogLayout content = new JFXDialogLayout();
				content.setHeading(new Text(""));
				JFXButton valider = new JFXButton("OK");
				valider.setStyle("-fx-background-color :  #396ec9; -fx-text-fill : white");
				Stage stage = (Stage) username.getScene().getWindow();
				valider.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						stage.close();
					}
				});
				content.setBody(new Text("The moderator was added succesfully."));
				content.getBody().add(valider);
				valider.setAlignment(Pos.BOTTOM_RIGHT);
				JFXDialog check_username = new JFXDialog(errorDialog, content, JFXDialog.DialogTransition.CENTER);
				check_username.show();
			} else {
				JFXDialogLayout content = new JFXDialogLayout();
				content.setHeading(new Text(""));
				content.setBody(new Text("A problem occured. Please try again."));
				JFXDialog check_username = new JFXDialog(errorDialog, content, JFXDialog.DialogTransition.CENTER);
				check_username.show();
			}
		}
	}
	
	@FXML
	private void resetfields(ActionEvent event) {
		username.setText("");
		email.setText("");
		password.setText("");
		confirmpassword.setText("");
		lastname.setText("");
		firstname.setText("");
		phonenumber.setText("");
	}
	
}
