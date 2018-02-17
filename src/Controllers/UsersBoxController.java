/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Admin;
import Entities.Moderator;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author br4in
 */
public class UsersBoxController implements Initializable {

	@FXML
	private VBox nav;
	@FXML
	private JFXButton users;
	@FXML
	private JFXButton moderators;
	@FXML
	private JFXButton mainMenu1;
	@FXML
	private JFXButton usersStatics;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if(Admin.current_user == null && Moderator.current_user != null){
			moderators.setVisible(false);
		}
	}	

	@FXML
	private void gamesContent(MouseEvent event) {
	}
	
}
