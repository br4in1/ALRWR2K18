/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Team;
import Services.TeamCrud;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class FrontTeamBoxController implements Initializable {

	@FXML
	private VBox nav;
	public static List<Team> list  ;

	/**
	 * Initializes the controller class.
	 */
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		list = TeamCrud.findAllTeam() ;
		for(int i = 0;i < list.size();i++){
			Label eq = new Label();
			eq.setText(list.get(i).getName());
			ImageView im = new ImageView(list.get(i).getFlagPhoto());
			im.setFitHeight(50);
			im.setFitWidth(70);
			eq.setGraphic(im);
			eq.setId(String.valueOf(list.get(i).getId()));
			nav.getChildren().add(eq);
		}
		
		
	}	
	
}
