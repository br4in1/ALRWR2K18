/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
						JFXDialog check_username = new JFXDialog((StackPane) username.getParent(), content, JFXDialog.DialogTransition.CENTER);
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
		Stage stage = (Stage) username.getScene().getWindow();
		stage.close();
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
