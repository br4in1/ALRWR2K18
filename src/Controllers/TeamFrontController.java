/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Team;
import Services.TeamCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class TeamFrontController implements Initializable {

	public static TeamFrontController thisController;
	@FXML
	private FlowPane profileContent;
	@FXML
	private Label lastname;
	@FXML
	private Label President;
	@FXML
	private Label coach;
	@FXML
	private Label fifaDate;
	@FXML
	private Label participation;
	@FXML
	private Label wcGroupe;
	@FXML
	private StackPane staticSwigNode;
	
	public static Integer current_team_id;
	@FXML
	private ImageView TeamImageView;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		thisController = this;
	
	}	
	
	public void refreshData(){
		Team t = TeamCrud.findById(current_team_id);
		lastname.setText(t.getName());
						President.setText(t.getPresident());
						coach.setText(t.getCoach());
						fifaDate.setText(t.toString());
						participation.setText(Integer.toString(t.getParticipations()));
						wcGroupe.setText(t.getWcGroup());

						//image view
						//	Image image = new Image(new File(TeamCrud.findById(ID).getSquadPhoto()).toString());
						TeamImageView.setImage(new Image(t.getDescriptionPhoto()));
	}
}
