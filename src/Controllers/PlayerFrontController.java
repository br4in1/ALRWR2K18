/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Player;
import Services.PlayerCrud;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class PlayerFrontController implements Initializable {

	@FXML
	private StackPane playerSP;
	@FXML
	private ImageView PlayerImageView;
	public static int current_player_id ;
	@FXML
	private JFXComboBox<Integer> id;
	@FXML
	private Label name;
	@FXML
	private Label lastName;
	@FXML
	private Label Nation;
	@FXML
	private Label Age;
	@FXML
	private Label Height;
	@FXML
	private Label Club;
	@FXML
	private Label Weigth;
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		id.getItems().add(current_player_id);
		id.getSelectionModel().selectFirst();
		Player P = PlayerCrud.findById(current_player_id);
		PlayerImageView.setImage(new Image(P.getProfilePhoto()));
		name.setText(P.getName());
		lastName.setText(P.getLastName());
		Age.setText(Integer.toString(P.getAge()));
		Nation.setText(P.getNation());
		Club.setText(P.getClub());
		Height.setText(Double.toString(P.getHeight()));
		Weigth.setText(Double.toString(P.getWeight()));
	}	

	
}
