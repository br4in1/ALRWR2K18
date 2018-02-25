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
	private JFXPasswordField oldpassword;
	@FXML
	private JFXPasswordField newpassword;
	private JFXTextField newpassword2;
	@FXML
	private JFXButton apply2;
	@FXML
	private FlowPane nav;
	@FXML
	private StackPane stack;
	@FXML
	private JFXPasswordField newpassword1;

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
		}
		else if(UserCrud.findUserByEmail(email.getText()) && !email.getText().equals(SimpleUser.current_user.getEmail())){
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text(""));
			content.setBody(new Text("This mail address already exists."));
			JFXDialog check_data = new JFXDialog(stack, content, JFXDialog.DialogTransition.CENTER);
			check_data.show();
		} else {
			UserCrud.editProfile(SimpleUser.current_user.getUsername(),(firstname.getText().length() != 0) ? firstname.getText() : SimpleUser.current_user.getFirstname(),(lastname.getText().length()!= 0) ? lastname.getText() : SimpleUser.current_user.getLastname(),birthdate.getValue(),(email.getText().length()!=0) ? email.getText() : SimpleUser.current_user.getEmail());
			password.setText("");
			oldpassword.setText("");
			FrontEndController.thisController.myProfileClicked();
		}
	}

	@FXML
	private void changePasswordAction(ActionEvent event) throws IOException {
		if (!UserCrud.CheckUserPassword(SimpleUser.current_user.getUsername(), oldpassword.getText())) {
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text(""));
			content.setBody(new Text("The old password you provided is wrong."));
			JFXDialog check_data = new JFXDialog(stack, content, JFXDialog.DialogTransition.CENTER);
			check_data.show();
		} else if(newpassword.getText() == null || newpassword.getText().length() < 7) {
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text(""));
			content.setBody(new Text("The new password should contains at least 7 characters."));
			JFXDialog check_data = new JFXDialog(stack, content, JFXDialog.DialogTransition.CENTER);
			check_data.show();
		}
		else if(!newpassword1.getText().equals(newpassword.getText())){
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text(""));
			content.setBody(new Text("The two passwords must be the same."));
			JFXDialog check_data = new JFXDialog(stack, content, JFXDialog.DialogTransition.CENTER);
			check_data.show();
		}
		else{
			UserCrud.ChangePasswordForUser(SimpleUser.current_user.getEmail(), newpassword.getText());
			password.setText("");
			oldpassword.setText("");
			FrontEndController.thisController.myProfileClicked();
		}
	}

}
