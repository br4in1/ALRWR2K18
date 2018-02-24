/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.SimpleUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;

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
	private JFXTextField birthdate;
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

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		SimpleUser u = SimpleUser.current_user;
		firstname.setText(u.getFirstname());
		lastname.setText(u.getLastname());
		birthdate.setText(u.getBirthdate().toString());
		email.setText(u.getEmail());
		apply1.setDisable(true);
		apply2.setDisable(true);
	}	

	@FXML
	private void editProfileAction(ActionEvent event) {
		
	}

	@FXML
	private void changePasswordAction(ActionEvent event) {
		
	}
	
}
