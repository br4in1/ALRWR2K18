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
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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
	private GridPane groupB;
	@FXML
	private GridPane groupC;
	@FXML
	private GridPane groupD;
	@FXML
	private GridPane groupE;
	@FXML
	private GridPane groupF;
	@FXML
	private GridPane groupG;
	@FXML
	private GridPane groupH;
	int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0;
	int a1 = 0, b1 = 0, c1 = 0, d1 = 0, e1 = 0, f1 = 0, g1 = 0;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		List<Game> gamesList = GameCrud.findAllGames();
		
		for (Game game : gamesList) {
			switch (TeamCrud.findByName(game.getHomeTeam()).getWcGroup()) {
				case "A":	
					if (a > 1) {
						a = 0;
						a1++;
					}
					groupA.add(match(TeamCrud.findByName(game.getHomeTeam()).getFlagPhoto(), TeamCrud.findByName(game.getAwayTeam()).getFlagPhoto(), game.getResult(), game.getStadium(), groupA.getChildren().size() + 1, game.getDate()), a, a);
					a++;
					groupA.getChildren().get(groupA.getChildren().size() - 1).setStyle("-fx-padding: 10 20 0 10;");
					break;
				case "B":	
					if (b > 1) {
						b = 0;
						b1++;
					}
					groupB.add(match(TeamCrud.findByName(game.getHomeTeam()).getFlagPhoto(), TeamCrud.findByName(game.getAwayTeam()).getFlagPhoto(), game.getResult(), game.getStadium(), groupB.getChildren().size() + 1, game.getDate()), b, b1);
					b++;
					groupB.getChildren().get(groupB.getChildren().size() - 1).setStyle("-fx-padding: 10 20 0 10;");
					break;
			}

		}
	}

	public VBox match(String homeTeam, String AwayTeam, String result, String stadium, int i, Date d) {
		VBox match = new VBox();
		Pane title = new Pane();
		title.setStyle("-fx-background-image: url(\"/assets/red.png\");");
		title.setMinHeight(40);
		Pane content = new Pane();
		content.setStyle("-fx-background-color: #fcfcfc;");
		content.setMinHeight(140);
		match.getChildren().add(title);
		match.getChildren().add(content);
		HBox sd = new HBox();
		HBox teams = new HBox();
		ImageView homeT = new ImageView(homeTeam);
		homeT.setFitHeight(53);
		homeT.setFitWidth(95);
		Label res = new Label(result);
		res.setStyle("-fx-padding: 16 20 0 20;");
		res.setFont(new Font("Dusha V5", 18));
		ImageView awayT = new ImageView(AwayTeam);
		awayT.setFitHeight(53);
		awayT.setFitWidth(95);
		teams.getChildren().add(homeT);
		teams.getChildren().add(res);
		teams.getChildren().add(awayT);
		teams.setSpacing(20);
		teams.setStyle("-fx-padding: 45 0 0 80;");
		content.getChildren().add(teams);
		Label stadium1 = new Label(stadium);
		Label gameNb = new Label("Game " + i);
		Label date1 = new Label(String.valueOf(d));
		sd.getChildren().add(stadium1);
		sd.getChildren().add(gameNb);
		sd.getChildren().add(date1);
		stadium1.setTextFill(Color.WHITE);
		stadium1.setPrefWidth(170);
		gameNb.setTextFill(Color.WHITE);
		gameNb.setStyle("-fx-padding: 10 100 0 30;");
		stadium1.setStyle("-fx-padding: 10 -20 0 20;");
		date1.setTextFill(Color.WHITE);
		date1.setStyle("-fx-padding: 10 20 0 0;");
		title.getChildren().add(sd);

		return match;
	}

}
