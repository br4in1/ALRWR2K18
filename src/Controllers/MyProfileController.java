/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.SimpleUser;
import Services.UserCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author br4in
 */
public class MyProfileController implements Initializable {

	public static MyProfileController thisController;
	@FXML
	private Circle pic;
	@FXML
	private Label lastname;
	@FXML
	private Label firstname;
	public static String current_username;
	@FXML
	private FlowPane profileContent;
	@FXML
	private Label username;
	@FXML
	private Label email;
	@FXML
	private Label birthdate;
	@FXML
	private Label registrationdate;
	@FXML
	private Label nationality;
	@FXML
	private Label lastlogin;
	@FXML
	private HBox fidbox;
	@FXML
	private Label fidelitypoints;
	@FXML
	private HBox birthbox;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		thisController = this;
		if (current_username != null) {
			show();
		}
	}

	public void show() {
		SimpleUser u = UserCrud.getSimpleUserByUsername(current_username);
		lastname.setText(u.getLastname());
		firstname.setText(u.getFirstname());
		pic.setFill(new ImagePattern(new Image(u.getProfilepicture())));
		username.setText(current_username);
		email.setText(u.getEmail());
		if (u.getBirthdate() != null) {
			birthdate.setText(u.getBirthdate().toString());
		} else {
			birthdate.setText("-");
		}
		registrationdate.setText(u.getRegistrationdate().toString());
		if (u.getNationality() != null && !u.getNationality().equals("")) {
			nationality.setText(u.getNationality());
			nationality.setGraphic(new ImageView("assets/flags/" + u.getNationality().toLowerCase().replace(" ", "_") + ".png"));
			((ImageView) nationality.getGraphic()).setFitHeight(20);
			((ImageView) nationality.getGraphic()).setFitWidth(20);
		}
		else nationality.setText("-");
		if(u.getLoggedin()){
			lastlogin.setText("En ligne.");
			lastlogin.setGraphic(new ImageView("assets/online.png"));
			((ImageView) lastlogin.getGraphic()).setFitHeight(20);
			((ImageView) lastlogin.getGraphic()).setFitWidth(20);
		}
		else lastlogin.setText(u.getLast_login().toString());
		if(current_username.equals(SimpleUser.current_user.getUsername())){
			fidelitypoints.setText(""+u.getFidaelitypoints()+" pts.");
		}
		else fidbox.setVisible(false);
	}
}
