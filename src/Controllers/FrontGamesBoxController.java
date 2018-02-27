/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.FrontGamesBoxController.selected;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author simo
 */
public class FrontGamesBoxController implements Initializable {

	@FXML
	private VBox nav;
	@FXML
	private Label liveScore;
	@FXML
	private Label prediction;
	@FXML
	private Label games;
	@FXML
	private Label groups;
	
	public static String selected;
	public static FrontGamesBoxController thisController;

	public void unhighlightAll() {
		games.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		groups.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		liveScore.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		prediction.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
	}

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		thisController = this;
		selected = "games";
		games.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");
	}

	@FXML
	private void unhighlightLiveScores(MouseEvent event) {
		if (!selected.equals("liveScore")) {
			liveScore.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		}
	}

	@FXML
	public void highlightLiveScores(MouseEvent event) {
		if (!selected.equals("liveScore")) {
			liveScore.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");
		}
	}

	@FXML
	private void unhighlightPrediction(MouseEvent event) {
		if (!selected.equals("prediction")) {
			prediction.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		}
	}

	@FXML
	public void highlightPrediction(MouseEvent event) {
		if (!selected.equals("prediction")) {
			prediction.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");
		}
	}

	@FXML
	private void unhighlightGames(MouseEvent event) {
		if (!selected.equals("games")) {
			games.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		}
	}

	@FXML
	public void highlightGames(MouseEvent event) {
		if (!selected.equals("games")) {
			games.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");
		}
	}

	@FXML
	private void unhighlightGroups(MouseEvent event) {
		if (!selected.equals("groups")) {
			groups.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		}
	}

	@FXML
	public void highlightGroups(MouseEvent event) {
		if (!selected.equals("groups")) {
			groups.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");
		}
	}

}
