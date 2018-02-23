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
import javafx.scene.layout.FlowPane;
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

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		thisController = this;
		if(current_username != null) show();
	}
	
	public void show(){
		SimpleUser u = UserCrud.getSimpleUserByUsername(current_username);
		lastname.setText(u.getLastname());
		firstname.setText(u.getFirstname());
		pic.setFill(new ImagePattern(new Image(u.getProfilepicture())));
	}
}
