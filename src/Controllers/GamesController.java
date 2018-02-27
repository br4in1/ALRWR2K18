/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Game;
import Services.GameCrud;
import Services.TeamCrud;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author simo
 */
public class GamesController implements Initializable {

	@FXML
	private FlowPane profileContent;
	@FXML
	private GridPane groupA;
	@FXML
	private GridPane groupA1;
	@FXML
	private GridPane groupA2;
	@FXML
	private GridPane groupA3;
	@FXML
	private GridPane groupA4;
	@FXML
	private GridPane groupA5;
	@FXML
	private GridPane groupA6;
	@FXML
	private GridPane groupA7;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		List<Game> gamesList = GameCrud.findAllGames();
		
		for (Game game : gamesList) {
			System.out.println(TeamCrud.findByName(game.getHomeTeam()).getWcGroup());
				
			
		}
	}	
	
}
