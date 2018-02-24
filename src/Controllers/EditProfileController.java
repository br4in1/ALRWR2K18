/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.SimpleUser;
import Services.UserCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author br4in
 */
public class EditProfileController implements Initializable {

	@FXML
	private JFXTextField firstname;
	@FXML
	private JFXTextField lastname;
	@FXML
	private JFXDatePicker birthdate;
	@FXML
	private JFXTextField email;
	@FXML
	private JFXPasswordField password;
	@FXML
	private JFXButton apply1;
	@FXML
	private JFXTextField oldpassword;
	@FXML
	private JFXTextField newpassword;
	@FXML
	private JFXTextField newpassword2;
	@FXML
	private JFXButton apply2;
	@FXML
	private FlowPane nav;
	@FXML
	private StackPane stack;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		SimpleUser u = SimpleUser.current_user;
		firstname.setText(u.getFirstname());
		lastname.setText(u.getLastname());
		birthdate.setValue(SimpleUser.current_user.getBirthdate().toLocalDate());
		email.setText(u.getEmail());
	}

	@FXML
	private void editProfileAction(ActionEvent event) throws IOException {
		if (password.getText() == null) {
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text(""));
			content.setBody(new Text("Please provide your current password."));
			JFXDialog check_data = new JFXDialog(stack, content, JFXDialog.DialogTransition.CENTER);
			check_data.show();
		} else if (!UserCrud.CheckUserPassword(SimpleUser.current_user.getUsername(), password.getText())) {
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text(""));
			content.setBody(new Text("The password you provided is wrong."));
			JFXDialog check_data = new JFXDialog(stack, content, JFXDialog.DialogTransition.CENTER);
			check_data.show();
		} else {
			UserCrud.editProfile(SimpleUser.current_user.getUsername(),(firstname.getText().length() != 0) ? firstname.getText() : SimpleUser.current_user.getFirstname(),(lastname.getText().length()!= 0) ? lastname.getText() : SimpleUser.current_user.getLastname(),birthdate.getValue(),(email.getText().length()!=0) ? email.getText() : SimpleUser.current_user.getEmail());
			FrontEndController.thisController.myProfileClicked();
		}
	}

	@FXML
	private void changePasswordAction(ActionEvent event) {

	}

}
