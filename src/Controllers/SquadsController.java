/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Player;
import Services.PlayerCrud;
import Services.TeamCrud;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author simo
 */
public class SquadsController implements Initializable {
	@FXML
	private Pane pane;
	@FXML
	private JFXComboBox HomeTeam;
	@FXML
	private JFXComboBox AwayTeam;
	
	private HashMap<String, Integer> map;
	@FXML
	private TableColumn<Player, String> positionHome;
	@FXML
	private TableColumn<Player, String> nameHome;
	@FXML
	private TableColumn<Player, Circle> iconHome;
	@FXML
	private TableColumn<Player, String> positionAway;
	@FXML
	private TableColumn<Player, String> NameAway;
	@FXML
	private TableColumn<Player, Circle> IconAway;
	/**
	 * Initializes the controller class.
	 */
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	    
		map = TeamCrud.GetNameIdMap();
		HomeTeam.setItems(FXCollections.observableArrayList(map.keySet()));
		AwayTeam.setItems(FXCollections.observableArrayList(map.keySet()));
		positionHome.setCellValueFactory(
            new PropertyValueFactory<>("name"));
		nameHome.setCellValueFactory(
            new PropertyValueFactory<>("lastname"));
		
		 HomeTeam.setOnAction((event) -> {
			 ObservableList<Player> OL = FXCollections.observableList(PlayerCrud.findPlayersByNation(map.get(HomeTeam.getSelectionModel().getSelectedItem())));
  });
	}	
	
}
